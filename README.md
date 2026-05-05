
# FlashStudy

FlashStudy is a small command-line flashcard application intended for beginning CS students. It demonstrates core Java skills: OOP design, collections, file I/O, JSON persistence, unit testing, and a Maven build.

Features
- Create and delete named decks
- Add and remove flashcards (question + answer)
- Study mode with correctness tracking
- Persist decks to JSON in `~/.flashstudy`
- Maven build and tests

Requirements
- Java 11+
- Maven

Architecture Overview

- **Domain (`com.example.flashstudy.model`)**: Contains core application models like `Flashcard` and `Deck` with basic behavior and data validation.
- **Persistence (`com.example.flashstudy.persistence`)**: The `DeckStore` class manages saving and loading decks as JSON files to the user's local disk.
- **UI/CLI (`com.example.flashstudy.Main` & `com.example.flashstudy.service`)**: The entrypoint and interactive prompt to manage decks and study sessions.

Running Tests

The project includes a suite of JUnit tests. You can run them via Maven:

```bash
mvn test
```

Quick start

Build the project:

```bash
mvn package
```

Run the app:

```bash
java -jar target/flashstudy-0.1.0.jar
```

Example data

Copy the example deck into your data directory to try:

```bash
mkdir -p ~/.flashstudy
cp examples/sample-deck.json ~/.flashstudy/Sample_Deck.json
```

Repository status & CI

This repository includes a GitHub Actions workflow at `.github/workflows/maven.yml` that runs `mvn test` and packages the artifact on pushes and pull requests to `main`.

License

This project is provided under the MIT license. See `LICENSE` for details.

Contributing

Contributions are welcome — open an issue or submit a pull request. If you publish your own decks, consider adding them under `examples/`.

Repository

The project is published at: https://github.com/EvanSchakel/flashstudy

