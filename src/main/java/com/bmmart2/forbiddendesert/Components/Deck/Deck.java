package com.bmmart2.forbiddendesert.Components.Deck;

import java.util.*;

@SuppressWarnings("ALL")
public class Deck {

    private LinkedList<Card> cards = new LinkedList<>();
    private Stack<Card> history = new Stack<>();

    public Deck() {}

    //Adds card to tail of deck
    protected boolean add(Card c) {
        if (!cards.isEmpty()) {
            if ((c instanceof GearCard && cards.peekFirst() instanceof GearCard)
                    || (c instanceof StormCard && cards.peekFirst() instanceof StormCard)) {
                cards.addLast(c);
                return true;
            }
            else {
                System.out.println("Deck Error: Card type mismatch.");
                return false;
            }
        }
        else {
            cards.addLast(c);
            return true;
        }
    }

    public void shuffle() {
        if (cards.isEmpty())
            return;
        else
            Collections.shuffle(cards);
    }

    public Card drawTop() {
        if (cards.isEmpty()) {
            return null;
        }
        Card c = cards.pollFirst();
        history.push(c);
        return c;
    }

    public void resetDeck() {
        while (!history.isEmpty()) {
            cards.addLast(history.pop());
        }
        Collections.shuffle(cards);
    }

    public List<Card> viewHistory() {
        List<Card> l = new ArrayList<Card>();
        Stack<Card> temp = ((Stack<Card>) history.clone());
        while (!temp.empty()) {
            l.add(temp.pop());
        }
        return l;
    }

    public Card peekAt(int cardFromTop) {
        if (cardFromTop >= cards.size()) {
            throw new IndexOutOfBoundsException();
        }
        else {
            return cards.get(cardFromTop);
        }
    }

    public boolean contains(Card c) {
        return cards.contains(c);
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
