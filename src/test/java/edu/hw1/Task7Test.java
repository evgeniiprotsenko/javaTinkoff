package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task7Test {

    Task7 task7 = new Task7();

    @Test
    @DisplayName("rotateRight(8, 1) -> 4")
    public void test1() {
        int input = 8;
        int shift = 1;
        int result = task7.rotateRight(input, shift);
        assertThat(result).isEqualTo(4);
    }

    @Test
    @DisplayName("rotateLeft(16, 1) -> 1")
    public void test2() {
        int input = 16;
        int shift = 1;
        int result = task7.rotateLeft(input, shift);
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("rotateLeft(17, 2) -> 6")
    public void test3() {
        int input = 17;
        int shift = 2;
        int result = task7.rotateLeft(input, shift);
        assertThat(result).isEqualTo(6);
    }

    @Test
    @DisplayName("rotateRight(5, 4) -> 5")
    public void test4() {
        int input = 5;
        int shift = 4;
        int result = task7.rotateRight(input, shift);
        assertThat(result).isEqualTo(5);
    }
}