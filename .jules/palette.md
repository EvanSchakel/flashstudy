## 2024-05-06 - Improve Empty States for CLI app
**Learning:** CLI applications often leave users stranded when there is no data to show (e.g. empty states in listing or studying). This hurts accessibility and user experience as they are left guessing what to do next. Adding explicit, actionable instructions inside empty states provides clear pathways for user success.
**Action:** Always include a clear call-to-action (like "Try creating one using option 2") in CLI empty state messages instead of just saying "No items found".

## 2024-05-22 - Add Escape Hatches for Interactive Prompts
**Learning:** Long-running loops in CLI interactive prompts (like study sessions) can trap users if they want to exit early, leading to unnatural exits (like closing the terminal or forcing an interrupt). Providing an explicit escape hatch avoids trapping the user.
**Action:** Always provide and clearly document an explicit escape hatch (e.g., pressing 'q' to quit) for CLI interactive prompts and loops.

## 2024-05-27 - Explicit Validation for CLI Boolean Prompts
**Learning:** Naive default fallbacks in CLI boolean prompts (like treating any non-'y' input as 'no') can cause accidental negative actions when users unintentionally submit empty inputs (like double-tapping 'Enter').
**Action:** Force explicit validation loops requiring exact inputs (e.g., 'y' or 'n') for critical boolean prompts to prevent accidental actions.

## 2024-05-28 - Treat empty CLI string input as safe cancellation
**Learning:** For CLI inputs, empty string inputs can cause `NumberFormatException`s or unwanted errors when users unintentionally press Enter. Handling these empty inputs properly avoids error spam.
**Action:** For CLI inputs using `Scanner`, explicitly handle empty string inputs (`s.isEmpty()`) to prevent exceptions on subsequent parsing operations, treating them as safe cancellations or defaults instead of application errors.

## 2024-05-29 - Explicit Entity Names in CLI Feedback
**Learning:** Generic success or failure messages (like "Deck created" or "Card added") in CLI apps lack context, leaving users unsure if their specific action succeeded on the correct item, which negatively impacts accessibility and confidence.
**Action:** Always include the specific entity's name or a strong identifier (e.g., "Deck 'Math' created") in success or error feedback messages to reassure users and improve context.
