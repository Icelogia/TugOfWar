package Client.controllers;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Node;

public class JoinServerMenuController
{
    @FXML
    private TextField serverIPField;

    @FXML
    private TextField portField;

    @FXML
    private Text connectionText;

    private String serverIP = "localhost";
    private int port = 4444;

    @FXML
    public void SetServerIP()
    {
        serverIP = serverIPField.getText();
    }

    @FXML
    public void SetServerPort()
    {
        port = Integer.parseInt(portField.getText());
    }

    @FXML
    public void JoinToServer(ActionEvent event)
    {
        var loader = new FXMLLoader(getClass().getResource("../view/GameWindow.fxml"));
        boolean didSettingSucceeded = SetGameControllerSucceeeded(loader);

        if(didSettingSucceeded)
        {
            Pane pane = null;
            try 
            {
                pane = loader.load();
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
            
            ActiveGameScene(pane, event);
        }
    }

    private void ActiveGameScene(Pane pane, ActionEvent event) 
    {
        var scene = new Scene(pane);
        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        appStage.hide();
        appStage.setScene(scene);
        appStage.setTitle("Game Window");
        appStage.show();
    }

    private Socket GetClientSocket()
    {
        Socket socket = null;
        try 
        {
            socket = new Socket(serverIP, port);
            System.out.println(socket);
        } 
        catch (UnknownHostException e) 
        {
            System.out.println(connectionText);
            connectionText.setText("Couldn't Connect");
        } 
        catch (IOException e) 
        {
            System.out.println(connectionText);
            connectionText.setText("Couldn't Connect");
        }

        return socket;
    }
    
    private boolean SetGameControllerSucceeeded(FXMLLoader loader)
    {
        Socket socket = GetClientSocket();
        if(socket != null)
        {
            GameWindowController gameController = loader.getController();
            gameController.SetClientSocket(socket);
            return true;
        }
        else
        {
            return false;
        }
    }
}
