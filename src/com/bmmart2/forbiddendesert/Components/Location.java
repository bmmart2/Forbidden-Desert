package com.bmmart2.forbiddendesert.Components;

import java.awt.image.BufferedImage;

public class Location {

    private String id;
    private String desc;
    private BufferedImage img;
    private LocationType type;

    public Location(String id, String desc, BufferedImage img, LocationType type) {
        this.id = id;
        this.desc = desc;
        this.img = img;
        this.type = type;
    }

    public String getId() {
        return id;
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
