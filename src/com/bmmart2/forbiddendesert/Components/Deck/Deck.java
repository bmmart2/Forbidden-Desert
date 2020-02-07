package com.bmmart2.forbiddendesert.Components.Deck;

import java.util.*;

@SuppressWarnings("ALL")
public class Deck {

    private LinkedList<Card> cards = new LinkedList<>();
    private Stack<Card> history = new Stack<>();

    public Deck() {}

    //Adds card to tail of deck
    protected void add(Card c) {
        if (!cards.isEmpty()) {
            if ((c instanceof GearCard && cards.peekFirst() instanceof GearCard)
                    || (c instanceof StormCard && cards.peekFirst() instanceof StormCard)) {
                cards.addLast(c);
                return;
            }
            else {
                throw new IllegalArgumentException("A deck can only contain cards of the same type.");
            }
        }
    }

    public void shuffle() {
        if (cards.isEmpty())
            return;
        else
            Collections.shuffle(cards);
    }

    public Card drawTop() {
        Card c = cards.pollFirst();
        history.push(c);
        return c;
    }

    public List<Card> viewHistory() {
        List<Card> l = new ArrayList<Card>();
        Stack<Card> temp = ((Stack<Card>) history.clone());
        while (!temp.empty()) {
            l.add(temp.pop());
        }
        return l;
    }

    public int getMaxDeckSize() {
        return history.size() + cards.size();
    }

    public int getDeckCurrentSize() {
        return cards.size();
    }

    public int getHistorySize() {
        return history.size();
    }

    public void undo() {
        cards.addFirst(history.pop());
    }


}
