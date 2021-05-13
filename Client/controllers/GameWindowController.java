package Client.controllers;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ResourceBundle.Control;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GameWindowController extends Control implements OnGameFinishedListiner
{

    @FXML
    private ImageView rope;

    @FXML
    private Pane gamePane;

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
        UpdateGame update = new UpdateGame(bf,pr, rope, this);
        update.start();
    }

    @FXML
    public void Pull()
    {
        //Send info to server about input
        pr.println("Pull");
        pr.flush();
    }

    @Override
    public void FinishGame(String winLoseInfo) 
    {
        //Load restat window
        var loader = new FXMLLoader(this.getClass().getResource("../view/RestartWindow.fxml"));

        Pane pane = null;
        try 
        {
            pane = loader.load();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        var scene = new Scene(pane);

        Stage stage = (Stage)gamePane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("JoinWindow");
        stage.show();
    }
}
