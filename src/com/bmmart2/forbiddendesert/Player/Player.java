package com.bmmart2.forbiddendesert.Player;

import com.bmmart2.forbiddendesert.Components.Artifact;
import com.bmmart2.forbiddendesert.Components.Deck.GearCard;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.LinkedList;

public class Player {

    //private int id;
    private String name;
    private Point2D loc;
    private LinkedList<GearCard> gear = new LinkedList<GearCard>();
    private int canteenSize;
    private int canteenContents;
    private boolean isInitialized = false;

    public Player(String name) {
        this.name = name;
        loc = new Point(0,0);
    }

    public void hardMove(Point p) {
        loc = p;
    }

    public void drink() {
        canteenContents--;
    }

    public void printGear() {
        System.out.println(gear.toString());
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
