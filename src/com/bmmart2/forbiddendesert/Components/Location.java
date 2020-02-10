package com.bmmart2.forbiddendesert.Components;

import com.bmmart2.forbiddendesert.Components.Deck.Clue;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class Location {

    public static int GEARTILES = 12;
    public static int WATERTILES = 2;
    public static int WATERFAKETILES = 1;
    public static int LANDINGPADTILES = 1;
    public static int ARTIFACTTILESETS = 4;
    public static int TUNNELTILES = 3;

    private String desc;
    private BufferedImage img;
    private LocationType type;

    protected Location() {
        desc = "";
    }

    protected Location(LocationType type) {
        switch (type) {
            case WELL:
                //TODO this.img = img;
                this.desc = "This tile contains water.";
                this.type = LocationType.WELL;
                break;
            case WELLFAKE:
                //this.img = img;
                this.desc = "This well has dried up.";
                this.type = LocationType.WELLFAKE;
                break;
            case LANDINGPAD:
                //this.img = img;
                this.desc = "A suitable place to launch a rocket.";
                this.type = LocationType.LANDINGPAD;
                break;
            case GEAR:
                //TODO: this.img = Stack<Img>.pop() or something
                this.desc = "An artifact buried by the sands.";
                this.type = LocationType.GEAR;
                break;
            case SHELTER:
                //this.img = img;
                this.desc = "A welcome relief from the scorching heat.";
                this.type = LocationType.SHELTER;
                break;
            case STORM:
                //this.img = img;
                this.desc = "A dangerous storm.";
                this.type = LocationType.STORM;
                break;
            default:
                throw new IllegalArgumentException("Please use a valid LocationType. Clues should be made as Clue() objects.");
        }
    }

    public String getDesc() {
        return desc;
    }

    public BufferedImage getImg() {
        return img;
    }

    public LocationType getType() {
        return type;
    }


    //Generates an unshuffled list of locations specified by the constants
    protected static LinkedList<Location> generateLocations() {
        int i = 0;
        LinkedList<Location> list = new LinkedList<Location>();

        for (i = 0; i < GEARTILES; i++) {
            list.add(new Location(LocationType.GEAR));
        }
        for (i = 0; i < WATERTILES; i++) {
            list.add(new Location(LocationType.WELL));
        }
        for (i = 0; i < WATERFAKETILES; i++) {
            list.add(new Location(LocationType.WELLFAKE));
        }
        for (i = 0; i < LANDINGPADTILES; i++) {
            list.add(new Location(LocationType.LANDINGPAD));
        }
        for (i = 0; i < TUNNELTILES; i++) {
            list.add(new Location(LocationType.SHELTER));
        }
        list.add(new Clue(Artifact.COMPASS, Clue.Orientation.NS));
        list.add(new Clue(Artifact.COMPASS, Clue.Orientation.EW));
        list.add(new Clue(Artifact.PROPELLER, Clue.Orientation.NS));
        list.add(new Clue(Artifact.PROPELLER, Clue.Orientation.EW));
        list.add(new Clue(Artifact.ENGINE, Clue.Orientation.NS));
        list.add(new Clue(Artifact.ENGINE, Clue.Orientation.EW));
        list.add(new Clue(Artifact.CRYSTAL, Clue.Orientation.NS));
        list.add(new Clue(Artifact.CRYSTAL, Clue.Orientation.EW));

        return list;
    }
}
