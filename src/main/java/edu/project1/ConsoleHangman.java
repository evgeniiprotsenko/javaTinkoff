package edu.project1;

@SuppressWarnings({"RegexpSinglelineJava"})

public class ConsoleHangman {
    private final int maxAttempts = 3;

    public void run() {
        String wordToGuess = new StaticDictionary().randomWord();
        Session session = new Session(wordToGuess, maxAttempts);

        System.out.println("Welcome to Hangman!");
        System.out.println("The word has " + wordToGuess.length() + " letters.");

        while (true) {
            char guess;
            System.out.print("Enter a letter (for giving up, type '0'): ");
            try {
                int input = System.in.read();
                System.in.skip(2);
                guess = (char) input;
            } catch (Exception e) {
                guess = '\0';
            }

            if (guess == '0') {
                GuessResult result = session.giveUp();
                printResult(result, session);
                break;
            }

            GuessResult result = session.guess(guess);
            printResult(result, session);

            if (result instanceof GuessResult.Defeat || result instanceof GuessResult.Win) {
                break;
            }
            System.out.println("Attempts left: " + (maxAttempts - result.attempt()));
            System.out.println("Current status: " + String.valueOf(result.state()));
        }
    }

    private void printResult(GuessResult result, Session session) {
        System.out.println(result.message());
        if (result instanceof GuessResult.Defeat || result instanceof GuessResult.Win) {
            System.out.println("The word was: " + session.getAnswer());
        }
    }
}
