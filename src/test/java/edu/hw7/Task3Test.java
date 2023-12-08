package edu.hw7;

import edu.hw7.Task3.Person;
import edu.hw7.Task3.Task3;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Task3Test {

    @Test
    void task3Test(){
        Task3 database = new Task3();

        Person person1 = new Person(1, "John", "123 Main St", "555-1234");
        Person person2 = new Person(2, "Alice", "456 Oak St", "555-5678");
        Person person3 = new Person(3, "Bob", "789 Pine St", "555-9012");

        database.add(person1);
        database.add(person2);
        database.add(person3);

        List<Person> foundByName = database.findByName("Alice");
        assertIterableEquals(List.of(person2), foundByName);

        List<Person> foundByAddress = database.findByAddress("123 Main St");
        assertIterableEquals(List.of(person1), foundByAddress);

        List<Person> foundByPhone = database.findByPhone("555-9012");
        assertIterableEquals(List.of(person3), foundByPhone);

        database.delete(2);

        List<Person> foundByNameAfterDeletion = database.findByName("Alice");
        assertIterableEquals(List.of(), foundByNameAfterDeletion);
    }

    @Test
    void task35Test(){
        Task35 database = new Task35();

        Person person1 = new Person(1, "John", "123 Main St", "555-1234");
        Person person2 = new Person(2, "Alice", "456 Oak St", "555-5678");
        Person person3 = new Person(3, "Bob", "789 Pine St", "555-9012");

        database.add(person1);
        database.add(person2);
        database.add(person3);

        List<Person> foundByName = database.findByName("Alice");
        assertIterableEquals(List.of(person2), foundByName);

        List<Person> foundByAddress = database.findByAddress("123 Main St");
        assertIterableEquals(List.of(person1), foundByAddress);

        List<Person> foundByPhone = database.findByPhone("555-9012");
        assertIterableEquals(List.of(person3), foundByPhone);

        database.delete(2);

        List<Person> foundByNameAfterDeletion = database.findByName("Alice");
        assertIterableEquals(List.of(), foundByNameAfterDeletion);
    }

}
