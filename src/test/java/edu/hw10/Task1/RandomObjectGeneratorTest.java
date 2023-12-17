package edu.hw10.Task1;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import static org.junit.jupiter.api.Assertions.*;

class RandomObjectGeneratorTest {

    @Test
    void nextObjectClass()
        throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        RandomObjectGenerator randomObjectGenerator = new RandomObjectGenerator();
        MyClass result = randomObjectGenerator.nextObject(MyClass.class, "");

        assertTrue(result.getId() >= 5 && result.getId() <= 10);
    }

    @Test
    void nextObjectRecord()
        throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        RandomObjectGenerator randomObjectGenerator = new RandomObjectGenerator();
        MyRecord result = randomObjectGenerator.nextObject(MyRecord.class);

        assertNotNull(result);
    }
}
