package Client.controllers;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class RestartWindowController 
{
    @FXML
    private Label winLabel;

    public void SetWinLabel(String winInfo)
    {
        winLabel.setText(winInfo);
    }

    @FXML
    public void Leave()
    {
        Platform.exit();
        System.exit(0);
    }
}
