package edu.hw2.task3;

import java.util.Random;
import static edu.hw2.task3.PopularCommandExecutor.logInfo;

public class FaultyConnection implements Connection {

    private final Class<?> currentClass = getClass();
    private final Random randomException = new Random();

    @Override
    public void execute(String command) {
        logInfo(currentClass, "Connection failed.");
        if (randomException.nextBoolean()) {
            throw new ConnectionException("Random exception", new IllegalArgumentException("Bad random."));
        }
        close();
    }

    @Override
    public void close() {
        logInfo(currentClass, "Connection closed.");
    }
}
