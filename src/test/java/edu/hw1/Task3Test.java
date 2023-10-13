package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task3Test {

    Task3 task3 = new Task3();

    @Test
    @DisplayName("[1, 2, 3, 4], [0, 6] -> true")
    public void test1() {
        int[] a1 = {1, 2, 3, 4};
        int[] a2 = {0, 6};
        boolean result = task3.isNestable(a1, a2);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("[3, 1], [4, 0] -> true")
    public void test2() {
        int[] a1 = {3, 1};
        int[] a2 = {4, 0};
        boolean result = task3.isNestable(a1, a2);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("[9, 9, 8], [8, 9] -> false")
    public void test3() {
        int[] a1 = {9, 9, 8};
        int[] a2 = {8, 9};
        boolean result = task3.isNestable(a1, a2);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("[1, 2, 3, 4], [2, 3] -> false")
    public void test4() {
        int[] a1 = {1, 2, 3, 4};
        int[] a2 = {2, 3};
        boolean result = task3.isNestable(a1, a2);
        assertThat(result).isFalse();
    }

}