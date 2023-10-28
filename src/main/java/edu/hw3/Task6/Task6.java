package edu.hw3.Task6;

import java.util.PriorityQueue;

public class Task6 implements StockMarket {
    private final PriorityQueue<Stock> priorityQueue;

    public Task6() {
        priorityQueue = new PriorityQueue<>(new StockComparator());
    }

    @Override
    public void add(Stock stock) {
        priorityQueue.offer(stock);
    }

    @Override
    public void remove(Stock stock) {
        priorityQueue.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        return priorityQueue.peek();
    }
}
