package com.bmmart2.forbiddendesert.Components;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Board {

    //must be set to 5
    final int n = 5;
    final int m = 5;
    final int maxSand = 48;

    private Tile[][] tiles;
    private Point2D storm = new Point2D.Double();
    private Point2D landingPad = new Point2D.Double();
    private int remainingSand;
    private boolean initialized = false;


    public Board() {
        storm.setLocation(0,0);
        remainingSand = maxSand;
    }

    //TODO: Allow ability to resize board before generation
    //Initializes the board. Currently only works for 5x5 sized games.
    public void init() {

        if (initialized) {
            System.out.println("Board already initialized.");
            return;
        }
        initialized = true;
        tiles = new Tile[n][m];
        LinkedList<Location> locations = Location.generateLocations();
        Collections.shuffle(locations);

        storm.setLocation(n/2, m/2);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (j == storm.getX() && i == storm.getY()) {
                    tiles[i][j] = new Tile(new Location(LocationType.STORM));
                }
                else {
                    if (locations.peekFirst().getType().equals(LocationType.LANDINGPAD))
                        landingPad.setLocation(j, i);
                    tiles[i][j] = new Tile(locations.pollFirst());
                }
            }
        }

        //TODO: Generalize the diamond bury pattern
        bury(tiles[0][2]);
        bury(tiles[1][1]);
        bury(tiles[1][3]);
        bury(tiles[2][4]);
        bury(tiles[2][0]);
        bury(tiles[3][1]);
        bury(tiles[3][3]);
        bury(tiles[4][2]);
    }

    public Tile getTile(Point2D point2D) {
        return tiles[(int)point2D.getY()][(int)point2D.getX()];
    }

    public void dig(Point2D p) {
        getTile(p).dig();
        remainingSand++;
    }

    public void bury(Point2D p) {
        getTile(p).bury();
        remainingSand--;
    }

    public void dig(Tile t) {
        t.dig();
        remainingSand++;
    }

    public void bury(Tile t) {
        t.bury();
        remainingSand--;
    }
}
