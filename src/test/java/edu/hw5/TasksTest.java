package edu.hw5;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class TasksTest {

    Tasks tasks = new Tasks();

    @Test
    void getAverageTime() {
        List<String> input = List.of(
            "2022-03-12, 20:20 - 2022-03-12, 23:50",
            "2022-04-01, 21:30 - 2022-04-02, 01:20");
        String expected = "3ч 40м";
        String result = tasks.getAverageTime(input);
        assertEquals(expected,result);
    }

    @Test
    void getAllFriday13th() {
        int input = 2024;
        List<LocalDate> expected = List.of(
            LocalDate.of(2024,9,13),
            LocalDate.of(2024,12,13));
        List<LocalDate> result = tasks.getAllFriday13th(input);
        assertEquals(expected,result);
    }

    @Test
    void getNextFriday13th() {
        LocalDate input = LocalDate.of(2024,1,1);
        LocalDate expected = LocalDate.of(2024,9,13);
        LocalDate result = tasks.getNextFriday13th(input);
        assertEquals(expected,result);
    }

    @Test
    void parseDate() {
        String input = "5/5/23";
        Optional<LocalDate> expected = Optional.of(LocalDate.of(2023,5,5));
        Optional<LocalDate> result = tasks.parseDate(input);
        assertEquals(expected,result);
    }

    @Test
    void checkPassword() {
        String input = "abc";
        Boolean expected = false;
        Boolean result = tasks.checkPassword(input);
        assertEquals(expected,result);

        Boolean expected2 = true;
        Boolean result2 = tasks.checkPassword(input+"~");
        assertEquals(expected2,result2);
    }

    @Test
    void isValidCarNumber() {
        String input = "А123ВЕ777";
        Boolean expected = true;
        Boolean result = tasks.isValidCarNumber(input);
        assertEquals(expected,result);

        String input2 = "А123ВГ77";
        Boolean expected2 = false;
        Boolean result2 = tasks.isValidCarNumber(input2);
        assertEquals(expected2,result2);
    }

    @Test
    void isSubLine() {
        String input1 = "achfdbaabgabcaabg";
        String input2 = "abc";
        Boolean expected = true;
        Boolean result = tasks.isSubLine(input1, input2);
        assertEquals(expected,result);
    }

    @Test
    void isValidString() {
        String input = "100110";
        Boolean expected = true;
        Boolean result = tasks.isValidString(input);
        assertEquals(expected,result);

        String input2 = "00101";
        Boolean expected2 = false;
        Boolean result2 = tasks.isValidString(input2);
        assertEquals(expected2,result2);
    }

    @Test
    void isValidStringBonus() {
        String input = "101";
        Boolean expected = true;
        Boolean result = tasks.isValidStringBonus(input);
        assertEquals(expected,result);
    }
}
