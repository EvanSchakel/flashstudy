<div align="center">

# 🧠 FlashStudy

**A Command-Line Flashcard Application for Accelerated Learning**

[![Build Status](https://github.com/EvanSchakel/flashstudy/actions/workflows/maven.yml/badge.svg)](https://github.com/EvanSchakel/flashstudy/actions)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Java Version](https://img.shields.io/badge/Java-11%2B-blue)](https://www.oracle.com/java/)
[![Maven Central](https://img.shields.io/badge/Maven-Supported-orange.svg)](https://maven.apache.org/)

[Features](#features) • [Quick Start](#quick-start) • [Architecture](#architecture) • [Usage](#usage) • [Contributing](#contributing)

</div>

---

**FlashStudy** is a robust, lightweight command-line application designed to help you memorize anything efficiently. Originally conceived as a demonstration of core Java capabilities for beginning Computer Science students, it has evolved into a fully functional study companion.

It showcases essential Java paradigms including Object-Oriented Programming (OOP) design, robust Collections usage, File I/O operations, JSON persistence via Gson, comprehensive unit testing, and a streamlined Maven build process.

## ✨ Features

*   🗂️ **Deck Management:** Easily create, organize, and delete named decks of flashcards.
*   ➕ **Card Editor:** Add, edit, and remove individual flashcards (Questions & Answers).
*   🧠 **Interactive Study Mode:** Test yourself and track your correctness over time.
*   💾 **Persistent Storage:** Seamlessly saves all decks to JSON format locally in your `~/.flashstudy` directory.
*   ⚡ **Lightweight & Fast:** Built for speed with a responsive CLI interface.
*   🛠️ **Modern Java Stack:** Runs on Java 11+ and uses Maven for dependency management.

## 🚀 Quick Start

### Prerequisites

Ensure you have the following installed on your system:
*   [Java Development Kit (JDK) 11](https://www.oracle.com/java/technologies/downloads/) or higher.
*   [Apache Maven](https://maven.apache.org/download.cgi) (for building the project).

### Installation

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/EvanSchakel/flashstudy.git
    cd flashstudy
    ```

2.  **Build the application:**
    ```bash
    mvn clean package
    ```
    *This command compiles the source code, runs unit tests, and packages the application into an executable JAR file.*

3.  **Run FlashStudy:**
    ```bash
    java -jar target/flashstudy-0.1.0.jar
    ```

## 📖 Usage & Examples

### Using Example Data

To get started quickly, we've provided an example deck. You can copy it into your local data directory:

```bash
# Create the data directory if it doesn't exist
mkdir -p ~/.flashstudy

# Copy the sample deck
cp examples/sample-deck.json ~/.flashstudy/Sample_Deck.json
```

Once copied, launch the application and the `Sample Deck` will be immediately available in your library.

## 🏗️ Architecture

FlashStudy is built with a focus on simplicity and clean code principles:

*   **CLI Interface:** Provides a straightforward menu-driven experience.
*   **Data Models:** Clean representation of `Deck` and `Card` objects.
*   **Persistence Layer:** Uses `com.google.code.gson` to serialize and deserialize decks into human-readable JSON files, ensuring your study data is safe and easily backup-able.
*   **Testing:** Thoroughly tested using JUnit 5 to guarantee stability and correctness.

## 🤝 Contributing

We welcome contributions from the community! Whether you're fixing bugs, adding new features, or improving documentation, your help is appreciated.

Please see our [CONTRIBUTING.md](CONTRIBUTING.md) for detailed guidelines on how to get started.

> **💡 Pro Tip:** If you create awesome decks that you think others would find useful, consider submitting a Pull Request to add them to the `examples/` directory!

## 📜 License

This project is open-source software licensed under the [MIT License](LICENSE).

---
<div align="center">
  <i>Developed with ❤️ by <a href="https://github.com/EvanSchakel">Evan Schakel</a> and contributors.</i>
</div>
