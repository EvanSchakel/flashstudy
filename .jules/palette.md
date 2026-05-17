## 2024-05-06 - Improve Empty States for CLI app
**Learning:** CLI applications often leave users stranded when there is no data to show (e.g. empty states in listing or studying). This hurts accessibility and user experience as they are left guessing what to do next. Adding explicit, actionable instructions inside empty states provides clear pathways for user success.
**Action:** Always include a clear call-to-action (like "Try creating one using option 2") in CLI empty state messages instead of just saying "No items found".
## 2024-05-17 - Add escape hatch for study session
**Learning:** For interactive CLI loops like study sessions, users can easily feel trapped if there isn't a documented and clear way out. Additionally, calculation logic must handle premature exits correctly to avoid division-by-zero errors.
**Action:** Always provide an explicit escape hatch (like pressing 'q' to quit) in long-running CLI prompts and properly track the actual items processed instead of only the total items.
