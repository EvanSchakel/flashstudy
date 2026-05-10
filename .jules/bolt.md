## 2026-05-04 - Parallel JSON Loading Optimization
**Learning:** In a Java app making many small file reads and parsing JSON (e.g., loading thousands of deck files), parallelizing the file reading and parsing using `Files.list(dir).parallel()` over `Files.newDirectoryStream` yields a significant performance improvement (~58% faster) since it utilizes multi-core IO and parsing instead of sequential processing.
**Action:** Always consider parallel streams for bulk file loading/deserialization tasks when dealing with a large volume of separate small files.

## 2026-05-05 - Pre-compile Regex Patterns
**Learning:** Using `String.replaceAll` internally compiles a new regex `Pattern` on every invocation. For string sanitization functions that are repeatedly called during bulk operations (like generating filenames for 1000s of decks), this creates unnecessary CPU overhead.
**Action:** Always pre-compile regular expressions into static `Pattern` constants for functions that are called frequently or in loops, resulting in nearly 50% faster execution.

## 2026-05-10 - Gson Serialization Optimization
**Learning:** For internal data storage using JSON, Gson's pretty printing introduces significant serialization overhead and inflates file size. Since the `.flashstudy` JSON files are internal and not primarily intended for manual editing, the performance cost of pretty-printing outweighs its readability benefits.
**Action:** Use standard `new Gson()` instead of `new GsonBuilder().setPrettyPrinting().create()` for all internal data storage to optimize execution speed and minimize disk usage.
