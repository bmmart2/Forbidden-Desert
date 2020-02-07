package com.bmmart2.forbiddendesert.Components;

import java.awt.geom.Point2D;

public class Board {

    private Tile[][] tiles;
    private Point2D storm = new Point2D.Double();
    private int maxSand;
    private int remainingSand;

    public Board() {
        storm.setLocation(0,0);
        maxSand = 0;
        remainingSand = 0;
    }

    public void init() {

    }
}
