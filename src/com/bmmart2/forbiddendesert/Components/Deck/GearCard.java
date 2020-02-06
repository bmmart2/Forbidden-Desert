package com.bmmart2.forbiddendesert.Components.Deck;

import com.bmmart2.forbiddendesert.Components.Gear;

public class GearCard extends Card {

    private Gear item;

    public GearCard(Gear g) {
        item = g;
        // TODO: code img and description injection for gear cards

    }

    @Override
    // Executed when card is drawn from the deck.
    public void draw() {

    }

}
