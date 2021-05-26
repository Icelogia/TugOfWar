package Client.controllers;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GameWindowController implements OnGameUpdateListiner 
{
    @FXML
    private Label teamLeftNumber;

    @FXML
    private Label teamRightNumber;

    @FXML
    private ImageView rope;

    @FXML
    private Pane gamePane;

    private Socket clientSocket;

    private PrintWriter pr;
    private InputStreamReader in;
    private BufferedReader bf;

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
        UpdateGame update = new UpdateGame(bf, rope, this);
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
        Platform.runLater(()->{
            RestartWindowController restartWindowController = loader.getController();
            restartWindowController.SetWinLabel(winLoseInfo);
        } );
        

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
        stage.setTitle("Restart Window");
        stage.show();
    }

    @Override
    public void UpdateAmountOfLeftPlayers(int value)
    {
        UpdateAmountOfPlayers(value, teamLeftNumber);
    }

    @Override
    public void UpdateAmountOfRightPlayers(int value)
    {
        UpdateAmountOfPlayers(value, teamRightNumber);
    }

    private void UpdateAmountOfPlayers(int value, Label amountLabel)
    {
        String v = "" + value;
        amountLabel.setText(v);
    }
}
