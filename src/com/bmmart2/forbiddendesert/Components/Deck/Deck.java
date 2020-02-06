package com.bmmart2.forbiddendesert.Components.Deck;

import java.util.LinkedList;
import java.util.Stack;

public class Deck {

    private LinkedList<Card> cards = new LinkedList<>();
    private Stack<Card> history = new Stack<>();

    public Deck() {}

    //Adds card to tail of deck
    protected add(Card c) {
        if (!cards.isEmpty()) {
            if ((c instanceof GearCard && cards.peekFirst() instanceof GearCard)
                    || (c instanceof StormCard && cards.peekFirst() instanceof StormCard)) {

            }
        }
    }

}
