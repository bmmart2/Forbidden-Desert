package com.bmmart2.forbiddendesert.Components.Deck;

import com.bmmart2.forbiddendesert.Components.Artifact;
import com.bmmart2.forbiddendesert.Components.Location;

public class Clue extends Location {

    public enum Orientation { NS, EW }

    private Artifact item;
    private Orientation orientation;

    public Clue() {
        super();
    }



    public Artifact getItem() {
        return item;
    }

    public Orientation getOrientation() {
        return orientation;
    }

}
