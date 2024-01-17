package edu.hw7;

import edu.hw7.Task3.Person;
import edu.hw7.Task3.PersonDatabase;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Task35 implements PersonDatabase {
    private final Map<Integer, Person> peopleById = new HashMap<>();
    private final Map<String, List<Integer>> nameIndex = new HashMap<>();
    private final Map<String, List<Integer>> addressIndex = new HashMap<>();
    private final Map<String, List<Integer>> phoneIndex = new HashMap<>();

    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    public void add(Person person) {
        int id = person.id();
        lock.writeLock().lock();
        try {
            peopleById.put(id, person);
            addToIndex(nameIndex, person.name(), id);
            addToIndex(addressIndex, person.address(), id);
            addToIndex(phoneIndex, person.phoneNumber(), id);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void delete(int id) {
        lock.writeLock().lock();
        try {
            Person person = peopleById.get(id);
            if (person != null) {
                removeFromIndex(nameIndex, person.name(), id);
                removeFromIndex(addressIndex, person.address(), id);
                removeFromIndex(phoneIndex, person.phoneNumber(), id);

                peopleById.remove(id);
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public List<Person> findByName(String name) {
        lock.readLock().lock();
        try {
            return findByIndex(nameIndex, name);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByAddress(String address) {
        lock.readLock().lock();
        try {
            return findByIndex(addressIndex, address);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByPhone(String phone) {
        lock.readLock().lock();
        try {
            return findByIndex(phoneIndex, phone);
        } finally {
            lock.readLock().unlock();
        }
    }

    private void addToIndex(Map<String, List<Integer>> index, String key, int id) {
        index.computeIfAbsent(key, k -> new ArrayList<>()).add(id);
    }

    private void removeFromIndex(Map<String, List<Integer>> index, String key, int id) {
        List<Integer> ids = index.get(key);
        if (ids != null) {
            ids.remove(Integer.valueOf(id));
            if (ids.isEmpty()) {
                index.remove(key);
            }
        }
    }

    private List<Person> findByIndex(Map<String, List<Integer>> index, String key) {
        List<Integer> ids = index.get(key);
        if (ids != null) {
            List<Person> result = new ArrayList<>();
            for (int id : ids) {
                Person person = peopleById.get(id);
                if (person != null) {
                    result.add(person);
                }
            }
            return result;
        } else {
            return new ArrayList<>();
        }
    }
}
