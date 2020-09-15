package com.bmmart2.forbiddendesert.Components.Deck;

import com.bmmart2.forbiddendesert.Components.Deck.Card;
import com.bmmart2.forbiddendesert.Components.Deck.Deck;
import com.bmmart2.forbiddendesert.Components.Deck.GearCard;
import com.bmmart2.forbiddendesert.Components.Deck.StormCard;
import com.bmmart2.forbiddendesert.Components.Gear;
import com.bmmart2.forbiddendesert.Components.StormAction;
import com.bmmart2.forbiddendesert.Direction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    @Test
    void add() {
        Deck d = new Deck();
        d.add(new GearCard(Gear.SECRET_WATER_RESERVE));
        assertTrue(d.add(new GearCard(Gear.JET_PACK)));
        assertFalse(d.add(new StormCard(StormAction.STORM_PICKS_UP)));

        Deck d2 = new Deck();
        d2.add(new StormCard(StormAction.STORM_PICKS_UP));
        assertTrue(d2.add(new StormCard(StormAction.SUN_BEATS_DOWN)));
        assertFalse(d2.add(new GearCard(Gear.TIME_THROTTLE)));
    }

    @Test
    void drawTop() {
        Deck d = new Deck();
        d.add(new StormCard(StormAction.SUN_BEATS_DOWN));
        d.add(new StormCard(StormAction.STORM_PICKS_UP));
        d.add(new StormCard(StormAction.STORM_MOVE, Direction.EAST, 2));
        d.shuffle();
        Card c = d.drawTop();
        assertTrue(d.viewHistory().contains(c));
        assertFalse(d.contains(c));
    }

    @Test
    void undo() {
        Deck d = new Deck();
        d.add(new StormCard(StormAction.SUN_BEATS_DOWN));
        d.add(new StormCard(StormAction.STORM_PICKS_UP));
        d.add(new StormCard(StormAction.STORM_MOVE, Direction.EAST, 2));
        d.shuffle();
        Card c = d.drawTop();
        d.undo();
        assertFalse(d.viewHistory().contains(c));
        assertEquals(c, d.drawTop());

    }
}