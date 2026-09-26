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