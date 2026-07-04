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
## 2026-05-27 - Enforce secure file permissions on log files
**Vulnerability:** Error log file (`error.log`) containing sensitive application data (stack traces) was created with default, overly permissive file permissions.
**Learning:** Even files not explicitly considered "user data" (like error logs) can leak sensitive internal state and must have their permissions restricted upon creation.
**Prevention:** Always explicitly set restrictive permissions (e.g. owner-only readable/writable) on log files containing sensitive data via `File#setReadable(false, false)` followed by `File#setReadable(true, true)` upon file creation.
## 2026-05-28 - Enforce secure file permissions on local data files
**Vulnerability:** Newly created user deck files (`.json`) were being created with default OS permissions, potentially allowing other users on the same system to read or modify sensitive flashcard data.
**Learning:** `Files.newBufferedWriter(file)` relies on the system umask and does not restrict file access. User data files stored locally should always default to restrictive permissions to minimize the risk of local data exposure.
**Prevention:** Explicitly set strict, owner-only file permissions using `java.io.File` methods (`f.setReadable(false, false)`, etc.) conditionally wrapped inside `f.createNewFile()` prior to writing sensitive user data.
## 2026-07-04 - Fix TOCTOU in file/directory creation
**Vulnerability:** File and directory creation logic used insecure sequences like `File.createNewFile()` followed by `File.setReadable()`. This creates a Time-Of-Check to Time-Of-Use (TOCTOU) window where an attacker could replace the file with a symbolic link before permissions are tightened, potentially overwriting arbitrary files or stealing sensitive data (Symlink Race attack).
**Learning:** Legacy Java `java.io.File` methods are susceptible to race conditions. Securing files after creation is fundamentally flawed on multi-user systems.
**Prevention:** Always use `java.nio.file.Files.createFile` and `Files.createDirectories` with `PosixFilePermissions` to create files with secure attributes atomically. Always catch `UnsupportedOperationException` to gracefully fallback on non-POSIX systems like Windows, applying legacy permissions within a conditional check of `createNewFile()`. Also use `LinkOption.NOFOLLOW_LINKS` with `Files.newOutputStream`.
