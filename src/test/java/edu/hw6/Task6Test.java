package edu.hw6;

import org.junit.jupiter.api.Test;

import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class Task6Test {

    Task6 task6 = new Task6();

    @Test
    void scanPorts() {

        Map<Integer, String> result = task6.scanPorts();
        assertNotNull(result);

        result.forEach((port, info) -> System.out.println(info));

    }
}
