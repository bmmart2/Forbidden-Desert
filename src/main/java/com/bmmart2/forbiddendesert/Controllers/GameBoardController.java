package com.bmmart2.forbiddendesert.Controllers;

import com.bmmart2.forbiddendesert.Components.Board;
import com.bmmart2.forbiddendesert.Components.Game;
import com.bmmart2.forbiddendesert.Components.Tile;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class GameBoardController {

    public static final int WIDTH_HEIGHT = 120;

    @FXML
    private GridPane gameGridPane;

    private Game masterGame;
    private final ImageView[][] boardDisplay = new ImageView[5][5];
    private Tile storm;

    public void initialize() {
        for (int m = 0; m < Board.m; m++) {
            for (int n = 0; n < Board.n; n++) {
                boardDisplay[m][n] = new ImageView();
                boardDisplay[m][n].setFitWidth(WIDTH_HEIGHT);
                boardDisplay[m][n].setFitHeight(WIDTH_HEIGHT);
                boardDisplay[m][n].setPreserveRatio(true);
                gameGridPane.add(boardDisplay[m][n], m, n);
            }
        }
    }

    protected void setGame(Game g) {
        masterGame = g;
        Tile storm = masterGame.getBoard().getTile(2,2);
    }

    protected void reloadBoard() {
        for (int m = 0; m < Board.m; m++) {
            for (int n = 0; n < Board.n; n++) {
                if (masterGame.getBoard().getTile(m,n).isFlipped()) {
                    boardDisplay[m][n].setImage(masterGame.getBoard().getTile(m,n).getLoc().getImg());
                }
                else {
                    boardDisplay[m][n].setImage(masterGame.getBoard().getTile(m,n).getBackImg());
                }
            }
        }
    }


}
