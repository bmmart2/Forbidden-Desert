package com.bmmart2.forbiddendesert.Components;

import com.bmmart2.forbiddendesert.Components.Deck.Deck;
import com.bmmart2.forbiddendesert.Components.Deck.DeckCreator;
import com.bmmart2.forbiddendesert.Components.Deck.GearCard;
import com.bmmart2.forbiddendesert.Components.Deck.StormCard;
import com.bmmart2.forbiddendesert.Direction;
import com.bmmart2.forbiddendesert.Player.Climber;
import com.bmmart2.forbiddendesert.Player.Navigator;
import com.bmmart2.forbiddendesert.Player.Player;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Game {



    private Barometer barometer;
    private ArrayList<Player> players;
    private Board board;
    private HashMap<Artifact, Boolean> collectedArtifacts;
    private int activePlayer;
    private Deck stormdeck;
    private Deck geardeck;

    public int getPlayerCount() {
        return players.size();
    }

    public Board getBoard() {
        return board;
    }

    public Player getActivePlayer() {
        return players.get(activePlayer);
    }

    private Game() {
        players = new ArrayList<Player>();
        collectedArtifacts = new HashMap<>();
        board = new Board();
        board.init();
        stormdeck = DeckCreator.generateStormDeck();
        geardeck = DeckCreator.generateGearDeck();
    }

    public Game(ArrayList<Player> players, int difficulty) {
        this();
        this.players = players;
        barometer = new Barometer(players.size(), difficulty);
        Collections.shuffle(this.players);
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public boolean move(Direction d, Player p) {
        if ((d == Direction.SOUTHWEST || d == Direction.SOUTHEAST || d == Direction.NORTHWEST ||
                d == Direction.NORTHEAST) && !(p instanceof Navigator)) {
            return false;
        }
        int px = (int) p.getPoint().getX();
        int py = (int) p.getPoint().getY();
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
                newLoc.setLocation(px, py - 1);
                break;
            case SOUTH:
                newLoc.setLocation(px, py + 1);
                break;
            case EAST:
                newLoc.setLocation(px + 1, py);
                break;
            case WEST:
                newLoc.setLocation(px - 1, py);
                break;
            case NORTHWEST:
                newLoc.setLocation(px - 1, py - 1);
                break;
            case NORTHEAST:
                newLoc.setLocation(px + 1, py - 1);
                break;
            case SOUTHWEST:
                newLoc.setLocation(px - 1, py + 1);
                break;
            case SOUTHEAST:
                newLoc.setLocation(px + 1, py + 1);
                break;
            default:
                newLoc.setLocation(px, py);
        }
        if (newLoc.equals(board.getStormLoc()))
            return false;
        if (board.getTile(newLoc).getSand() >= 2 && !(p instanceof Climber))
            return false;
        p.hardMove(newLoc);
        p.decrementTurn();
        return true;
    }

    public boolean move(Direction d) {
        return move(d, getActivePlayer());
    }

    public boolean executeStormCard(StormCard sc) {
        if (sc.getAction() == StormAction.STORM_MOVE) {
            Point2D temp;
            for (int i = 0; i < sc.getMoveAmt(); i++) {
                temp = board.getStormLoc();
                board.moveStorm(sc.getDirection());
                for (Player p : players) {
                    if (p.getPoint().equals(board.getStormLoc())) {
                        p.hardMove(temp);
                    }
                }
                //drawBoard()
            }
        }
        else if (sc.getAction() == StormAction.SUN_BEATS_DOWN) {
            for (Player p : players) {
                if (!(p.isSolarShieldActive() || (board.getTile(p.getPoint()).getLoc().getType() == LocationType.TUNNEL
                        && board.getTile(p.getPoint()).isFlipped()))) {
                    p.drink();
                }
            }
        }
        else if (sc.getAction() == StormAction.STORM_PICKS_UP) {
            barometer.increase();
        }
        return true;
    }

    public void useTunnel(int index) {
        if (!getActivePlayer().hasTurnLeft()) {
            return;
        }
        if (!board.getTile(getActivePlayer().getPoint()).isFlipped()) {
            throw new IllegalStateException("Tile must be flipped to use.");
        }
        if (board.getTunnels().size() <= index) {
            throw new IllegalArgumentException("Index of desired tunnel cannot be greater than amount of tunnels.");
        }
        if (board.getTile(getActivePlayer().getPoint()).getLoc().getType() == LocationType.TUNNEL) {
          getActivePlayer().hardMove(board.getTunnels(index));
          getActivePlayer().decrementTurn();
        }
    }

    public void flipTile() {
        if (!getActivePlayer().hasTurnLeft()) {
            return;
        }
        if (board.getTile(getActivePlayer().getPoint()).isFlipped())
            throw new IllegalStateException("Tile already flipped.");

        Tile t = board.getTile(getActivePlayer().getPoint());
        t.flip();
        //TODO: display some sort of display for flipped card
        switch (t.getLoc().getType()) {
            case GEAR:
                GearCard g = (GearCard) geardeck.drawTop();
                getActivePlayer().addGearCard(g);
                break;
            case WELL:
                for (Player p : players) {
                    if (p.getPoint().equals(getActivePlayer().getPoint())) {
                        p.addWater(2);
                    }
                }
                break;
            case TUNNEL:
                board.addTunnel(getActivePlayer().getPoint());
                break;
            case CLUE:
                board.recordClue((Clue) t.getLoc());
                break;
            default:
                return;
        }
    }

    public void endTurn() {
        drawStormCards();
        getActivePlayer().setTurn(Player.MAX_TURN);
        if (activePlayer >= players.size()) {
            activePlayer = 0;
        }
        else {
            activePlayer++;
        }
    }

    public void drawStormCards() {
        int drawAmt = barometer.getDrawAmt(); //ensures drawAmt doesnt increase this turn if storm picks up is drawn
        for (int i = 0; i < drawAmt; i++) {
            StormCard sc = (StormCard)stormdeck.drawTop();
            executeStormCard(sc);
        }
    }

}
