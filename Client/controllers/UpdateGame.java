package Client.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javafx.scene.image.ImageView;

public class UpdateGame extends Thread
{
    private ImageView rope;
    private BufferedReader bf;

    public UpdateGame(BufferedReader bf, ImageView rope)
    {
        this.bf = bf;
        this.rope = rope;
    }
    
    @Override
    public void run()
    {
        while(true)
        {
            SetRopePosition();
        } 
    }

    private void SetRopePosition() 
    {
        double newXPosition = rope.getX();
        try 
        {
            int move = Integer.parseInt(bf.readLine());
            System.out.println(move);
            newXPosition += move;
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        
        rope.setX(newXPosition);
    }
}
