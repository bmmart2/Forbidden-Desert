package com.bmmart2.forbiddendesert.Components.Deck;

import com.bmmart2.forbiddendesert.Components.StormAction;
import com.bmmart2.forbiddendesert.Direction;

public class StormCard extends Card {

    private StormAction action;

    private Direction direction;
    private int moveAmt;

    public StormCard(StormAction sa) {
        if (sa == StormAction.STORM_MOVE) {
            throw new IllegalArgumentException("STORM_MOVE must be supplied with a direction and movement amount.");
        }
        else {
            action = sa;
        }
    }

    public StormCard(StormAction sa, Direction d, int amt) {
        if (!(sa == StormAction.STORM_MOVE)) {
            throw new IllegalArgumentException("Can only supply Direction and Move Amount to STORM_MOVE cards.");
        }
        else {
            action = sa;
            direction = d;
            moveAmt = amt;
        }
    }

    public StormAction getAction() {

        return action;
    }

    public Direction getDirection() {
        if (!(action == StormAction.STORM_MOVE)) {
            throw new IllegalArgumentException("Only STORM_MOVE cards have a Direction attribute.");
        }
        return direction;
    }

    public int getMoveAmt() {
        if (!(action == StormAction.STORM_MOVE)) {
            throw new IllegalArgumentException("Only STORM_MOVE cards have a MoveAmt attribute.");
        }
        return moveAmt;
    }


}