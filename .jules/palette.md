## 2024-05-06 - Improve Empty States for CLI app
**Learning:** CLI applications often leave users stranded when there is no data to show (e.g. empty states in listing or studying). This hurts accessibility and user experience as they are left guessing what to do next. Adding explicit, actionable instructions inside empty states provides clear pathways for user success.
**Action:** Always include a clear call-to-action (like "Try creating one using option 2") in CLI empty state messages instead of just saying "No items found".

## 2024-05-20 - Escape Hatches in CLI Loops
**Learning:** Interactive CLI prompts, especially in long-running loops like study sessions, can trap the user if they want to exit early, leading to unnatural application kills. Providing an explicit escape hatch (like pressing 'q') improves control. Additionally, when a user exits early, statistics must be calculated against attempted items rather than the total dataset size to provide accurate feedback and avoid mathematical errors like division-by-zero.
**Action:** Always provide and document an explicit escape hatch for long-running CLI loops, and dynamically track attempted items for accurate completion statistics.
