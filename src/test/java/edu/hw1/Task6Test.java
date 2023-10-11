package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task6Test {

    Task6 task6 = new Task6();

    @Test
    @DisplayName("6621 -> 5")
    public void test1() {
        int input = 6621;
        int result = task6.countK(input);
        assertThat(result).isEqualTo(5);
    }

    @Test
    @DisplayName("6554 -> 4")
    public void test2() {
        int input = 6554;
        int result = task6.countK(input);
        assertThat(result).isEqualTo(4);
    }

    @Test
    @DisplayName("1234 -> 3")
    public void test3() {
        int input = 1234;
        int result = task6.countK(input);
        assertThat(result).isEqualTo(3);
    }
}