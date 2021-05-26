package Client.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javafx.application.Platform;
import javafx.scene.image.ImageView;

public class UpdateGame extends Thread
{
    private ImageView rope;
    private BufferedReader bf;
    private boolean isGameInProgress = true;
    
    final String winInfo = "You win!";
    final String loseInfo = "You lose!";
    final String activeMsg = "Active";
    
    OnGameUpdateListiner  gameUpdateListiner;

    double newXPosition = 0;

    public UpdateGame(BufferedReader bf, ImageView rope, OnGameUpdateListiner  listiner)
    {
        this.bf = bf;
        this.rope = rope;
        this.gameUpdateListiner = listiner;
        newXPosition = rope.getX();
    }
    
    @Override
    public void run()
    {
        while(isGameInProgress)
        {
            GetInfoFromServer();
            SetRopePosition();
        } 
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
            
            if(msg.equals("Win"))
            {
                isGameInProgress = false;
                Platform.runLater(() -> gameUpdateListiner.FinishGame(winInfo));
            }
                else if(msg.equals("Lose"))
            {
                isGameInProgress = false;
                Platform.runLater(() -> gameUpdateListiner.FinishGame(loseInfo));
            }
            else if(msg.contains("Left"))
            {
                System.out.println(msg + " " + "Update ");
                String[] amountMsg = msg.split(" ");
                int amount = Integer.parseInt(amountMsg[1]);
                Platform.runLater(() -> gameUpdateListiner.UpdateAmountOfLeftPlayers(amount)); 
            }
            else if(msg.contains("Right"))
            {
                System.out.println(msg + " " + "Update ");
                String[] amountMsg = msg.split(" ");
                int amount = Integer.parseInt(amountMsg[1]);
                Platform.runLater(() -> gameUpdateListiner.UpdateAmountOfRightPlayers(amount));
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
