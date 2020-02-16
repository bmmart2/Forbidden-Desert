package com.bmmart2.forbiddendesert.Components.Deck;

import java.math.*;
import java.awt.image.BufferedImage;

abstract class Card {
    protected double id = Math.random() * 1000 + 1;
    protected String desc;
    protected BufferedImage img;

/*    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!Card.class.isAssignableFrom(obj.getClass()))
            return false;
        final Card other = (Card)obj;
        if (this.id == other.id)
            return true;
        return false;
    }*/
}
