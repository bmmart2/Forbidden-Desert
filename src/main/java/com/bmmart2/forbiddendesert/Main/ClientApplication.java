package com.bmmart2.forbiddendesert.Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.util.Objects;

public class ClientApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        System.out.println(new File("").getCanonicalPath());
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("MainMenu.fxml")));
        primaryStage.setTitle("Forbidden Desert");
        primaryStage.setScene(new Scene(root, 900,600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
