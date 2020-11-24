package com.bmmart2.forbiddendesert.Components;

import com.bmmart2.forbiddendesert.Direction;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.*;

import static com.bmmart2.forbiddendesert.Components.Artifact.*;
import static com.bmmart2.forbiddendesert.Components.LocationType.TUNNEL;

public class Board {

    /*
    Standards on coordinate references:

        All public functions returning and/or using as inputs such as
        Point2Ds, X's, or Y's referring to a coordinate of some tile on
        the board are referenced in standard (X,Y) format. The board
        takes place in Quadrant-IV. X grows from left to right, while
         Y grows from top to bottom.
     */

    //must be set to 5
    public static final int n = 5; //x
    public static final int m = 5; //y
    final int maxSand = 48;

    private Tile[][] tiles;

    private Point2D storm = new Point2D.Double();
    private ArrayList<Point2D> tunnels = new ArrayList<>();
    private HashMap<Artifact, Clue> locatedClues;
    private int remainingSand;
    private boolean initialized = false;

    public Board() {
        storm.setLocation(0,0);
        remainingSand = maxSand;
        locatedClues = new HashMap<>();
        tiles = new Tile[m][n];
    }

    protected void setStormLoc(Point2D p) {
        storm = p;
    }

    public Point2D getStormLoc() {
        return (Point2D) storm.clone();
    }

    private HashMap<Artifact, Clue> getLocatedClues() { return locatedClues; }

    public void recordClue(Clue c) {
        if (c.getItem() == NULL)
            throw new IllegalArgumentException("ERROR: Clue has no item.");
        if (locatedClues.containsKey(c.getItem())) {
            if (locatedClues.get(c.getItem()).equals(c)) {
                throw new IllegalStateException("ERROR: Clue array duplicate.");
            }
            //both orientations of clue discovered, spawn item
            Point2D intersection = (c.getOrientation().equals(Clue.Orientation.NS) ?
                    calculateIntersection(search(c), search(locatedClues.get(c.getItem()))) :
                    calculateIntersection(search(locatedClues.get(c.getItem())), search(c)));
            getTile(intersection).setItem(c.getItem());
        }
        else {
            locatedClues.put(c.getItem(), c);
        }
    }

    public boolean isInitialized() {
        return initialized;
    }

