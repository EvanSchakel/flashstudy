package com.example.flashstudy.persistence;

import com.example.flashstudy.model.Deck;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DeckStore {
    private final Path dir;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public DeckStore(Path dir) {
        this.dir = dir;
        try {
            Files.createDirectories(dir);
        } catch (IOException e) {
            throw new RuntimeException("Cannot create data directory: " + dir, e);
        }
    }

    public void saveDeck(Deck deck) throws IOException {
        Path file = dir.resolve(sanitize(deck.getName()) + ".json");
        try (Writer w = Files.newBufferedWriter(file)) {
            gson.toJson(deck, w);
        }
    }

    public Deck loadDeck(String name) throws IOException {
        Path file = dir.resolve(sanitize(name) + ".json");
        if (!Files.exists(file))
            return null;
        try (Reader r = Files.newBufferedReader(file)) {
            return gson.fromJson(r, Deck.class);
        }
    }

    public List<Deck> loadAll() throws IOException {
        List<Deck> list = new ArrayList<>();
        try (DirectoryStream<Path> ds = Files.newDirectoryStream(dir, "*.json")) {
            for (Path p : ds) {
                try (Reader r = Files.newBufferedReader(p)) {
                    Deck d = gson.fromJson(r, Deck.class);
                    if (d != null)
                        list.add(d);
                } catch (IOException ignored) {
                }
            }
        }
        return list;
    }

    public boolean deleteDeck(String name) throws IOException {
        return Files.deleteIfExists(dir.resolve(sanitize(name) + ".json"));
    }

    private String sanitize(String name) {
        return name.replaceAll("[^a-zA-Z0-9\\-_. ]", "_").replace(' ', '_');
    }
}
