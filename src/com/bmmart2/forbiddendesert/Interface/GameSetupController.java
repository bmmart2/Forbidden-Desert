package com.bmmart2.forbiddendesert.Interface;

import com.bmmart2.forbiddendesert.Player.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class GameSetupController implements Initializable {
    @FXML
    private Label countLabel;
    private List<Player> playerList;
    private HashSet<Player.PlayerTypes> toggled;

    private IntegerProperty count = new SimpleIntegerProperty(0);

    public GameSetupController() throws IOException {
        toggled = new HashSet<>();
    }

    @Override
    public void initialize(URL loc, ResourceBundle res) {
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

    public void submitOptions() {
        playerList = new ArrayList<>();
        for (Player.PlayerTypes p : toggled) {
            switch (p) {
                case ARCHAEOLOGIST -> playerList.add(new Archaeologist());
                case CLIMBER -> playerList.add(new Climber());
                case EXCAVATOR -> playerList.add(new Excavator());
                case METEOROLOGIST -> playerList.add(new Meteorologist());
                case NAVIGATOR -> playerList.add(new Navigator());
                case WATER_CARRIER -> playerList.add(new WaterCarrier());
                default -> throw new IllegalArgumentException("Invalid player class.");
            }
        }
        for (Player p : playerList) {
            System.out.println(p.getClass());
        }
    }
}
