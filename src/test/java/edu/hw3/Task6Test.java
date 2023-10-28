package edu.hw3;

import edu.hw3.Task6.Stock;
import edu.hw3.Task6.Task6;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task6Test {
    Task6 task6 = new Task6();

    @Test
    public void testAdd() {
        Stock appleStock = new Stock("Apple", 150.0);
        task6.add(appleStock);
        Stock mostValuable = task6.mostValuableStock();
        assertEquals(appleStock, mostValuable);
    }

    @Test
    public void testRemove() {
        Stock appleStock = new Stock("Apple", 150.0);
        task6.remove(appleStock);
        Stock mostValuable = task6.mostValuableStock();
        assertNull(mostValuable);
    }

    @Test
    public void testMostValuableStock() {
        Stock appleStock = new Stock("Apple", 150.0);
        Stock googleStock = new Stock("Google", 200.0);
        Stock amazonStock = new Stock("Amazon", 300.0);

        task6.add(appleStock);
        task6.add(googleStock);
        task6.add(amazonStock);

        Stock mostValuable = task6.mostValuableStock();
        assertEquals(amazonStock, mostValuable);
    }
}
