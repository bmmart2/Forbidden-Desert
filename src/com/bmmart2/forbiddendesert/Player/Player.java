package com.bmmart2.forbiddendesert.Player;

import com.bmmart2.forbiddendesert.Components.Artifact;
import com.bmmart2.forbiddendesert.Components.Board;
import com.bmmart2.forbiddendesert.Components.Deck.GearCard;
import com.bmmart2.forbiddendesert.Direction;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.LinkedList;

public class Player {

    public final static int MAX_TURN = 4;

    //private int id;
    private String name;
    private Point2D loc;
    private LinkedList<GearCard> gear = new LinkedList<GearCard>();
    private boolean solarShieldActive;
    private int canteenSize;
    private int canteenContents;
    private int turn;
    private boolean isInitialized;

    protected Player() {
        name = "";
        loc = new Point(0,0);
        isInitialized = false;
        turn = MAX_TURN;
        solarShieldActive = false;
    }

    public Player(String name) {
        this();
        this.name = name;
    }

    public Point2D getPoint() {
        return loc;
    }

    public int getTurn() {
        return turn;
    }

    public boolean hasTurnLeft() {
        return turn > 0;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public void decrementTurn() {
        this.turn--;
    }

    public void hardMove(Point2D p) {
        loc = p;
    }

    public void hardMove(int x, int y) {
        loc.setLocation(x, y);
    }

    public void drink() {
        canteenContents--;
    }

    public void printGear() {
        System.out.println(gear.toString());
    }

    public boolean isSolarShieldActive() {
        return solarShieldActive;
    }

    public GearCard getGearAt(int pos) {
        if (pos >= gear.size())
            return null;
        return gear.get(pos);
    }

    public boolean passGearCard(Player p, GearCard g) {
        if (this.loc.equals(p.loc)) {
            if (this.gear.contains(g)) {
                p.gear.add(this.gear.remove());
                return true;
            }
        }
        return false;
    }

    public void addGearCard(GearCard g) {
        this.gear.addLast(g);
    }

    public boolean hasGearCard(GearCard a) {
        return gear.contains(a);
    }

    /*@Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!Player.class.isAssignableFrom(obj.getClass()))
            return false;
        final Player other = (Player) obj;
        if (this.name.equals(other.name) && this.id == other.id)
            return true;
        return false;
    }*/
}
