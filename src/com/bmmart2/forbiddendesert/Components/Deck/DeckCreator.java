package com.bmmart2.forbiddendesert.Components.Deck;

import com.bmmart2.forbiddendesert.Components.StormAction;

public class DeckCreator {


    public static Deck generateStormDeck() {
        Deck d = new Deck();
        d.add(new StormCard(StormAction.STORM_PICKS_UP));
        d.add(new StormCard(StormAction.STORM_PICKS_UP));
        d.add(new StormCard(StormAction.STORM_PICKS_UP));
        d.add(new StormCard(StormAction.SUN_BEATS_DOWN));
        d.add(new StormCard(StormAction.SUN_BEATS_DOWN));
        d.add(new StormCard(StormAction.SUN_BEATS_DOWN));
        d.add(new StormCard(StormAction.SUN_BEATS_DOWN));
        //etc


        return d;
    }

    public static Deck generateGearDeck() {
        Deck d = new Deck();



        return d;
    }

}
