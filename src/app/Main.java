package app;

import java.io.IOException;
import java.util.Objects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main Class
 */
public class Main extends Application {

    /**
     * Start application
     * @param primaryStage stage
     * @throws IOException exception
     */
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("start_screen.fxml")));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Graphic by Programistich");
        primaryStage.show();
    }

    /**
     * Start program
     * @param args arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}