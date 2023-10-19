package edu.hw2;

import edu.hw2.task3.Connection;
import edu.hw2.task3.ConnectionManager;
import edu.hw2.task3.DefaultConnectionManager;
import edu.hw2.task3.FaultyConnectionManager;
import edu.hw2.task3.PopularCommandExecutor;
import edu.hw2.task3.StableConnection;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task3Test {
    PopularCommandExecutor popularCommandExecutor = new PopularCommandExecutor(new DefaultConnectionManager(), 3);

    @Test
    public void test1() {
        ConnectionManager manager = new FaultyConnectionManager();
        int attempts = 3;

        PopularCommandExecutor executor = new PopularCommandExecutor(manager, attempts);
        executor.updatePackages(attempts);
    }

}
