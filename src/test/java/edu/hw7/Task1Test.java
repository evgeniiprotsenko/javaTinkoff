package edu.hw7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task1Test {
    Task1 task1 = new Task1();
    @Test
    void run() throws InterruptedException {
        int expected = 0;
        int threadCount = 10_000;
        Thread[] threads = new Thread[threadCount];
        for(int i=0; i<threadCount; i++){
            int incrementCount = i;
            expected +=i;
            threads[i] = new Thread(() -> task1.run(incrementCount));
            threads[i].start();
        }
        for (Thread thread : threads){
            thread.join();
        }
        assertEquals(expected, task1.getResult());
    }
}
