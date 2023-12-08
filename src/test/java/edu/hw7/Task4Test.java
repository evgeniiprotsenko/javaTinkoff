package edu.hw7;

import org.junit.jupiter.api.Test;

class Task4Test {
    Task4 task4 = new Task4();

    @Test
    void runSingleAndMultiThreadedSimulation() throws InterruptedException {

        long singleThreadedStart = System.nanoTime();
        double singleThreadedPi = task4.runSingleThreadedSimulation();
        long singleThreadedEnd = System.nanoTime();
        long singleTime = singleThreadedEnd - singleThreadedStart;

        long multiThreadedStart = System.nanoTime();
        double multiThreadedPi = task4.runMultiThreadedSimulation();
        long multiThreadedEnd = System.nanoTime();
        long multiTime = multiThreadedEnd - multiThreadedStart;

        System.out.println("Single-threaded Pi: " + singleThreadedPi);
        System.out.println("Single-threaded Time: " + (double) singleTime / 1_000_000 + " ms");

        System.out.println("Multi-threaded Pi: " + multiThreadedPi);
        System.out.println("Multi-threaded Time: " + (double) multiTime / 1_000_000 + " ms");

        //assertTrue(multiTime < singleTime);
        //double speedup = (double) singleTime / multiTime;
        //System.out.println("Multi-threaded в " + speedup + " раз быстрее, чем Single-threaded");
    }
}
