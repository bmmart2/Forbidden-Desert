package com.bmmart2.forbiddendesert.Components;

import com.bmmart2.forbiddendesert.Components.Artifact;
import com.bmmart2.forbiddendesert.Components.Board;
import com.bmmart2.forbiddendesert.Direction;
import com.bmmart2.forbiddendesert.Player.Navigator;
import com.bmmart2.forbiddendesert.Player.Player;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;

import static com.bmmart2.forbiddendesert.Direction.SOUTH;

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

    public boolean move(Direction d, Player p) {
        if ((d == Direction.SOUTHWEST || d == Direction.SOUTHEAST || d == Direction.NORTHWEST ||
                d == Direction.NORTHEAST) && !(p instanceof Navigator)) {
            return false;
        }
        int px = (int) p.getLoc().getX();
        int py = (int) p.getLoc().getY();
        if (px == Board.n - 1) {
            if (d == Direction.EAST || d == Direction.NORTHEAST || d == Direction.SOUTHEAST) {
                return false;
            }
        }
        else if (px == 0) {
            if (d == Direction.WEST || d == Direction.NORTHWEST || d == Direction.SOUTHWEST) {
                return false;
            }
        }
        if (py == Board.m - 1) {
            if (d == Direction.NORTH || d == Direction.NORTHWEST || d == Direction.NORTHEAST) {
                return false;
            }
        }
        else if (py == 0) {
            if (d == Direction.SOUTH || d == Direction.SOUTHWEST || d == Direction.SOUTHEAST) {
                return false;
            }
        }
        Point2D newLoc = new Point();
        switch (d) {
            case NORTH:
                newLoc.setLocation(px, py + 1);
                break;
            case SOUTH:
                newLoc.setLocation(px, py - 1);
                break;
            case EAST:
                newLoc.setLocation(px + 1, py);
                break;
            case WEST:
                newLoc.setLocation(px - 1, py);
                break;
            case NORTHWEST:
                newLoc.setLocation(px - 1, py + 1);
                break;
            case NORTHEAST:
                newLoc.setLocation(px + 1, py + 1);
                break;
            case SOUTHWEST:
                newLoc.setLocation(px - 1, py - 1);
                break;
            case SOUTHEAST:
                newLoc.setLocation(px + 1, py - 1);
                break;
            default:
                newLoc.setLocation(px, py);
        }
        if (newLoc.equals(board.getStormLoc()))
            return false;
        if (board.getTile(newLoc).getSand() >= 2)
            return false;
        p.hardMove(newLoc);
        return true;
    }

}
