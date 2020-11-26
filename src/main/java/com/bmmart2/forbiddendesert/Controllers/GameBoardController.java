package com.bmmart2.forbiddendesert.Controllers;

import com.bmmart2.forbiddendesert.Components.Board;
import com.bmmart2.forbiddendesert.Components.Game;
import com.bmmart2.forbiddendesert.Components.Tile;
import com.bmmart2.forbiddendesert.Player.Player;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class GameBoardController {

    @FXML
    private GridPane gameGridPane;

    private Game masterGame;
    private final ImageView[][] boardDisplay = new ImageView[5][5];

    public void initialize() {
        for (int m = 0; m < Board.m; m++) {
            for (int n = 0; n < Board.n; n++) {
                boardDisplay[m][n] = new ImageView();
                gameGridPane.add(boardDisplay[m][n], m, n);
            }
        }
    }

    protected void setGame(Game g) {
        masterGame = g;
    }

    protected void reloadBoard() {
        for (int m = 0; m < Board.m; m++) {
            for (int n = 0; n < Board.n; n++) {
                boardDisplay[m][n].setImage(masterGame.getBoard().getTile(m,n).getBackImg());
            }
        }
    }


}
