package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task4Test {

    Task4 task4 = new Task4();

    @Test
    @DisplayName("123456 -> 214365")
    public void test1() {
        String input = "123456";
        String result = task4.fixString(input);
        assertThat(result).isEqualTo("214365");
    }

    @Test
    @DisplayName("hTsii  s aimex dpus rtni.g -> This is a mixed up string.")
    public void test2() {
        String input = "hTsii  s aimex dpus rtni.g";
        String result = task4.fixString(input);
        assertThat(result).isEqualTo("This is a mixed up string.");
    }

    @Test
    @DisplayName("badce -> abcde")
    public void test3() {
        String input = "badce";
        String result = task4.fixString(input);
        assertThat(result).isEqualTo("abcde");
    }

    @Test
    @DisplayName("1 -> 1")
    public void test4() {
        String input = "1";
        String result = task4.fixString(input);
        assertThat(result).isEqualTo("1");
    }

}