package com.bmmart2.forbiddendesert.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class MainMenuController {

    @FXML
    private Button startBtn;

    public void start(ActionEvent e) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../../../../../resources/GameSetup.fxml"));
            Scene scene = new Scene(root);
            //This line gets the Stage Information
            Stage window=(Stage)((Node)e.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void exit() {
        System.exit(1);
    }

}
