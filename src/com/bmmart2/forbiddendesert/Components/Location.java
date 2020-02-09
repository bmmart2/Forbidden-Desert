package com.bmmart2.forbiddendesert.Components;

import java.awt.image.BufferedImage;

public class Location {

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
}
