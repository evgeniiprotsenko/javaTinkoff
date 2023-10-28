package edu.hw3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class Task5Test {

    Task5 task5 = new Task5();
    @Test
    public void testParseContactsASC() {
        String[] input = {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"};
        String type = "ASC";
        String[] expectedOutput = {"Thomas Aquinas", "Rene Descartes", "David Hume", "John Locke"};

        String[] result = task5.parseContacts(input, type);

        assertArrayEquals(expectedOutput, result);
    }

    @Test
    public void testParseContactsDESC() {
        String[] input = {"Paul Erdos", "Leonhard Euler", "Carl Gauss"};
        String type = "DESC";
        String[] expectedOutput = {"Carl Gauss", "Leonhard Euler", "Paul Erdos"};

        String[] result = task5.parseContacts(input, type);

        assertArrayEquals(expectedOutput, result);
    }

    @Test
    public void testParseContactsEmpty() {
        String[] input = new String[0];
        String type = "DESC";
        String[] expectedOutput = new String[0];

        String[] result = task5.parseContacts(input, type);

        assertArrayEquals(expectedOutput, result);
    }

    @Test
    public void testParseContactsNull() {
        String[] input = null;
        String type = "DESC";
        String[] expectedOutput = new String[0];

        String[] result = task5.parseContacts(input, type);

        assertArrayEquals(expectedOutput, result);
    }

    @Test
    public void testParseContactsNoSurname() {
        String[] input = {"John Locke", "Thomas", "David Hume", "Rene Descartes"};
        String type = "ASC";
        String[] expectedOutput = {"Rene Descartes", "David Hume", "John Locke", "Thomas"};

        String[] result = task5.parseContacts(input, type);

        assertArrayEquals(expectedOutput, result);
    }
}
