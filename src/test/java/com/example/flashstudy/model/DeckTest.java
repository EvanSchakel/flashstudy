package com.example.flashstudy.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {
    @Test
    public void addAndRemoveCard() {
        Deck d = new Deck("Test");
        Flashcard c = new Flashcard("Q", "A");
        d.addCard(c);
        assertEquals(1, d.size());
        d.removeCard(0);
        assertEquals(0, d.size());
    }
}
