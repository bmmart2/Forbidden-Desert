package com.bmmart2.forbiddendesert.Components;

import javafx.scene.image.Image;

public class Tile {

    private static final Image normalBackImg = new Image(Thread.currentThread().getContextClassLoader().getResource("assets/board-piece-hidden.jpg").toString());
    private static final Image waterBackImg = new Image(Thread.currentThread().getContextClassLoader().getResource("assets/water/board-piece-hidden-water.jpg").toString());
    private static final Image startingBackImg = new Image(Thread.currentThread().getContextClassLoader().getResource("assets/starting-tile.jpg").toString());

    private Location loc;
    private boolean flipped;
    private int sand;
    private Artifact item;
    private Image backImg;

    private Tile() {
        flipped = false;
        sand = 0;
        item = Artifact.NULL;
        backImg = normalBackImg;
    }

    protected Tile(Location l) {
        this();
        loc = l;
        if (l.getType() == LocationType.MIRAGE || l.getType() == LocationType.WELL)
            backImg = waterBackImg;
        else if (l.isStartingLoc())
            backImg = startingBackImg;
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

    protected void setItem(Artifact a) { item = a; }

    public boolean hasItem() {
        return !(item == Artifact.NULL);
    }

    public Image getBackImg() {
        return backImg;
    }

    protected void dig() {
        if (sand == 0)
            return;
        sand--;
    }

    protected void bury() {
        sand++;
    }

    protected void flip() {
        if (flipped) {
            return;
        }
        flipped = true;
    }

    public static void generateTileSet() {

    }




}
