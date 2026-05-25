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
    public void rejectsTooLongQuestionOrAnswer() {
        String tooLong = "x".repeat(256);
        assertThrows(IllegalArgumentException.class, () -> new Flashcard(tooLong, "A"));
        assertThrows(IllegalArgumentException.class, () -> new Flashcard("Q", tooLong));
    }
}
