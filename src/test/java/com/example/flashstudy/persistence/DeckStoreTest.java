package com.example.flashstudy.persistence;

import com.example.flashstudy.model.Deck;
import com.example.flashstudy.model.Flashcard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DeckStoreTest {

    @TempDir
    Path tempDir;

    private DeckStore store;

    @BeforeEach
    public void setUp() {
        store = new DeckStore(tempDir);
    }

    @Test
    public void testSaveAndLoadDeck() throws IOException {
        Deck deck = new Deck("Science");
        deck.addCard(new Flashcard("H2O", "Water"));

        store.saveDeck(deck);

        Deck loadedDeck = store.loadDeck("Science");
        assertNotNull(loadedDeck);
        assertEquals("Science", loadedDeck.getName());
        assertEquals(1, loadedDeck.size());
        assertEquals("H2O", loadedDeck.getCards().get(0).getQuestion());
    }

    @Test
    public void testLoadNonExistentDeck() throws IOException {
        Deck loadedDeck = store.loadDeck("Math");
        assertNull(loadedDeck);
    }

    @Test
    public void testLoadAll() throws IOException {
        Deck deck1 = new Deck("Math");
        Deck deck2 = new Deck("History");

        store.saveDeck(deck1);
        store.saveDeck(deck2);

        List<Deck> decks = store.loadAll();
        assertEquals(2, decks.size());
    }

    @Test
    public void testDeleteDeck() throws IOException {
        Deck deck = new Deck("ToDelete");
        store.saveDeck(deck);

        assertTrue(store.deleteDeck("ToDelete"));
        assertNull(store.loadDeck("ToDelete"));
        assertFalse(store.deleteDeck("ToDelete"));
    }
}
