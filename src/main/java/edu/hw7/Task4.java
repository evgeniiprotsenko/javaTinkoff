package edu.hw7;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

public class Task4 {

    private static final int NUM_SIMULATIONS = 100_000_000;
    private static final int NUM_THREADS = Runtime.getRuntime().availableProcessors();
    private static final AtomicLong TOTAL_COUNT = new AtomicLong(0);
    private static final AtomicLong CIRCLE_COUNT = new AtomicLong(0);

    public double runSingleThreadedSimulation() {
        TOTAL_COUNT.set(0);
        CIRCLE_COUNT.set(0);
        for (int i = 0; i < NUM_SIMULATIONS; i++) {
            double x = ThreadLocalRandom.current().nextDouble();
            double y = ThreadLocalRandom.current().nextDouble();

            if (isInsideCircle(x, y)) {
                CIRCLE_COUNT.incrementAndGet();
            }

            TOTAL_COUNT.incrementAndGet();
        }
        return calculatePi(CIRCLE_COUNT.get(), TOTAL_COUNT.get());
    }

    public double runMultiThreadedSimulation() throws InterruptedException {
        TOTAL_COUNT.set(0);
        CIRCLE_COUNT.set(0);

        Thread[] threads = new Thread[NUM_THREADS];
        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i] = new Thread(this::runSimulationPart);
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
        return calculatePi(CIRCLE_COUNT.get(), TOTAL_COUNT.get());
    }

    private void runSimulationPart() {
        long localTotalCount = 0;
        long localCircleCount = 0;

        for (int i = 0; i < NUM_SIMULATIONS / NUM_THREADS; i++) {
            double x = ThreadLocalRandom.current().nextDouble();
            double y = ThreadLocalRandom.current().nextDouble();

            if (isInsideCircle(x, y)) {
                localCircleCount++;
            }

            localTotalCount++;
        }

        TOTAL_COUNT.addAndGet(localTotalCount);
        CIRCLE_COUNT.addAndGet(localCircleCount);
    }

    private static final double PI_RATE = 4.0;

    private double calculatePi(long circleCount, long totalCount) {
        return PI_RATE * circleCount / totalCount;
    }

    private static boolean isInsideCircle(double x, double y) {
        return x * x + y * y <= 1.0;
    }
}
