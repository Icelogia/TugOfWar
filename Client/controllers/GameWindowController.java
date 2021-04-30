package Client.controllers;
import java.net.Socket;
import java.net.SocketException;

import javafx.fxml.FXML;

public class GameWindowController 
{

    private Socket clientSocket;

    private int clientDirection = 0;
    final private int amountOfPixelsToMove = 5;

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

    @FXML
    public void Pull()
    {
        
    }
}
