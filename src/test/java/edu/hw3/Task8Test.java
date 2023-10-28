package edu.hw3;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Task8Test {
    @Test
    public void testHasNext() {
        Task8<Integer> iterator = new Task8<>(List.of(1, 2, 3));
        assertTrue(iterator.hasNext());
    }

    @Test
    public void testNext() {
        Task8<Integer> iterator = new Task8<>(List.of(1, 2, 3));
        assertEquals(3, iterator.next());
        assertEquals(2, iterator.next());
        assertEquals(1, iterator.next());
    }
}
