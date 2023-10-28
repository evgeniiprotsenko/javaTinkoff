package edu.project1;

import java.util.Random;
import org.jetbrains.annotations.NotNull;

public class StaticDictionary implements Dictionary {
    private final String[] words = {
        "Apple",
        "Banana",
        "Car",
        "Dog",
        "Elephant",
        "Flower",
        "Book",
        "House",
        "Ocean",
        "Bird"
    };
    private final Random randomWord = new Random();

    @Override
    public @NotNull String randomWord() {
        int randomIndex = randomWord.nextInt(words.length);
        return words[randomIndex].toLowerCase();
    }
}
