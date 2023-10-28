package edu.project1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class SessionTest {

    @Test
    void testGuessSuccessfulState() {
        Session session = new Session("hello", 3);
        GuessResult result = session.guess('h');
        assertThat(String.valueOf(result.state())).isEqualTo("h****");
    }

    @Test
    void testGuessFailedState() {
        Session session = new Session("hello", 3);
        GuessResult result = session.guess('x');
        assertThat(String.valueOf(result.state())).isEqualTo("*****");
    }

    @Test
    void testGuessSuccessfulAttempt() {
        Session session = new Session("hello", 3);
        GuessResult result = session.guess('h');
        assertThat(result.attempt()).isEqualTo(0);
    }

    @Test
    void testGuessFailedAttempt() {
        Session session = new Session("hello", 3);
        GuessResult result = session.guess('x');
        assertThat(result.attempt()).isEqualTo(1);
    }

    @Test
    void testGuessDefeat() {
        Session session = new Session("hello", 1);
        GuessResult result = session.guess('x');
        assertThat(result).isInstanceOf(GuessResult.Defeat.class);
    }

    @Test
    void testGuessWin() {
        Session session = new Session("hello", 3);
        session.guess('h');
        session.guess('e');
        session.guess('l');
        GuessResult result = session.guess('o');
        assertThat(result).isInstanceOf(GuessResult.Win.class);
    }
}
