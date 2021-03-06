package com.bmmart2.forbiddendesert.Components;

import com.bmmart2.forbiddendesert.Direction;
import javafx.geometry.Orientation;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

//TODO: write more unit tests!

class BoardTest {
    
    Location l = new Location();

    @Test
    void moveStorm() {
        Board b = new Board();
        Tile t = new Tile(l);
        Tile t1 = new Tile(l);

        //N and S coverage
        b.setStormLoc(new Point(2, 2));
        b.setTile(new Point(2,2), t);
        b.setTile(new Point(2, 3), t1);
        b.moveStorm(Direction.SOUTH);
        assertEquals(t, b.getTile(new Point(2,3)));
        assertEquals(new Point(2, 3), b.getStormLoc());
        b.moveStorm(Direction.NORTH);
        assertEquals(t1, b.getTile(new Point(2,3)));
        assertEquals(t, b.getTile(new Point(2,2)));

        //E and W coverage
        Tile t3 = new Tile(l);
        b.setTile(new Point(3,2), t3);
        b.moveStorm(Direction.EAST);
        assertEquals(t3, b.getTile(new Point(2, 2)));
        assertEquals(new Point(3, 2), b.getStormLoc());
        b.moveStorm(Direction.WEST);
        assertEquals(t3, b.getTile(new Point(3, 2)));
        assertEquals(new Point(2, 2), b.getStormLoc());
    }

    @Test
    void initTest() {
        Board b = new Board();
        assertFalse(b.isInitialized());
        b.init();
        assertTrue(b.isInitialized());

        HashMap<LocationType,Integer> ltmap = new HashMap<>();
        HashMap<Artifact, Integer> artifactmap = new HashMap<>();
        HashMap<Artifact, Clue.Orientation> omap = new HashMap<>();
        LocationType lt;
        Artifact a;

        b.dump();

        for (int i = 0; i < Board.m; i++) {
            for (int j = 0; j < Board.n; j++) {
                assertNotNull(b.getTile(i, j), "Tile does not exist at "+j+", "+i+" (X,Y std.)" );
                lt = b.getTile(i,j).getLoc().getType();
                ltmap.put(lt,ltmap.containsKey(lt) ? ltmap.get(lt)+1 : 1);
                if (b.getTile(i,j).getLoc() instanceof Clue) {
                    a = ((Clue)(b.getTile(i,j).getLoc())).getItem();
                    artifactmap.put(a, artifactmap.containsKey(a) ? artifactmap.get(a)+1 : 1);
                    if (omap.containsKey(a)) {
                        assertNotEquals(((Clue) (b.getTile(i, j).getLoc())).getOrientation(), omap.get(a), "Two of the same Clues and orientations detected!");
                    }
                    else {
                        omap.put(a,((Clue)(b.getTile(i,j).getLoc())).getOrientation());
                    }
                }
            }
        }
        assertEquals(Location.GEARTILES, ltmap.get(LocationType.GEAR),
                "Amt of gear tiles not equal to definition.");
        assertEquals(Location.ARTIFACTTILESETS*2, ltmap.get(LocationType.CLUE),
                "Amt of clue tiles not equal to definition.");
        assertEquals(Location.LANDINGPADTILES, ltmap.get(LocationType.LANDINGPAD),
                "Amt of landing pad tiles not equal to definition.");
        assertEquals(Location.TUNNELTILES, ltmap.get(LocationType.TUNNEL),
                "Amt of tunnel tiles not equal to definition.");
        assertEquals(Location.WATERTILES, ltmap.get(LocationType.WELL),
                "Amt of water tiles not equal to definition.");
        assertEquals(Location.WATERFAKETILES, ltmap.get(LocationType.MIRAGE),
                "Amt of mirage tiles not equal to definition.");

    }

    @Test
    //TODO: Write more exhaustive test cases
    void calculateIntersection() {
        Point2D ns = new Point(2,1);
        Point2D ew = new Point(0,3);
        assertEquals(new Point(2, 3), Board.calculateIntersection(ns, ew));
    }
}