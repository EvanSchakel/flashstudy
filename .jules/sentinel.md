## 2024-05-04 - Missing Global Error Handling in CLI Apps
**Vulnerability:** The application lacks a global exception handler and does not check for end-of-file (EOF) on standard input, which leads to raw stack traces being printed to the console when an unexpected error occurs or input abruptly ends.
**Learning:** Even in command-line applications, uncaught exceptions can leak internal application state, class names, and execution paths through stack traces. This is considered information leakage and can aid an attacker in understanding the application's structure.
**Prevention:** Always implement a global `try-catch` block (or `Thread.setDefaultUncaughtExceptionHandler`) at the entry point of the application to catch unhandled errors, log them securely if needed, and exit gracefully with a generic error message. Additionally, always validate standard input availability (e.g., `Scanner.hasNextLine()`) to prevent abrupt crashes on EOF.
## 2026-05-06 - [Enforce secure file permissions]
**Vulnerability:** Data storage directory for flashcards lacked explicit permission constraints, risking unauthorized read/write access to user data.
**Learning:** Default directory creation (Files.createDirectories) uses umask, which can be overly permissive.
**Prevention:** Always explicitly set restrictive permissions (e.g. owner-only readable/writable/executable) on directories containing sensitive user data via `File#setReadable(false, false)` followed by `File#setReadable(true, true)` (and similarly for writable and executable).
## 2024-05-08 - Prevent Path Traversal in File Saving
**Vulnerability:** The sanitization regex for file names allowed dots (`.`), which could permit a malicious user to craft a deck name like `../../etc/passwd` to overwrite arbitrary files when saving a deck, escaping the designated storage directory.
**Learning:** Allowlisting characters for file names is the correct approach, but one must carefully consider what characters are safe. Allowing dots specifically opens up the possibility for directory traversal via `..` sequences.
**Prevention:** Always use a strict whitelist for file name sanitization that explicitly excludes dots (e.g., `[^a-zA-Z0-9\-_ ]`) when the user controls the filename and it's resolved against a base directory.
