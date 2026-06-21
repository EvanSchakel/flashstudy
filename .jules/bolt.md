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

## 2026-05-27 - Incremental Cache Updates vs. Invalidation
**Learning:** Fully invalidating an in-memory cache on every write operation (e.g., `deckCache = null` on `saveDeck`) causes extreme performance degradation for subsequent reads (e.g., `loadAll()`), particularly when dealing with many files. Reparsing all 10k files when only 1 file changed took over 1200ms compared to under 100ms with incremental updates.
**Action:** When managing an in-memory cache representing distinct disk entities, prefer incrementally updating the cache (adding/removing the specific modified entity) over full cache invalidation to eliminate redundant disk I/O and deserialization overhead.

## 2026-05-28 - In-Memory Caching and Data Encapsulation
**Learning:** Returning objects directly from an internal cache can lead to issues if those objects are mutable. Additionally, intercepting `loadDeck` to return directly from the cache bypassed the `NoSuchFileException` that the method contract guarantees if a deck is not found, altering the expected behavior of the system and potentially causing `NullPointerException` in calling code.
**Action:** When working with caching, ensure that fetching from the cache maintains the original method's semantics (e.g., throwing expected exceptions). Also, do not cache mutable objects and then serve references to them. It is generally safer to let operations like `loadDeck` (which fetches a specific entity) continue hitting the disk unless specifically designed to return immutable copies or defensive copies from the cache.
## 2026-06-21 - Early Return on Unmodified State
**Learning:** The application was performing unnecessary JSON serialization and disk I/O by unconditionally saving state even when the user exited a session without making changes.
**Action:** Track if state was actually modified during interactive sessions and use early returns to skip redundant disk save operations.
