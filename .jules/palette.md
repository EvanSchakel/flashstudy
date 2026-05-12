## 2024-05-06 - Improve Empty States for CLI app
**Learning:** CLI applications often leave users stranded when there is no data to show (e.g. empty states in listing or studying). This hurts accessibility and user experience as they are left guessing what to do next. Adding explicit, actionable instructions inside empty states provides clear pathways for user success.
**Action:** Always include a clear call-to-action (like "Try creating one using option 2") in CLI empty state messages instead of just saying "No items found".
## 2024-05-12 - Explicit Escape Hatches for CLI Loops
**Learning:** For CLI interactive prompts, particularly long-running loops (e.g., study sessions), always provide and clearly document an explicit escape hatch (e.g., pressing 'q' to quit) to avoid trapping the user and forcing unnatural exits.
**Action:** When implementing long-running processes or loops in a CLI interface, verify that a clear opt-out path exists and is mentioned directly in the user prompts.
