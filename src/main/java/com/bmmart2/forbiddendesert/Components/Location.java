package com.bmmart2.forbiddendesert.Components;

import javafx.scene.image.Image;

import java.io.File;
import java.util.LinkedList;
import java.util.Objects;

public class Location {

    /* Definition of tile configuration */
    public static int GEARTILES = 9;
    public static int WATERTILES = 2;
    public static int WATERFAKETILES = 1;
    public static int LANDINGPADTILES = 1;
    public static int ARTIFACTTILESETS = 4;
    public static int TUNNELTILES = 3;

    private String desc;
    private Image img;
    private LocationType type;

    private boolean isStartingLoc = false;


    protected Location() {
        desc = "";
    }

    protected Location(LocationType type) {
        switch (type) {
            case WELL:
                this.desc = "This tile contains water.";
                this.type = LocationType.WELL;
                break;
            case MIRAGE:
                this.desc = "This well has dried up.";
                this.type = LocationType.MIRAGE;
                break;
            case LANDINGPAD:
                this.desc = "A suitable place to launch a rocket.";
                this.type = LocationType.LANDINGPAD;
                break;
            case GEAR:
                this.desc = "An artifact buried by the sands.";
                this.type = LocationType.GEAR;
                break;
            case TUNNEL:
                this.desc = "A welcome relief from the scorching heat.";
                this.type = LocationType.TUNNEL;
                break;
            case STORM:
                this.desc = "A dangerous storm.";
                this.type = LocationType.STORM;
                break;
            default:
                throw new IllegalArgumentException("Please use a valid LocationType. Clues should be made as Clue() objects.");
        }
        this.img = ImagePacker.DEFAULT_IMG;
    }

    public void toggleStartingLoc() {
        isStartingLoc = true;
    }

    public boolean isStartingLoc() {
        return isStartingLoc;
    }

    protected Location withImg(Image img) {
        this.img = img;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) { this.img = img; }

    public LocationType getType() {
        return type;
    }

    protected void setType(LocationType lt) { type = lt; }

    //Generates an unshuffled list of locations specified by the constants
    protected static LinkedList<Location> generateLocations() {
        int i = 0;
        LinkedList<Location> list = new LinkedList<Location>();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        ImagePacker gearImgs = new ImagePacker();

        try {
            gearImgs.readDirectory(new File(Objects.requireNonNull(classLoader.getResource("assets/gear-tiles")).getPath()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (i = 0; i < GEARTILES; i++) {
            list.add(new Location(LocationType.GEAR).withImg(gearImgs.popImg()));
        }
        list.get((int)(Math.random()*list.size())).toggleStartingLoc();

        Image waterImg = new Image(Objects.requireNonNull(classLoader.getResource("assets/water/water-tile.jpg")).toString());
        for (i = 0; i < WATERTILES; i++) {
            list.add(new Location(LocationType.WELL).withImg(waterImg));
        }

        Image waterFakeImg = new Image(Objects.requireNonNull(classLoader.getResource("assets/water/water-fake-tile.jpg")).toString());
        for (i = 0; i < WATERFAKETILES; i++) {
            list.add(new Location(LocationType.MIRAGE).withImg(waterFakeImg));
        }

        //TODO: Finish images
        for (i = 0; i < LANDINGPADTILES; i++) {
            list.add(new Location(LocationType.LANDINGPAD).withImg(new Image(Objects.requireNonNull(classLoader.getResource("assets/other-tiles/landing-pad.jpg")).toString())));
        }
        for (i = 0; i < TUNNELTILES; i++) {
            list.add(new Location(LocationType.TUNNEL).withImg(new Image(Objects.requireNonNull(classLoader.getResource("assets/other-tiles/tunnel.jpg")).toString())));
        }
        list.add(new Clue(Artifact.COMPASS, Clue.Orientation.NS).withImg(new Image(Objects.requireNonNull(classLoader.getResource("assets/other-tiles/compass-UD.jpg")).toString())));
        list.add(new Clue(Artifact.COMPASS, Clue.Orientation.EW).withImg(new Image(Objects.requireNonNull(classLoader.getResource("assets/other-tiles/compass-LR.jpg")).toString())));
        list.add(new Clue(Artifact.PROPELLER, Clue.Orientation.NS).withImg(new Image(Objects.requireNonNull(classLoader.getResource("assets/other-tiles/propeller-UD.jpg")).toString())));
        list.add(new Clue(Artifact.PROPELLER, Clue.Orientation.EW).withImg(new Image(Objects.requireNonNull(classLoader.getResource("assets/other-tiles/propeller-LR.jpg")).toString())));
        list.add(new Clue(Artifact.ENGINE, Clue.Orientation.NS).withImg(new Image(Objects.requireNonNull(classLoader.getResource("assets/other-tiles/motor-UD.jpg")).toString())));
        list.add(new Clue(Artifact.ENGINE, Clue.Orientation.EW).withImg(new Image(Objects.requireNonNull(classLoader.getResource("assets/other-tiles/motor-LR.jpg")).toString())));
        list.add(new Clue(Artifact.CRYSTAL, Clue.Orientation.NS).withImg(new Image(Objects.requireNonNull(classLoader.getResource("assets/other-tiles/monolith-UD.jpg")).toString())));
        list.add(new Clue(Artifact.CRYSTAL, Clue.Orientation.EW).withImg(new Image(Objects.requireNonNull(classLoader.getResource("assets/other-tiles/monolith-LR.jpg")).toString())));

        return list;
    }
}
