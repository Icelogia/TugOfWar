package Client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
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
        StackPane stackPane = new StackPane();
        Button joinButton = new Button("Join");

        stackPane.getChildren().add(joinButton);

        var scene = new Scene(stackPane, 400, 600);

        primaryStage.setScene(scene);
        primaryStage.setTitle("JoinWindow");
        primaryStage.show();
    }
}
