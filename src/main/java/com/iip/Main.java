package com.iip;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
//        System.out.println(getClass().getResource("view/Login.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/com/iip/ui/space_time/view/Login.fxml"));
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);


        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
