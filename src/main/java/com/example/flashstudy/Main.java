package com.example.flashstudy;

import com.example.flashstudy.model.Deck;
import com.example.flashstudy.model.Flashcard;
import com.example.flashstudy.persistence.DeckStore;
import com.example.flashstudy.service.StudySession;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
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
            logError(t);
            System.err.println("A fatal application error occurred. Exiting securely.");
            System.exit(1);
        }
    }

    private static void logError(Throwable t) {
        try {
            Path errorLogPath = Paths.get(System.getProperty("user.home"), ".flashstudy", "error.log");
            Files.createDirectories(errorLogPath.getParent());
            // 🛡️ Sentinel: Enforce strict file permissions for the error log directory
            java.io.File dirFile = errorLogPath.getParent().toFile();
            dirFile.setReadable(false, false);
            dirFile.setWritable(false, false);
            dirFile.setExecutable(false, false);
            dirFile.setReadable(true, true);
            dirFile.setWritable(true, true);
            dirFile.setExecutable(true, true);

            // 🛡️ Sentinel: Atomically create file with strict permissions to prevent TOCTOU and Symlink Race attacks
            try {
                java.nio.file.attribute.FileAttribute<java.util.Set<java.nio.file.attribute.PosixFilePermission>> attr =
                        java.nio.file.attribute.PosixFilePermissions.asFileAttribute(java.nio.file.attribute.PosixFilePermissions.fromString("rw-------"));
                Files.createFile(errorLogPath, attr);
            } catch (java.nio.file.FileAlreadyExistsException | UnsupportedOperationException ignored) {
                // File exists or OS does not support POSIX attributes
            }

            try (java.io.OutputStream os = Files.newOutputStream(errorLogPath, java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND, java.nio.file.LinkOption.NOFOLLOW_LINKS);
                 PrintWriter pw = new PrintWriter(new java.io.OutputStreamWriter(os, java.nio.charset.StandardCharsets.UTF_8))) {
                pw.println("--- Error Logged at " + LocalDateTime.now() + " ---");
                t.printStackTrace(pw);
                pw.println();
            }
        } catch (Exception ignored) {
            // Silently ignore logging exceptions to prevent cascading failures
        }
    }

    private static void loop() {
        while (true) {
            System.out.println(
                    "\nFlashStudy - menu:\n1) List decks\n2) Create deck\n3) Add card\n4) Study deck\n5) Delete deck\n0) Exit\nChoose: ");

            if (!scanner.hasNextLine()) {
                System.out.println("\nExiting...");
                break;
            }

            String choice = scanner.nextLine().trim();
            if (choice.isEmpty()) {
                continue;
            }
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
                        System.out.println("Unknown option");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                logError(e);
                System.out.println("An unexpected error occurred. Please try again.");
            }
        }
    }

    private static void listDecks() throws Exception {
        List<Deck> decks = store.loadAll();
        if (decks.isEmpty()) {
            System.out.println("No decks found. Try creating one using option 2.");
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
            System.out.println("No decks available. Try creating one using option 2.");
            return null;
        }
        for (int i = 0; i < decks.size(); i++)
            System.out.printf("%d) %s (%d cards)\n", i + 1, decks.get(i).getName(), decks.get(i).size());
        System.out.print("Choose deck number (or 'q' to cancel): ");
        String s = scanner.nextLine().trim();
        if (s.isEmpty() || s.equalsIgnoreCase("q")) {
            System.out.println("Canceled.");
            return null;
        }
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
        System.out.print("Enter deck name (or 'q' to cancel): ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty() || name.equalsIgnoreCase("q")) {
            System.out.println("Canceled.");
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
        System.out.print("Question (or 'q' to cancel): ");
        String q = scanner.nextLine().trim();
        if (q.isEmpty() || q.equalsIgnoreCase("q")) {
            System.out.println("Canceled.");
            return;
        }
        System.out.print("Answer (or 'q' to cancel): ");
        String a = scanner.nextLine().trim();
        if (a.isEmpty() || a.equalsIgnoreCase("q")) {
            System.out.println("Canceled.");
            return;
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
