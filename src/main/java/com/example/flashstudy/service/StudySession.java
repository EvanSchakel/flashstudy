package com.example.flashstudy.service;

import com.example.flashstudy.model.Deck;
import com.example.flashstudy.model.Flashcard;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class StudySession {
    private final Deck deck;

    public StudySession(Deck deck) {
        this.deck = deck;
    }

    public void run(Scanner scanner) {
        if (deck == null || deck.size() == 0) {
            System.out.println("Deck is empty. Try adding cards using option 3.");
            return;
        }
        List<Flashcard> session = new ArrayList<>(deck.getCards());
        Collections.shuffle(session);
        int correct = 0;
        int attempted = 0;
        for (int i = 0; i < session.size(); i++) {
            Flashcard c = session.get(i);
            System.out.printf("\nCard %d/%d\nQuestion:\n%s\n\nPress Enter to reveal answer (or 'q' to quit)...", i + 1, session.size(),
                    c.getQuestion());
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("q")) {
                break;
            }
            attempted++;
            System.out.println("Answer:\n" + c.getAnswer());
            System.out.print("Did you get it right? (y/n): ");
            String ans = scanner.nextLine().trim().toLowerCase();
            boolean got = ans.startsWith("y");
            c.recordAttempt(got);
            if (got)
                correct++;
        }
        if (attempted == 0) {
            System.out.println("\nSession ended before any cards were attempted.");
        } else {
            System.out.printf("\nSession complete: %d/%d correct (%.1f%%)\n", correct, attempted,
                    100.0 * correct / attempted);
        }
    }
}
