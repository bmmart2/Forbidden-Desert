package com.bmmart2.forbiddendesert.Components;

import com.bmmart2.forbiddendesert.Components.Artifact;
import com.bmmart2.forbiddendesert.Components.Location;
import com.bmmart2.forbiddendesert.Components.LocationType;

public class Clue extends Location {

    public static int artifactCount = 4;
    public enum Orientation { NS, EW }

    private Artifact item;
    private Orientation orientation;

    public Clue(Artifact a, Orientation o) {
        this.setType(LocationType.CLUE);
        this.item = a;
        this.orientation = o;
    }



    public Artifact getItem() {
        return item;
    }

    public Clue.Orientation getOrientation() {
        return orientation;
    }

}
