package com.bmmart2.forbiddendesert.Components.Deck;

import com.bmmart2.forbiddendesert.Components.Artifact;
import com.bmmart2.forbiddendesert.Components.Location;
import com.bmmart2.forbiddendesert.Components.LocationType;

public class Clue extends Location {

    public static int artifactCount = 4;
    public enum Orientation { NS, EW }

    private Artifact item;
    private Orientation orientation;

    public Clue(Artifact a, Orientation o) {
        super(LocationType.CLUE);
        this.item = a;
        this.orientation = o;
    }



    public Artifact getItem() {
        return item;
    }

    public Orientation getOrientation() {
        return orientation;
    }

}
