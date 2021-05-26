package Client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TugOfWarClient extends Application
{
    public static void main(String[] args) 
    {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        var loader = new FXMLLoader(this.getClass().getResource("view/JoinServerMenu.fxml"));
        Pane pane = loader.load();
        var scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JoinWindow");
        primaryStage.show();
    }
}
