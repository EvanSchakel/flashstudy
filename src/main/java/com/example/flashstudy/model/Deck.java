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
        // 🛡️ Sentinel: Enforce max length validation to prevent resource exhaustion (DoS) via excessively long strings.
        if (name.length() > 255)
            throw new IllegalArgumentException("deck name must be less than 255 characters");
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
