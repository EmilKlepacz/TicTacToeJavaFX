package game;

import game.controllers.GameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("board.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Tick Tac Toe");
        primaryStage.setScene(new Scene(root));

        GameController gameController = loader.getController();

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
