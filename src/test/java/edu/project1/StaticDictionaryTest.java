package edu.project1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class StaticDictionaryTest {
    private final String[] words = {
        "apple",
        "banana",
        "car",
        "dog",
        "elephant",
        "flower",
        "book",
        "house",
        "ocean",
        "bird"
    };

    @Test
    void randomWord() {
        StaticDictionary dictionary = new StaticDictionary();
        String randomWord = dictionary.randomWord();
        assertThat(randomWord).isIn((Object[]) words);
    }
}
