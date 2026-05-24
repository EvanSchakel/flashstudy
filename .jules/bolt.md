## 2026-05-04 - Parallel JSON Loading Optimization
**Learning:** In a Java app making many small file reads and parsing JSON (e.g., loading thousands of deck files), parallelizing the file reading and parsing using `Files.list(dir).parallel()` over `Files.newDirectoryStream` yields a significant performance improvement (~58% faster) since it utilizes multi-core IO and parsing instead of sequential processing.
**Action:** Always consider parallel streams for bulk file loading/deserialization tasks when dealing with a large volume of separate small files.

## 2026-05-05 - Pre-compile Regex Patterns
**Learning:** Using `String.replaceAll` internally compiles a new regex `Pattern` on every invocation. For string sanitization functions that are repeatedly called during bulk operations (like generating filenames for 1000s of decks), this creates unnecessary CPU overhead.
**Action:** Always pre-compile regular expressions into static `Pattern` constants for functions that are called frequently or in loops, resulting in nearly 50% faster execution.

## 2026-05-22 - Gson Pretty Printing Overhead
**Learning:** Using `new GsonBuilder().setPrettyPrinting().create()` for internal storage serialization adds significant CPU overhead and file size bloat (about 40% slower and 33% larger output) compared to standard `new Gson()`.
**Action:** Always prefer standard `new Gson()` for internal data persistence where human-readability is not a strict requirement, prioritizing speed and storage efficiency.

## 2026-05-24 - Remove Redundant Files.exists()
**Learning:** Using `Files.exists(path)` immediately followed by opening the file (e.g., `Files.newBufferedReader(path)`) is a performance anti-pattern (and potentially a Time-of-Check to Time-of-Use bug). The file system must be queried twice.
**Action:** Let the file open operation fail and catch the resulting `NoSuchFileException` instead of checking for existence beforehand, which can be nearly 2x faster for file loading operations.
