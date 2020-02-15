package com.bmmart2.forbiddendesert.Components.Deck;

import com.bmmart2.forbiddendesert.Components.Gear;
import com.bmmart2.forbiddendesert.Components.StormAction;
import com.bmmart2.forbiddendesert.Direction;

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
        d.add(new StormCard(StormAction.STORM_MOVE, Direction.NORTH, 2));
        d.add(new StormCard(StormAction.STORM_MOVE, Direction.NORTH, 3));
        d.add(new StormCard(StormAction.STORM_MOVE, Direction.NORTH, 2));
        d.add(new StormCard(StormAction.STORM_MOVE, Direction.NORTH, 1));
        d.add(new StormCard(StormAction.STORM_MOVE, Direction.NORTH, 1));
        d.add(new StormCard(StormAction.STORM_MOVE, Direction.NORTH, 1));
        d.add(new StormCard(StormAction.STORM_MOVE, Direction.SOUTH, 1));
        d.add(new StormCard(StormAction.STORM_MOVE, Direction.SOUTH, 1));
        d.add(new StormCard(StormAction.STORM_MOVE, Direction.SOUTH, 3));
        d.add(new StormCard(StormAction.STORM_MOVE, Direction.SOUTH, 2));
        d.add(new StormCard(StormAction.STORM_MOVE, Direction.SOUTH, 1));
        d.add(new StormCard(StormAction.STORM_MOVE, Direction.SOUTH, 2));
        d.add(new StormCard(StormAction.STORM_MOVE, Direction.EAST, 2));
        d.add(new StormCard(StormAction.STORM_MOVE, Direction.EAST, 1));
        d.add(new StormCard(StormAction.STORM_MOVE, Direction.EAST, 1));
        d.add(new StormCard(StormAction.STORM_MOVE, Direction.EAST, 2));
        d.add(new StormCard(StormAction.STORM_MOVE, Direction.EAST, 1));
        d.add(new StormCard(StormAction.STORM_MOVE, Direction.EAST, 3));
        d.add(new StormCard(StormAction.STORM_MOVE, Direction.WEST, 3));
        d.add(new StormCard(StormAction.STORM_MOVE, Direction.WEST, 1));
        d.add(new StormCard(StormAction.STORM_MOVE, Direction.WEST, 1));
        d.add(new StormCard(StormAction.STORM_MOVE, Direction.WEST, 1));
        d.add(new StormCard(StormAction.STORM_MOVE, Direction.WEST, 2));
        d.add(new StormCard(StormAction.STORM_MOVE, Direction.WEST, 2));
        d.shuffle();
        return d;
    }

    public static Deck generateGearDeck() {

        Deck d = new Deck();
        d.add(new GearCard(Gear.TERRASCOPE));
        d.add(new GearCard(Gear.TERRASCOPE));
        d.add(new GearCard(Gear.SOLAR_SHIELD));
        d.add(new GearCard(Gear.SOLAR_SHIELD));
        d.add(new GearCard(Gear.DUNE_BLASTER));
        d.add(new GearCard(Gear.DUNE_BLASTER));
        d.add(new GearCard(Gear.DUNE_BLASTER));
        d.add(new GearCard(Gear.JET_PACK));
        d.add(new GearCard(Gear.JET_PACK));
        d.add(new GearCard(Gear.JET_PACK));
        d.add(new GearCard(Gear.TIME_THROTTLE));
        d.add(new GearCard(Gear.SECRET_WATER_RESERVE));
        d.shuffle();
        return d;
    }


}
