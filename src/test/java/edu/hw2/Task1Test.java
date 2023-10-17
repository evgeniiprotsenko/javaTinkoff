package edu.hw2;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task1Test {

    Task1 task1 = new Task1();
    @Test
    public void test1() {
        String output = "Addition[firstConstant=Exponent[constant=Multiplication" +
            "[firstConstant=Addition[firstConstant=Constant[value=2.0], " +
            "secondConstant=Constant[value=4.0]], secondConstant=Negate[constant=Constant[value=1.0]]], " +
            "rate=2], secondConstant=Constant[value=1.0]] = 37.0";
        String result = task1.check(2,4,1,2,1);
        assertThat(result).isEqualTo(output);
    }

}
