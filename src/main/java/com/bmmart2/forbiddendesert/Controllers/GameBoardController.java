package com.bmmart2.forbiddendesert.Controllers;

import com.bmmart2.forbiddendesert.Components.Board;
import com.bmmart2.forbiddendesert.Components.Game;
import com.bmmart2.forbiddendesert.Player.Player;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class GameBoardController {

    @FXML
    private GridPane gameGridPane;

    private Game masterGame;


    protected void setGame(Game g) {
        masterGame = g;
    }


}
