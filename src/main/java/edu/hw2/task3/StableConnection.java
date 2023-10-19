package edu.hw2.task3;

import static edu.hw2.task3.PopularCommandExecutor.MYLOGGER;

public class StableConnection implements Connection {

    @Override
    public void execute(String command) {
        MYLOGGER.info(getClass() + " - connection completed.");
        MYLOGGER.info("Command: " + command + " - executed(" + getClass() + ").");
    }

    @Override
    public void close() throws Exception {
        MYLOGGER.info("Connection closed(" + getClass() + ").");
    }
}
