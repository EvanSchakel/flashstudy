package com.example.flashstudy.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FlashcardTest {
    @Test
    public void recordAttempt() {
        Flashcard f = new Flashcard("Q", "A");
        f.recordAttempt(true);
        f.recordAttempt(false);
        assertEquals(2, f.getAttemptCount());
        assertEquals(1, f.getCorrectCount());
        assertEquals(0.5, f.getAccuracy(), 1e-6);
    }

    @Test
    public void constructorThrowsOnTooLongQuestionOrAnswer() {
        String longString = "a".repeat(256);
        IllegalArgumentException e1 = assertThrows(IllegalArgumentException.class, () -> new Flashcard(longString, "A"));
        assertEquals("question or answer too long", e1.getMessage());

        IllegalArgumentException e2 = assertThrows(IllegalArgumentException.class, () -> new Flashcard("Q", longString));
        assertEquals("question or answer too long", e2.getMessage());
    }
}
