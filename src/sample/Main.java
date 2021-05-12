package sample;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Graphic by Programistich");
        primaryStage.setWidth(500);
        primaryStage.setHeight(400);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
