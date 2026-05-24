# Contributing to FlashStudy

FlashStudy is a solo personal project, but issues and pull requests are welcome.

## Found a bug?

Open an issue with a description of what you expected vs. what happened, your Java version, and the steps to reproduce it.

## Want to suggest a feature?

Open an issue describing the feature and why it would be useful. Keep in mind the project is intentionally small and CLI-focused.

## Submitting a pull request

1. Fork the repo and create a branch from `main`.
2. Make sure you have JDK 17+ and Maven 3.8+ installed.
3. Run the test suite before and after your changes:
   ```bash
   mvn test
   ```
4. Keep changes focused — one fix or feature per PR.
5. Open the pull request against `main`.

## Adding example decks

If you have a useful set of flashcards, you can add it to `examples/` as a JSON file in the existing format.
