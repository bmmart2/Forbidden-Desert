package com.bmmart2.forbiddendesert.Components;

import com.bmmart2.forbiddendesert.Direction;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void moveStorm() {
        Board b = new Board();
        Tile t = new Tile();
        Tile t1 = new Tile();
        b.setStormLoc(new Point(2, 2));
        b.setTile(new Point(2,2), t);
        b.setTile(new Point(2, 3), t1);
        b.moveStorm(Direction.SOUTH);
        assertEquals(t, b.getTile(new Point(2,3)));
        b.moveStorm(Direction.NORTH);
        assertEquals(t1, b.getTile(new Point(2,3)));
        assertEquals(t, b.getTile(new Point(2,2)));
        Tile t3 = new Tile();
        b.setTile(new Point(3,2), t3);
        b.moveStorm(Direction.EAST);
        assertEquals(t3, b.getTile(new Point(2, 2)));
        assertTrue(b.getStormLoc().equals(new Point(3, 2)));
    }
}