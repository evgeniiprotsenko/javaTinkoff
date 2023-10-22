package edu.project1;

import org.jetbrains.annotations.NotNull;

public class Session {
    private final String answer;
    private final char[] userAnswer;
    private final int maxAttempts;
    private int attempts;

    public Session(String answer, int maxAttempts) {
        this.answer = answer;
        this.userAnswer = new char[answer.length()];
        for (int i = 0; i < userAnswer.length; i++) {
            userAnswer[i] = '*';
        }
        this.maxAttempts = maxAttempts;
        this.attempts = 0;
    }

    @NotNull GuessResult guess(char guess) {
        boolean found = false;

        for (int i = 0; i < answer.length(); i++) {
            if (answer.charAt(i) == guess) {
                userAnswer[i] = guess;
                found = true;
            }
        }

        if (found) {
            if (String.valueOf(userAnswer).equals(answer)) {
                return new GuessResult.Win(userAnswer, attempts, maxAttempts, "Congratulations! You've won!");
            } else {
                return new GuessResult.SuccessfulGuess(userAnswer, attempts, maxAttempts, "Good guess!");
            }
        } else {
            attempts++;
            if (attempts >= maxAttempts) {
                return new GuessResult.Defeat(
                    userAnswer,
                    attempts,
                    maxAttempts,
                    "You've used all your attempts. You lose."
                );
            } else {
                return new GuessResult.FailedGuess(userAnswer, attempts, maxAttempts, "Incorrect guess. Try again.");
            }
        }
    }

    @NotNull GuessResult giveUp() {
        return new GuessResult.Defeat(
            answer.toCharArray(),
            attempts,
            maxAttempts,
            "You gave up."
        );
    }

    public String getAnswer() {
        return answer;
    }
}
