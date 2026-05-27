## 2026-05-04 - Parallel JSON Loading Optimization
**Learning:** In a Java app making many small file reads and parsing JSON (e.g., loading thousands of deck files), parallelizing the file reading and parsing using `Files.list(dir).parallel()` over `Files.newDirectoryStream` yields a significant performance improvement (~58% faster) since it utilizes multi-core IO and parsing instead of sequential processing.
**Action:** Always consider parallel streams for bulk file loading/deserialization tasks when dealing with a large volume of separate small files.

## 2026-05-05 - Pre-compile Regex Patterns
**Learning:** Using `String.replaceAll` internally compiles a new regex `Pattern` on every invocation. For string sanitization functions that are repeatedly called during bulk operations (like generating filenames for 1000s of decks), this creates unnecessary CPU overhead.
**Action:** Always pre-compile regular expressions into static `Pattern` constants for functions that are called frequently or in loops, resulting in nearly 50% faster execution.

## 2026-05-22 - Gson Pretty Printing Overhead
**Learning:** Using `new GsonBuilder().setPrettyPrinting().create()` for internal storage serialization adds significant CPU overhead and file size bloat (about 40% slower and 33% larger output) compared to standard `new Gson()`.
**Action:** Always prefer standard `new Gson()` for internal data persistence where human-readability is not a strict requirement, prioritizing speed and storage efficiency.

## 2026-05-27 - Immutable Collection Caching
**Learning:** Returning a direct reference to a cached, mutable internal data structure (like an `ArrayList` representing loaded database entities) is an anti-pattern. If a consumer mutates the list, the internal cache becomes permanently corrupted until restarted or invalidated.
**Action:** When implementing in-memory caches that return Collections, always wrap the cached result in `Collections.unmodifiableList(cache)` or return a defensive copy to prevent callers from inadvertently modifying internal state.
