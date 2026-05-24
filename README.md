<div align="center">

# 🧠 FlashStudy

**A Command-Line Flashcard Application for Accelerated Learning**

[![Build Status](https://github.com/EvanSchakel/flashstudy/actions/workflows/maven.yml/badge.svg)](https://github.com/EvanSchakel/flashstudy/actions)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Java Version](https://img.shields.io/badge/Java-17%2B-blue)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-Supported-orange.svg)](https://maven.apache.org/)

[Features](#-features) • [Quick Start](#-quick-start) • [Architecture](#-architecture) • [Usage](#-usage--examples)

</div>

---

**FlashStudy** is a lightweight command-line flashcard app built in Java. It lets you create decks, manage cards, and study interactively — all from the terminal, with no external services required.

It demonstrates core Java patterns including OOP design, Collections, File I/O, JSON persistence via Gson, JUnit 5 testing, and a Maven build pipeline.

---

## ✨ Features

- 🗂️ **Deck Management** — Create, organize, and delete named decks.
- ➕ **Card Editor** — Add, edit, and remove individual flashcards (question + answer).
- 🧠 **Interactive Study Mode** — Test yourself and track correctness over sessions.
- 💾 **Persistent Storage** — Decks saved as JSON in `~/.flashstudy` — readable and easy to back up.
- ⚡ **Lightweight & Fast** — Responsive CLI with minimal dependencies.
- 🛠️ **Modern Java Stack** — Java 17+ with Maven.

---

## 🚀 Quick Start

### Prerequisites

- JDK 17 or higher
- Apache Maven 3.8+

### Installation

```bash
git clone https://github.com/EvanSchakel/flashstudy.git
cd flashstudy
mvn clean package
java -jar target/flashstudy-0.1.0.jar
```

### Load example data

```bash
mkdir -p ~/.flashstudy
cp examples/sample-deck.json ~/.flashstudy/Sample_Deck.json
```

Once copied, launch the application and `Sample Deck` will appear in your library immediately.

---

## 📖 Usage & Examples

Launch the application and navigate the menu to create decks, add cards, or enter study mode.

---

## 🏗️ Architecture

- **CLI Interface** — Menu-driven terminal experience.
- **Data Models** — Clean `Deck` and `Card` object representations.
- **Persistence Layer** — Gson serializes/deserializes decks to human-readable JSON in `~/.flashstudy`.
- **Testing** — JUnit 5 unit tests covering core logic.

---

## 📄 License

[MIT](LICENSE) © 2026 Evan Schakel
