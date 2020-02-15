package com.bmmart2.forbiddendesert;

import com.bmmart2.forbiddendesert.Components.Artifact;
import com.bmmart2.forbiddendesert.Components.Board;
import com.bmmart2.forbiddendesert.Player.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {

    private ArrayList<Player> players = new ArrayList<Player>();
    private Board board = new Board();
    private HashMap<Artifact,Boolean> collectedArtifacts = new HashMap<>();
    private int activePlayer;

    public int getPlayerCount() {
        return players.size();
    }

    public Player getActivePlayer() {
        return players.get(activePlayer);
    }



}
