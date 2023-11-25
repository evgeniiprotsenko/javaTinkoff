package edu.hw7;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Task4 {

    private static final int NUM_SIMULATIONS = 1_000_000_000;
    private static final int NUM_THREADS = Runtime.getRuntime().availableProcessors();
    private static volatile long totalCount = 0;
    private static volatile long circleCount = 0;

    private static final Lock LOCK = new ReentrantLock();

    public double runSingleThreadedSimulation() {
        totalCount = 0;
        circleCount = 0;
        for (int i = 0; i < NUM_SIMULATIONS; i++) {
            double x = ThreadLocalRandom.current().nextDouble();
            double y = ThreadLocalRandom.current().nextDouble();

            if (isInsideCircle(x, y)) {
                circleCount++;
            }

            totalCount++;
        }
        return calculatePi(circleCount, totalCount);
    }

    public double runMultiThreadedSimulation() throws InterruptedException {
        totalCount = 0;
        circleCount = 0;

        Thread[] threads = new Thread[NUM_THREADS];
        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i] = new Thread(Task4::runSimulationPart);
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
        return calculatePi(circleCount, totalCount);
    }

    private static void runSimulationPart() {
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

        LOCK.lock();
        try {
            totalCount += localTotalCount;
            circleCount += localCircleCount;
        } finally {
            LOCK.unlock();
        }
    }

    private final static double PI_RATE = 4.0;

    private double calculatePi(long circleCount, long totalCount) {
        return PI_RATE * circleCount / totalCount;
    }

    private static boolean isInsideCircle(double x, double y) {
        return x * x + y * y <= 1.0;
    }
}
