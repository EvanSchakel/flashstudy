## 2024-05-06 - Improve Empty States for CLI app
**Learning:** CLI applications often leave users stranded when there is no data to show (e.g. empty states in listing or studying). This hurts accessibility and user experience as they are left guessing what to do next. Adding explicit, actionable instructions inside empty states provides clear pathways for user success.
**Action:** Always include a clear call-to-action (like "Try creating one using option 2") in CLI empty state messages instead of just saying "No items found".

## 2024-05-16 - Add Escape Hatches for CLI Long-Running Loops
**Learning:** In long-running CLI tasks such as study sessions, users can feel trapped if there isn't an explicit way out. Forcing unnatural exits like Ctrl+C ruins the user experience and leaves sessions unrecorded. Furthermore, failing to base completion statistics dynamically on actual user attempts can result in mathematical bugs, like division-by-zero errors when they quit immediately.
**Action:** Always provide and clearly document an explicit escape hatch (e.g., 'q' to quit) in CLI interactive loops and track "attempted" items to calculate accurate completion statistics upon early exit.
