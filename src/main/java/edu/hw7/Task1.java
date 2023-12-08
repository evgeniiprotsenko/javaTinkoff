package edu.hw7;

import java.util.concurrent.atomic.AtomicInteger;

public class Task1 implements Runnable {
    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public void run() {
        atomicInteger.incrementAndGet();
    }

    public void run(int n) {
        if (n <= 0) {
            return;
        }
        for (int i = 0; i < n; i++) {
            run();
        }
    }

    public int getResult() {
        return atomicInteger.get();
    }
}
