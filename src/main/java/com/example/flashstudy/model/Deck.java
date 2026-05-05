package com.example.flashstudy.model;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private String name;
    private List<Flashcard> cards = new ArrayList<>();

    // Default constructor for JSON
    public Deck() {
    }

    public Deck(String name) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("deck name required");
        this.name = name.trim();
    }

    public String getName() {
        return name;
    }

    public List<Flashcard> getCards() {
        return cards;
    }

    public void addCard(Flashcard card) {
        if (card != null)
            cards.add(card);
    }

    public Flashcard removeCard(int index) {
        if (index < 0 || index >= cards.size()) {
            throw new IndexOutOfBoundsException("Card index " + index + " is out of bounds for deck size " + cards.size());
        }
        return cards.remove(index);
    }

    public int size() {
        return cards.size();
    }
}
