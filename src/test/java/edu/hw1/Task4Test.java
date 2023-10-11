package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task4Test {

    @Test
    @DisplayName("123456 -> 214365")
    public void test1() {
        String input = "123456";
        String result = Task4.fixString(input);
        assertThat(result).isEqualTo("214365");
    }

    @Test
    @DisplayName("hTsii  s aimex dpus rtni.g -> This is a mixed up string.")
    public void test2() {
        String input = "hTsii  s aimex dpus rtni.g";
        String result = Task4.fixString(input);
        assertThat(result).isEqualTo("This is a mixed up string.");
    }

    @Test
    @DisplayName("badce -> abcde")
    public void test3() {
        String input = "badce";
        String result = Task4.fixString(input);
        assertThat(result).isEqualTo("abcde");
    }

    @Test
    @DisplayName("1 -> 1")
    public void test4() {
        String input = "1";
        String result = Task4.fixString(input);
        assertThat(result).isEqualTo("1");
    }

}