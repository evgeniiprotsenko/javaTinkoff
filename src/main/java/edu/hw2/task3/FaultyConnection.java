package edu.hw2.task3;

import java.util.Random;
import static edu.hw2.task3.PopularCommandExecutor.MYLOGGER;

public class FaultyConnection implements Connection {
    private final Random random = new Random();

    @Override
    public void execute(String command) {

        MYLOGGER.info(getClass() + " - connection failed.");
        if (random.nextBoolean()) {
            throw new ConnectionException("Throwing an expression", new Throwable());
        }
    }

    @Override
    public void close() throws Exception {
        MYLOGGER.info("Connection closed(" + getClass() + ").");
    }
}
