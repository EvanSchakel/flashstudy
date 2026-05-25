## 2024-05-04 - Missing Global Error Handling in CLI Apps
**Vulnerability:** The application lacks a global exception handler and does not check for end-of-file (EOF) on standard input, which leads to raw stack traces being printed to the console when an unexpected error occurs or input abruptly ends.
**Learning:** Even in command-line applications, uncaught exceptions can leak internal application state, class names, and execution paths through stack traces. This is considered information leakage and can aid an attacker in understanding the application's structure.
**Prevention:** Always implement a global `try-catch` block (or `Thread.setDefaultUncaughtExceptionHandler`) at the entry point of the application to catch unhandled errors, log them securely if needed, and exit gracefully with a generic error message. Additionally, always validate standard input availability (e.g., `Scanner.hasNextLine()`) to prevent abrupt crashes on EOF.
## 2026-05-06 - [Enforce secure file permissions]
**Vulnerability:** Data storage directory for flashcards lacked explicit permission constraints, risking unauthorized read/write access to user data.
**Learning:** Default directory creation (Files.createDirectories) uses umask, which can be overly permissive.
**Prevention:** Always explicitly set restrictive permissions (e.g. owner-only readable/writable/executable) on directories containing sensitive user data via `File#setReadable(false, false)` followed by `File#setReadable(true, true)` (and similarly for writable and executable).
## 2026-05-23 - Prevent Local DoS via Malformed Data Files
**Vulnerability:** The application failed to catch `RuntimeException`s (like `JsonSyntaxException`) when parsing locally stored JSON data in a parallel stream. A single corrupted or maliciously crafted local JSON file would crash the entire data loading process, resulting in a Denial of Service.
**Learning:** File parsers (like Gson) often throw unchecked exceptions on malformed input. When iterating over multiple files, an unhandled unchecked exception from one file will terminate the entire operation for all files.
**Prevention:** Always wrap parsing operations of user-controlled or external data in a robust `try-catch` block that explicitly handles or broadens to catch parsing-specific `RuntimeException`s, especially within loops or streams, ensuring that one bad file does not impact the availability of the rest of the application.
## 2026-05-25 - Prevent TOCTOU Race Condition on File Access
**Vulnerability:** The application used `Files.exists()` to check if a deck file exists before trying to open it. This introduces a Time-of-Check to Time-of-Use (TOCTOU) race condition, where the file could be deleted or modified between the check and the actual read operation.
**Learning:** File system checks before usage are often redundant and can lead to race conditions. It is more secure and efficient to attempt the operation directly and handle the resulting exception if it fails.
**Prevention:** Avoid `Files.exists()` checks before opening a file. Instead, attempt to open the file directly and catch `NoSuchFileException` or other relevant IO exceptions.
