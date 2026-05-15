## 2026-05-04 - Parallel JSON Loading Optimization
**Learning:** In a Java app making many small file reads and parsing JSON (e.g., loading thousands of deck files), parallelizing the file reading and parsing using `Files.list(dir).parallel()` over `Files.newDirectoryStream` yields a significant performance improvement (~58% faster) since it utilizes multi-core IO and parsing instead of sequential processing.
**Action:** Always consider parallel streams for bulk file loading/deserialization tasks when dealing with a large volume of separate small files.

## 2026-05-05 - Pre-compile Regex Patterns
**Learning:** Using `String.replaceAll` internally compiles a new regex `Pattern` on every invocation. For string sanitization functions that are repeatedly called during bulk operations (like generating filenames for 1000s of decks), this creates unnecessary CPU overhead.
**Action:** Always pre-compile regular expressions into static `Pattern` constants for functions that are called frequently or in loops, resulting in nearly 50% faster execution.

## 2026-05-14 - Gson Pretty Printing Overhead
**Learning:** Using `GsonBuilder().setPrettyPrinting().create()` introduces significant overhead in both serialization speed and resulting file size. In benchmark tests with 1000 items, standard `new Gson()` was ~33% faster (661ms vs 985ms) and produced ~33% smaller files (83KB vs 125KB).
**Action:** Always prefer standard `new Gson()` over pretty printing for internal data persistence where human-readability is not a primary requirement, to optimize both speed and storage.
