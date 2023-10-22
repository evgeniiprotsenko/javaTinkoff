package edu.hw2.task3;

import java.util.Random;

public class DefaultConnectionManager implements ConnectionManager {
    private final Random random = new Random();

    @Override
    public Connection getConnection() {
        if (random.nextBoolean()) {
            return new FaultyConnection();
        } else {
            return new StableConnection();
        }
    }
}