    //TODO: Allow ability to resize board before generation
    //Initializes the board. Currently only works for 5x5 sized games.
    protected void init() {

        if (initialized) {
            System.out.println("Board already initialized.");
            return;
        }
        initialized = true;
        LinkedList<Location> locations = Location.generateLocations();
        Collections.shuffle(locations);

        storm.setLocation(n/2, m/2);
        for (int y = 0; y < m; y++) {
            for (int x = 0; x < n; x++) {
                if (x == storm.getX() && y == storm.getY()) {
                    tiles[y][x] = new Tile(new Location(LocationType.STORM));
                }
                else {
                    tiles[y][x] = new Tile(locations.pollFirst());
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

    protected Tile setTile(Point2D p, Tile t) {
        if (p.getX() >= n || p.getY() >= m || p.getX() < 0 || p.getY() < 0)
            throw new ArrayIndexOutOfBoundsException("ERROR: Invalid point.");
        this.tiles[(int)p.getY()][(int)p.getX()] = t;
        return t;
    }

    public Tile getTile(Point2D point2D) {
        if (point2D.getX() >= n || point2D.getY() >= m || point2D.getX() < 0 || point2D.getY() < 0)
            throw new ArrayIndexOutOfBoundsException("ERROR: Invalid point.");
        return tiles[(int)point2D.getY()][(int)point2D.getX()];
    }

    public Tile getTile(int x, int y) {
        Point2D point = new Point(x,y);
        return getTile(point);
    }

    public Tile getTile(Point2D point, Direction d) {
        switch (d) {
            case NORTH:
                point.setLocation(point.getX(), point.getY()-1);
                break;
            case SOUTH:
                point.setLocation(point.getX(), point.getY()+1);
                break;
            case EAST:
                point.setLocation(point.getX()+1, point.getY());
                break;
            case WEST:
                point.setLocation(point.getX()-1, point.getY());
                break;
            case NORTHEAST:
                point.setLocation(point.getX()+1, point.getY()-1);
                break;
            case NORTHWEST:
                point.setLocation(point.getX()-1, point.getY()-1);
                break;
            case SOUTHEAST:
                point.setLocation(point.getX()+1, point.getY()+1);
                break;
            case SOUTHWEST:
                point.setLocation(point.getX()-1, point.getY()+1);
            default:
                throw new IllegalArgumentException("Error: Not valid direction");
        }
        return getTile(point);
    }

    public ArrayList<Point2D> getTunnels() {
        return tunnels;
    }

    protected void addTunnel(Point2D point2D) {
        Tile t = getTile(point2D);
        if (t.getLoc().getType() != TUNNEL)
            throw new IllegalArgumentException("UNKNOWN: Tile is not a tunnel!");
        tunnels.add(point2D);
    }

    public Point2D getTunnels(int index) {
        if (index >= tunnels.size()) {
            return null;
        }
        else {
            return tunnels.get(index);
        }
    }

    protected boolean moveStorm(Direction d) {
        switch (d) {
            case SOUTH:
                if (!(storm.getY() == m - 1)) {
                    swapTiles(storm, new Point((int)storm.getX(), (int)storm.getY()+1));
                    bury(storm);
                    storm.setLocation(storm.getX(), storm.getY()+1);
                }
                break;
            case NORTH:
                if (!(storm.getY() == 0)) {
                    swapTiles(storm, new Point((int)storm.getX(), (int)storm.getY()-1));
                    bury(storm);
                    storm.setLocation(storm.getX(), storm.getY()-1);
                }
                break;
            case EAST:
                if (!(storm.getX() == n - 1)) {
                    swapTiles(storm, new Point((int)storm.getX()+1, (int)storm.getY()));
                    bury(storm);
                    storm.setLocation(storm.getX()+1, storm.getY());
                }
                break;
            case WEST:
                if (!(storm.getX() == 0)) {
                    swapTiles(storm, new Point((int) storm.getX() - 1, (int) storm.getY()));
                    bury(storm);
                    storm.setLocation(storm.getX() - 1, storm.getY());
                }
                break;
            default:
                throw new IllegalArgumentException("Direction must be cardinal.");
        }
        return true;
    }

    protected void dig(Point2D p) {
        getTile(p).dig();
        remainingSand++;
    }

    protected void bury(Point2D p) {
        getTile(p).bury();
        remainingSand--;
    }

    protected void dig(Tile t) {
        t.dig();
        remainingSand++;
    }

    protected void bury(Tile t) {
        t.bury();
        remainingSand--;
    }

    public void dump() {
        if (!this.initialized)
            return;
        System.out.println("--BOARD DUMP BEGIN--\n");
        System.out.println("Storm Loc = " + storm.toString());
        System.out.println("Remaining Sand = " + this.remainingSand +"/"+this.maxSand);
        for (int i = 0; i < Board.m; i++) {
            System.out.println("[Y] = "+i);
            for (int j = 0; j < Board.n; j++) {
                System.out.printf("\t [X] = %d \n",j);
                System.out.printf("\t\t LocationType = ");
                System.out.println(this.tiles[i][j].getLoc().getType());
                System.out.println("\t\t Sand = " + this.tiles[i][j].getSand());
                System.out.println("\t\t Item = " + (this.getTile(i,j).hasItem() ? this.getTile(i,j).getItem() : "null"));
            }
        }
        System.out.println("--BOARD DUMP END--");
    }

    protected Point2D search(Location location) {
        for (int y = 0; y < m; y++) {
            for (int x = 0; x < n; x++) {
                if (getTile(x, y).getLoc() == location) {
                    return new Point(x,y);
                }
            }
        }
        return null;
    }

    protected static Point2D calculateIntersection(Point2D ns, Point2D ew) {
        return new Point((int)ns.getX(),(int)ew.getY());
    }

    private void swapTiles(Point2D p1, Point2D p2) {
        Tile temp = tiles[(int)p1.getY()][(int)p1.getX()];
        tiles[(int)p1.getY()][(int)p1.getX()] = tiles[(int)p2.getY()][(int)p2.getX()];
        tiles[(int)p2.getY()][(int)p2.getX()] = temp;
    }

}
