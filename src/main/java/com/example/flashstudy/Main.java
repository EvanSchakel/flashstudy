package com.example.flashstudy;

import com.example.flashstudy.model.Deck;
import com.example.flashstudy.model.Flashcard;
import com.example.flashstudy.persistence.DeckStore;
import com.example.flashstudy.service.StudySession;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static DeckStore store;
    private static Scanner scanner;

    public static void main(String[] args) {
        try {
            Path dataDir = Paths.get(System.getProperty("user.home"), ".flashstudy");
            store = new DeckStore(dataDir);
            scanner = new Scanner(System.in);
            loop();
        } catch (Throwable t) {
            System.err.println("A fatal application error occurred. Exiting securely.");
            System.exit(1);
        }
    }

    private static void loop() {
        while (true) {
            System.out.println("\n--------------------------------------------------");
            System.out.print("FlashStudy - menu:\n" +
                    "1) List decks\n" +
                    "2) Create deck\n" +
                    "3) Add card\n" +
                    "4) Study deck\n" +
                    "5) Delete deck\n" +
                    "0) Exit\n" +
                    "Choose: ");

            if (!scanner.hasNextLine()) {
                System.out.println("\nExiting...");
                break;
            }

            String choice = scanner.nextLine().trim();
            System.out.println("--------------------------------------------------");
            try {
                switch (choice) {
                    case "1":
                        listDecks();
                        break;
                    case "2":
                        createDeck();
                        break;
                    case "3":
                        addCard();
                        break;
                    case "4":
                        studyDeck();
                        break;
                    case "5":
                        deleteDeck();
                        break;
                    case "0":
                        System.out.println("Bye!");
                        return;
                    default:
                        System.out.println("Unknown option.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void listDecks() throws Exception {
        List<Deck> decks = store.loadAll();
        if (decks.isEmpty()) {
            System.out.println("No decks found.");
            return;
        }
        System.out.println("Available decks:");
        for (int i = 0; i < decks.size(); i++) {
            Deck d = decks.get(i);
            System.out.printf("%d) %s (%d cards)\n", i + 1, d.getName(), d.size());
        }
    }

    private static Deck chooseDeck() throws Exception {
        List<Deck> decks = store.loadAll();
        if (decks.isEmpty()) {
            System.out.println("No decks available.");
            return null;
        }
        for (int i = 0; i < decks.size(); i++)
            System.out.printf("%d) %s (%d cards)\n", i + 1, decks.get(i).getName(), decks.get(i).size());
        System.out.print("Choose deck number: ");
        String s = scanner.nextLine().trim();
        try {
            int idx = Integer.parseInt(s) - 1;
            if (idx < 0 || idx >= decks.size()) {
                System.out.println("Invalid number");
                return null;
            }
            return decks.get(idx);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input");
            return null;
        }
    }

    private static void createDeck() throws Exception {
        System.out.print("Enter deck name: ");
        String name = scanner.nextLine().trim();
        if (name.isBlank()) {
            System.out.println("Name required.");
            return;
        }
        if (store.loadDeck(name) != null) {
            System.out.println("Deck already exists.");
            return;
        }
        Deck deck = new Deck(name);
        store.saveDeck(deck);
        System.out.println("Deck created.");
    }

    private static void addCard() throws Exception {
        Deck deck = chooseDeck();
        if (deck == null)
            return;

        String q = "";
        while (q.isBlank()) {
            System.out.print("Question: ");
            q = scanner.nextLine().trim();
            if (q.isBlank()) System.out.println("Question cannot be empty.");
        }

        String a = "";
        while (a.isBlank()) {
            System.out.print("Answer: ");
            a = scanner.nextLine().trim();
            if (a.isBlank()) System.out.println("Answer cannot be empty.");
        }

        Flashcard card = new Flashcard(q, a);
        deck.addCard(card);
        store.saveDeck(deck);
        System.out.println("Card added.");
    }

    private static void studyDeck() throws Exception {
        Deck deck = chooseDeck();
        if (deck == null)
            return;
        StudySession s = new StudySession(deck);
        s.run(scanner);
        store.saveDeck(deck);
    }

    private static void deleteDeck() throws Exception {
        Deck deck = chooseDeck();
        if (deck == null)
            return;
        System.out.print("Type 'yes' to confirm delete " + deck.getName() + ": ");
        String ans = scanner.nextLine().trim();
        if (ans.equalsIgnoreCase("yes")) {
            if (store.deleteDeck(deck.getName()))
                System.out.println("Deleted.");
            else
                System.out.println("Could not delete.");
        } else {
            System.out.println("Canceled.");
        }
    }
}
