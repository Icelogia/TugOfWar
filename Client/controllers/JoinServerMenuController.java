package Client.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Node;

public class JoinServerMenuController 
{
    private String serverIP = "localhost";
    private String port = "4444";
    private String nickname = "Player";

    private JoinServerMenuController mainController;

    @FXML
    private Pane joinServerMenuPane;

    @FXML
    private TextField nicknameField;

    @FXML
    private TextField serverIPField;

    @FXML
    private TextField portField;

    @FXML
    public void initialize()
    {
        mainController = this;
    }

    @FXML
    public void SetServerIP()
    {
        serverIP = serverIPField.getText();
    }

    @FXML
    public void SetServerPort()
    {
        port = portField.getText();
    }

    @FXML
    public void SetNickname()
    {
        nickname = nicknameField.getText();
    }

    @FXML
    public void JoinToServer(ActionEvent event)
    {
        var loader = new FXMLLoader(getClass().getResource("../view/GameWindow.fxml"));
        Pane pane = null;
        try 
        {
            pane = loader.load();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }

        GameWindowController gameController = loader.getController();

        gameController.SetMainController(mainController);
        //gameController.SetClientDirection();
        //gameController.SetClientSocket();

        var gameScene = new Scene(pane);
        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        appStage.hide();
        appStage.setScene(gameScene);
        appStage.show();
    }
}
