package edu.hw2;

import edu.hw2.task3.ConnectionManager;
import edu.hw2.task3.DefaultConnectionManager;
import edu.hw2.task3.FaultyConnectionManager;
import edu.hw2.task3.PopularCommandExecutor;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

class Task3Test {

    @ParameterizedTest
    @MethodSource("managerAndAttemptsProvider")
    public void testUpdatePackages(ConnectionManager manager, int attempts) {
        PopularCommandExecutor executor = new PopularCommandExecutor(manager, attempts);
        executor.updatePackages();
    }

    static Stream<Arguments> managerAndAttemptsProvider() {
        return Stream.of(
            Arguments.of(new DefaultConnectionManager(), 3),
            Arguments.of(new FaultyConnectionManager(), 3)
        );
    }
}
