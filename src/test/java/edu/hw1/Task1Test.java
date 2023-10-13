package edu.hw1;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task1Test {
    Task1 task1 = new Task1();

    @Test
    @DisplayName("01:00 -> 60")
    public void test1() {
        String input = "01:00";
        int result = task1.minutesToSeconds(input);
        assertThat(result).isEqualTo(60);
    }

    @Test
    @DisplayName("13:56 -> 836")
    public void test2() {
        String input = "13:56";
        int result = task1.minutesToSeconds(input);
        assertThat(result).isEqualTo(836);
    }

    @Test
    @DisplayName("10:60 -> -1")
    public void test3() {
        String input = "10:60";
        int result = task1.minutesToSeconds(input);
        assertThat(result).isEqualTo(-1);
    }

    @Test
    @DisplayName("AB -> -1")
    public void test4() {
        String input = "AB";
        int result = task1.minutesToSeconds(input);
        assertThat(result).isEqualTo(-1);
    }

    @Test
    @DisplayName("12: -> -1")
    public void test5() {
        String input = "12:";
        int result = task1.minutesToSeconds(input);
        assertThat(result).isEqualTo(-1);
    }

    @Test
    @DisplayName(":30 -> -1")
    public void test6() {
        String input = ":30";
        int result = task1.minutesToSeconds(input);
        assertThat(result).isEqualTo(-1);
    }
}