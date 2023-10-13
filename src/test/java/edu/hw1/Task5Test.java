package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task5Test {

    Task5 task5 = new Task5();

    @Test
    @DisplayName("11211230 -> true")
    public void test1() {
        int input = 11211230;
        boolean result = task5.isPalindromeDescendant(input);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("13001120 -> true")
    public void test2() {
        int input = 13001120;
        boolean result = task5.isPalindromeDescendant(input);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("23336014 -> true")
    public void test3() {
        int input = 23336014;
        boolean result = task5.isPalindromeDescendant(input);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("11 -> true")
    public void test4() {
        int input = 11;
        boolean result = task5.isPalindromeDescendant(input);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("1234 -> false")
    public void test5() {
        int input = 1234;
        boolean result = task5.isPalindromeDescendant(input);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("123 -> false")
    public void test6() {
        int input = 123;
        boolean result = task5.isPalindromeDescendant(input);
        assertThat(result).isFalse();
    }
}