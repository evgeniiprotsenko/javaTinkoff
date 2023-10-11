package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task2Test {

    Task2 task2 = new Task2();

    @Test
    @DisplayName("4666 -> 4")
    public void test1() {
        int number = 4666;
        int result = task2.countDigits(number);
        assertThat(result).isEqualTo(4);
    }

    @Test
    @DisplayName("544 -> 3")
    public void test2() {
        int number = 544;
        int result = task2.countDigits(number);
        assertThat(result).isEqualTo(3);
    }

    @Test
    @DisplayName("0 -> 1")
    public void test3() {
        int number = 0;
        int result = task2.countDigits(number);
        assertThat(result).isEqualTo(1);
    }
}