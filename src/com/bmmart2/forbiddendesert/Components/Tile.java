package com.bmmart2.forbiddendesert.Components;

public class Tile {

    private Location loc;
    private boolean flipped;
    private int sand;
    private Artifact item;

    protected Tile() {
        flipped = false;
        sand = 0;
        item = Artifact.NULL;
    }

    protected Tile(Location l) {
        flipped = false;
        sand = 0;
        item = Artifact.NULL;
        loc = l;
    }

    public Location getLoc() {
        return loc;
    }

    public boolean isFlipped() {
        return flipped;
    }

    public int getSand() {
        return sand;
    }

    public Artifact getItem() {
        return item;
    }

    public boolean hasItem() {
        return !(item == Artifact.NULL);
    }

    public void dig() {
        if (sand == 0)
            return;
        sand--;
    }

    public void bury() {
        sand++;
    }

    public void flip() {
        if (flipped) {
            return;
        }
        flipped = true;
    }






}
