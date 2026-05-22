## 2024-05-06 - Improve Empty States for CLI app
**Learning:** CLI applications often leave users stranded when there is no data to show (e.g. empty states in listing or studying). This hurts accessibility and user experience as they are left guessing what to do next. Adding explicit, actionable instructions inside empty states provides clear pathways for user success.
**Action:** Always include a clear call-to-action (like "Try creating one using option 2") in CLI empty state messages instead of just saying "No items found".

## 2024-05-22 - Add Escape Hatches for Interactive Prompts
**Learning:** Long-running loops in CLI interactive prompts (like study sessions) can trap users if they want to exit early, leading to unnatural exits (like closing the terminal or forcing an interrupt). Providing an explicit escape hatch avoids trapping the user.
**Action:** Always provide and clearly document an explicit escape hatch (e.g., pressing 'q' to quit) for CLI interactive prompts and loops.
