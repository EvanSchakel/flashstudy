## 2024-05-06 - Improve Empty States for CLI app
**Learning:** CLI applications often leave users stranded when there is no data to show (e.g. empty states in listing or studying). This hurts accessibility and user experience as they are left guessing what to do next. Adding explicit, actionable instructions inside empty states provides clear pathways for user success.
**Action:** Always include a clear call-to-action (like "Try creating one using option 2") in CLI empty state messages instead of just saying "No items found".

## 2026-05-18 - Escape Hatches for Interactive CLI Loops
**Learning:** Interactive CLI loops (like a study session over many cards) can easily trap the user without a way out, forcing an unnatural exit (like Ctrl+C). This is an accessibility and UX anti-pattern as it removes user agency. Providing a clearly labeled escape hatch at every prompt is essential.
**Action:** Always provide and document an explicit escape hatch (e.g. "or 'q' to quit") for long-running interactive CLI prompts. Ensure exit logic updates statistics correctly based on items actually attempted.
