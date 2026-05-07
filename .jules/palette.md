## 2024-05-06 - Improve Empty States for CLI app
**Learning:** CLI applications often leave users stranded when there is no data to show (e.g. empty states in listing or studying). This hurts accessibility and user experience as they are left guessing what to do next. Adding explicit, actionable instructions inside empty states provides clear pathways for user success.
**Action:** Always include a clear call-to-action (like "Try creating one using option 2") in CLI empty state messages instead of just saying "No items found".
## 2025-05-07 - CLI Interactive Sessions Need Escape Hatches
**Learning:** In CLI applications, trapping users in long-running loops (like a 100-card study session) without an explicit exit command leads to frustration and forces unnatural quits (Ctrl+C).
**Action:** Always provide and clearly document a way to exit early from iterative interactive prompts (e.g., "Press Enter to reveal answer (or 'q' to quit)...").
