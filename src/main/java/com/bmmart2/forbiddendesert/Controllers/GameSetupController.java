package com.bmmart2.forbiddendesert.Controllers;

import com.bmmart2.forbiddendesert.Components.Game;
import com.bmmart2.forbiddendesert.Player.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class GameSetupController {
    @FXML
    private Label countLabel;
    private ArrayList<Player> playerList;
    private HashSet<Player.PlayerTypes> toggled;

    private IntegerProperty count = new SimpleIntegerProperty(0);
    //TODO: Implement difficulty selection
    int difficulty;

    public GameSetupController() throws IOException {
        toggled = new HashSet<>();
    }


    public void initialize() {
        countLabel.textProperty().bind(count.asString());
    }

    public void buttonPressArchaeologist() {
        System.out.println("Toggled Archaeologist ");
        if (!toggled.add(Player.PlayerTypes.ARCHAEOLOGIST)) {
            toggled.removeIf(x -> x.equals(Player.PlayerTypes.ARCHAEOLOGIST));
        }
    }

    public void buttonPressWaterCarrier() {
        System.out.println("Toggled Water Carrier");
        if (!toggled.add(Player.PlayerTypes.WATER_CARRIER)) {
            toggled.removeIf(x -> x.equals(Player.PlayerTypes.WATER_CARRIER));
        }
    }

    public void buttonPressClimber() {
        System.out.println("Toggled Climber ");
        if (!toggled.add(Player.PlayerTypes.CLIMBER)) {
            toggled.removeIf(x -> x.equals(Player.PlayerTypes.CLIMBER));
        }
    }

    public void buttonPressNavigator() {
        System.out.println("Toggled Navigator ");
        if (!toggled.add(Player.PlayerTypes.NAVIGATOR)) {
            toggled.removeIf(x -> x.equals(Player.PlayerTypes.NAVIGATOR));
        }
    }

    public void buttonPressMeteorologist() {
        System.out.println("Toggled Meteorologist ");
        if (!toggled.add(Player.PlayerTypes.METEOROLOGIST)) {
            toggled.removeIf(x -> x.equals(Player.PlayerTypes.METEOROLOGIST));
        }
    }

    public void buttonPressExcavator() {
        System.out.println("Toggled Excavator ");
        if (!toggled.add(Player.PlayerTypes.EXCAVATOR)) {
            toggled.removeIf(x -> x.equals(Player.PlayerTypes.EXCAVATOR));
        }
    }

    public void submitOptions(ActionEvent e) {
        playerList = new ArrayList<>();
        for (Player.PlayerTypes p : toggled) {
            switch (p) {
                case ARCHAEOLOGIST:
                    playerList.add(new Archaeologist());
                    break;
                case CLIMBER:
                    playerList.add(new Climber());
                    break;
                case EXCAVATOR:
                    playerList.add(new Excavator());
                    break;
                case METEOROLOGIST:
                    playerList.add(new Meteorologist());
                    break;
                case NAVIGATOR:
                    playerList.add(new Navigator());
                    break;
                case WATER_CARRIER:
                    playerList.add(new WaterCarrier());
                    break;
                default:
                    throw new IllegalArgumentException("Invalid player class.");
            }
        }
        for (Player p : playerList) {
            System.out.println(p.getClass());
        }

        System.out.println("Instantiating game...");
        Game game = new Game(playerList, difficulty);
        System.out.println("Game instantiated!");

        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource("GameBoard.fxml")));
            Parent root = loader.load();
            GameBoardController controller = loader.getController();
            controller.setGame(game);
            controller.reloadBoard();
            Scene scene = new Scene(root);
            //This line gets the Stage Information
            Stage window=(Stage)((Node)e.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
