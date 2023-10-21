package edu.hw2;

import edu.hw2.task4.CallingInfo;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task4Test {

    @Test
    void testCallingInfoMethod1() {
        CallingInfo callingInfo = CallingInfo.getCallingInfo();
        assertThat(callingInfo.className()).isEqualTo("Task4Test");
    }

    @Test
    void testCallingInfoMethod2() {
        CallingInfo callingInfo = CallingInfo.getCallingInfo();
        assertThat(callingInfo.methodName()).isEqualTo("testCallingInfoMethod2");
    }

}
