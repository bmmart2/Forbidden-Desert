package com.bmmart2.forbiddendesert.Player;

import com.bmmart2.forbiddendesert.Components.Deck.Deck;
import com.bmmart2.forbiddendesert.Components.Deck.DeckCreator;
import com.bmmart2.forbiddendesert.Components.Deck.GearCard;
import com.bmmart2.forbiddendesert.Components.Gear;

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
        assertFalse(p1.hasGearCard(gc));
    }

    @org.junit.jupiter.api.Test
    void hasGearCard() {
        Player p1 = new Player("p1");
        GearCard gc = new GearCard(Gear.DUNE_BLASTER);
        p1.addGearCard(gc);
        GearCard gc2 = new GearCard(Gear.DUNE_BLASTER);
        assertFalse(p1.hasGearCard(gc2));
        assertTrue(p1.hasGearCard(gc));
    }
}