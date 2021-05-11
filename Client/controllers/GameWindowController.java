package Client.controllers;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class GameWindowController 
{

    @FXML
    private ImageView rope;

    private Socket clientSocket;

    private JoinServerMenuController mainController;

    private PrintWriter pr;
    private InputStreamReader in;
    private BufferedReader bf;

    public void SetMainController(JoinServerMenuController mainController)
    {
        this.mainController = mainController;
    }

    public void SetClientSocket(Socket socket)
    {
        clientSocket = socket;

        try 
        {
            pr = new PrintWriter(clientSocket.getOutputStream());
            in = new InputStreamReader(clientSocket.getInputStream());
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }

        bf = new BufferedReader(in);

        StartGame();
    }

    public void StartGame()
    {
        UpdateGame update = new UpdateGame(bf, rope);
        update.start();
    }

    

    @FXML
    public void Pull()
    {
        //Send info to server about input
        pr.println("Pull");
        pr.flush();
    }
}
