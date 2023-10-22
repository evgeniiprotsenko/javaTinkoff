package edu.hw2.task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class PopularCommandExecutor {
    private final ConnectionManager manager;
    private final int maxAttempts;
    private final static Logger MYLOGGER = LogManager.getLogger();
    private final static String SLASH = " | ";
    private final static String REASON = ". Reason: ";

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() {
        try {
            tryExecute("apt update && apt upgrade -y");
        } catch (ConnectionException exp) {
            logException(exp, manager, exp.getMessage() + REASON + exp.getCause().getMessage());
        }
    }

    void tryExecute(String command) {
        boolean throwException = true;
        try {
            for (int i = 0; i < maxAttempts; i++) {
                logInfo(getClass(), "Trying to execute, attempt - " + (i + 1));
                Connection connection = manager.getConnection();
                connection.execute(command);
                if (connection.getClass().equals(StableConnection.class)) {
                    throwException = false;
                    break;
                }
            }
        } catch (Exception exp) {
            logException(exp, manager, exp.getMessage() + REASON + exp.getCause().getMessage());
            throwException = false;
        }

        if (throwException) {
            throw new ConnectionException("Attempts exception", new RuntimeException("Max numbers of attempts."));
        }
    }

    public static void logException(Exception exception, ConnectionManager manager, String cause) {
        MYLOGGER.error("Exception: " + exception.getClass().getSimpleName() + " | Manager:"
            + manager.getClass().getSimpleName() + SLASH + cause);
    }

    public static void logInfo(Class<?> callingClass, String infoMessage) {
        MYLOGGER.info("Info: " + callingClass.getSimpleName() + SLASH + infoMessage);
    }
}
