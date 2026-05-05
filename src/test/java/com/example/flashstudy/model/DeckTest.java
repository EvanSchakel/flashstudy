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

    @Test
    public void constructorRejectsNullOrBlank() {
        assertThrows(IllegalArgumentException.class, () -> new Deck(null));
        assertThrows(IllegalArgumentException.class, () -> new Deck(""));
        assertThrows(IllegalArgumentException.class, () -> new Deck("   "));
    }

    @Test
    public void removeCardOutOfBounds() {
        Deck d = new Deck("Test");
        assertThrows(IndexOutOfBoundsException.class, () -> d.removeCard(0));
        assertThrows(IndexOutOfBoundsException.class, () -> d.removeCard(-1));

        d.addCard(new Flashcard("Q", "A"));
        assertThrows(IndexOutOfBoundsException.class, () -> d.removeCard(1));
    }
}
