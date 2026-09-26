# Word Counter App (MADT Lab #2)

An Android application written in Java that calculates text metrics for user-entered text, generates test sentences, and maintains a runtime calculation history.

## Features
- **Text Metrics Calculation:** Calculates the number of sentences, words, punctuation marks, and numbers.
- **Reusable Architecture:** All metric calculations are implemented in a separate, reusable `TextMetricsCalculator` class.
- **Test Sentence Generator:** Allows cycling through pre-configured sample sentences with a single tap.
- **Input Validation:** Checks whether the input field is empty and notifies the user.
- **Runtime Calculation History:** Displays all calculations performed during the current runtime and preserves state across screen rotations.
- **Localization Ready:** All GUI text elements are externalized in `res/values/strings.xml`.

## Example Output
- `"Good morning" => 1, 2, 0, 0`
- `"The war started on September 1. Sad." => 2, 7, 2, 1`

---

## AI Workflow & Separate Sessions Log

In accordance with the lab requirements, the application was developed across four separate AI sessions without manual code editing:

### Session 1: Planning (`/grill-me`) & Initial Code Generation
- **Instruction used:** `/grill-me`
- **Clarifications resolved:**
  1. Target language and package name (`Java`, `com.example.myapplication`).
  2. Metric calculation rules (Sentences, Words, Punctuation marks, Numbers) implemented in a separate reusable `TextMetricsCalculator` class.
  3. Empty input notification handled via both `EditText.setError()` and `Toast`.
  4. All GUI text and sample test sentences externalized to `res/values/strings.xml`.

### Session 2: Code Review & Two Highest-Priority Changes
A separate AI session reviewed the initial codebase and implemented the two highest-priority improvements:
1. **Regex Pre-compilation & Decimal Handling (`TextMetricsCalculator.java`):** Converted regex patterns into `private static final Pattern` constants and updated sentence splitting logic so decimal numbers are not split as separate sentences.
2. **Runtime State Preservation (`MainActivity.java`):** Implemented `onSaveInstanceState` to preserve calculation history (`historyList`) and current metric results across screen rotations.

### Session 3: User Documentation (`README.md`)
- Generated user documentation and repository description in a dedicated AI session.

### Session 4: GitHub Version Control
- Executed sequential commits (`Initial code generation`, `Code review`, and `Add README.md`) and pushed to the remote GitHub repository.
