
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

