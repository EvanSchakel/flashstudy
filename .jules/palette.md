## 2024-05-06 - Improve Empty States for CLI app
**Learning:** CLI applications often leave users stranded when there is no data to show (e.g. empty states in listing or studying). This hurts accessibility and user experience as they are left guessing what to do next. Adding explicit, actionable instructions inside empty states provides clear pathways for user success.
**Action:** Always include a clear call-to-action (like "Try creating one using option 2") in CLI empty state messages instead of just saying "No items found".
## 2026-05-13 - Add explicit escape hatches to CLI interactive prompts
**Learning:** Long-running CLI interactive loops can trap users, forcing unnatural exits if no clear escape hatch is provided.
**Action:** Always provide and clearly document an explicit escape hatch (e.g., pressing 'q' to quit) in CLI interactive prompts, particularly in long-running loops.
