## 2024-05-06 - Improve Empty States for CLI app
**Learning:** CLI applications often leave users stranded when there is no data to show (e.g. empty states in listing or studying). This hurts accessibility and user experience as they are left guessing what to do next. Adding explicit, actionable instructions inside empty states provides clear pathways for user success.
**Action:** Always include a clear call-to-action (like "Try creating one using option 2") in CLI empty state messages instead of just saying "No items found".

## 2024-05-14 - Improve Escape Hatches for Interactive CLI Prompts
**Learning:** In interactive CLI applications, especially within long-running loops like study sessions, users can feel trapped if they must complete the entire sequence before exiting. Forcing users to rely on unnatural exits (like Ctrl+C) degrades the user experience and can lead to lost data. Additionally, gracefully handling edge cases, such as an early exit before any progress is made, prevents calculation errors like division-by-zero.
**Action:** Always provide an explicit and clearly documented escape hatch (e.g., "type 'q' to quit") during interactive prompts. Ensure that early exits gracefully handle empty states by checking attempt counts before calculating completion statistics.
