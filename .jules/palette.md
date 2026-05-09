## 2024-05-06 - Improve Empty States for CLI app
**Learning:** CLI applications often leave users stranded when there is no data to show (e.g. empty states in listing or studying). This hurts accessibility and user experience as they are left guessing what to do next. Adding explicit, actionable instructions inside empty states provides clear pathways for user success.
**Action:** Always include a clear call-to-action (like "Try creating one using option 2") in CLI empty state messages instead of just saying "No items found".

## 2024-05-09 - Explicit Escape Hatches in CLI Loops
**Learning:** In CLI applications, trapping users in long-running loops (like a study session over many flashcards) without an explicit way out creates a frustrating and inaccessible experience, forcing unnatural termination (like Ctrl+C).
**Action:** Always provide and clearly document an explicit escape hatch (e.g., "Press 'q' to quit") for any continuous or long-running interactive prompts.
