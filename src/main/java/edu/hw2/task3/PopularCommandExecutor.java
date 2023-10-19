package edu.hw2.task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class PopularCommandExecutor {
    private final ConnectionManager manager;
    private final int maxAttempts;
    public final static Logger MYLOGGER = LogManager.getLogger();

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages(int attempts) {
        try {
            tryExecute("apt update && apt upgrade -y");
        } catch (ConnectionException exp) {
            MYLOGGER.info("Catching the " + ConnectionException.class + " at using " + manager.getClass());
            MYLOGGER.info("Text of exception - " + exp.getMessage() + " - " + exp);
        }
    }

    void tryExecute(String command) {
        boolean throwException = true;
        try {
            for (int i = 0; i < maxAttempts; i++) {
                Connection connection = manager.getConnection();
                connection.execute(command);
                if (connection.getClass().equals(StableConnection.class)) {
                    throwException = false;
                    return;
                }
            }
        } catch (ConnectionException exp) {
            MYLOGGER.info("Catching the " + ConnectionException.class + " at using " + manager.getClass());
            MYLOGGER.info("Text of exception - " + exp.getMessage());
        }

        if (throwException) {
            throw new ConnectionException("Catching the " + ConnectionException.class + ": too little attempts", new Throwable());
        }
    }
}
