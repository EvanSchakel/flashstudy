package com.example.flashstudy.model;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private static final int MAX_NAME_LENGTH = 255;

    private String name;
    private List<Flashcard> cards = new ArrayList<>();

    // Default constructor for JSON
    public Deck() {
    }

    public Deck(String name) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("deck name required");
        if (name.length() > MAX_NAME_LENGTH)
            throw new IllegalArgumentException("deck name is too long");
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
        return cards.remove(index);
    }

    public int size() {
        return cards.size();
    }
}
