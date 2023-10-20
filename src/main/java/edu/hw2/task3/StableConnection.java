package edu.hw2.task3;

import static edu.hw2.task3.PopularCommandExecutor.logInfo;

public class StableConnection implements Connection {
    private final Class<?> currentClass = getClass();

    @Override
    public void execute(String command) {
        logInfo(currentClass, "Connection completed.");
        logInfo(currentClass, "Command: \"" + command + "\" executed.");
        close();
    }

    @Override
    public void close() {
        logInfo(currentClass, "Connection closed.");
    }
}
