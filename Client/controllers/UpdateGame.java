package Client.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javafx.application.Platform;
import javafx.scene.image.ImageView;

public class UpdateGame extends Thread
{
    private ImageView rope;
    private BufferedReader bf;
    private PrintWriter pr;
    private boolean isGameInProgress = true;
    
    final String winInfo = "You win!";
    final String loseInfo = "You lose!";
    final String activeMsg = "Active";
    
    OnGameFinishedListiner gameFinishedListiner;

    double newXPosition = 0;

    public UpdateGame(BufferedReader bf, PrintWriter pr, ImageView rope, OnGameFinishedListiner listiner)
    {
        this.bf = bf;
        this.pr = pr;
        this.rope = rope;
        this.gameFinishedListiner = listiner;
        newXPosition = rope.getX();
    }
    
    @Override
    public void run()
    {

        while(isGameInProgress)
        {
            GetInfoFromServer();
            SetRopePosition();
            ServerActiveClientChecker();
        } 
    }

    private void ServerActiveClientChecker() 
    {
        pr.println("Active");
        pr.flush();
    }

    private void SetRopePosition() 
    {
        rope.setX(newXPosition);
    }

    private void GetInfoFromServer()
    {
        try 
        {
            String msg = bf.readLine();
            System.out.println(msg);

            if(msg.equals("Win"))
            {
                isGameInProgress = false;
                Platform.runLater(() -> gameFinishedListiner.FinishGame(winInfo));
            }
            else if(msg.equals("Lose"))
            {
                isGameInProgress = false;
                Platform.runLater(() -> gameFinishedListiner.FinishGame(loseInfo));
            }
            else
            {
                newXPosition = Integer.parseInt(msg);
            }
            
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
}
