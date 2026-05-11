## 2024-05-06 - Improve Empty States for CLI app
**Learning:** CLI applications often leave users stranded when there is no data to show (e.g. empty states in listing or studying). This hurts accessibility and user experience as they are left guessing what to do next. Adding explicit, actionable instructions inside empty states provides clear pathways for user success.
**Action:** Always include a clear call-to-action (like "Try creating one using option 2") in CLI empty state messages instead of just saying "No items found".

## 2026-05-11 - Add CLI Study Session Escape Hatch
**Learning:** In CLI applications, users can easily feel trapped if they enter a long-running interactive loop (like studying a large deck of cards) without an explicit way out. Forcing users to terminate the entire application unnaturally is a poor user experience.
**Action:** Always provide and document an explicit escape hatch (like pressing 'q' to quit) for any long-running loops or interactive sessions, ensuring the user can gracefully exit.
