package Client.controllers;
import java.net.Socket;
import java.net.SocketException;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class GameWindowController 
{

    @FXML
    private ImageView rope;

    private Socket clientSocket;

    private int clientDirection = 1;
    final private int amountOfPixelsToMove = 10;

    private JoinServerMenuController mainController;

    public void SetMainController(JoinServerMenuController mainController)
    {
        this.mainController = mainController;
    }
    
    public void SetClientDirection(int dir)
    {
        clientDirection = dir;
    }

    public void SetClientSocket(Socket socket)
    {
        clientSocket = socket;
    }

    public void UpdateGame()
    {
        //while game is not over from server
        //get position of rope
    }

    @FXML
    public void Pull()
    {
        //Send info to server about input
        double newXPosition = clientDirection * amountOfPixelsToMove + rope.getX();
        rope.setX(newXPosition);
    }
}
