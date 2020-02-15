package com.bmmart2.forbiddendesert.Player;

import com.bmmart2.forbiddendesert.Components.Deck.Deck;
import com.bmmart2.forbiddendesert.Components.Deck.DeckCreator;
import com.bmmart2.forbiddendesert.Components.Deck.GearCard;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @org.junit.jupiter.api.Test
    void drink() {
    }

    @org.junit.jupiter.api.Test
    void passGearCard() {
        Deck dg = DeckCreator.generateGearDeck();
        Player p1 = new Player("p1");
        Player p2 = new Player("p2");
        GearCard gc = (GearCard)dg.drawTop();
        p1.addGearCard(gc);
        p1.passGearCard(p2, p1.getGearAt(0));
        assertEquals(gc, p2.getGearAt(0));
    }

    @org.junit.jupiter.api.Test
    void hasGearCard() {
    }
}