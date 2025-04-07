package org.example.problems.producerconsumer;

import java.time.Instant;
import java.util.LinkedList;
import java.util.Queue;

public class SharedQueue {
    final Queue<Integer> queue;
    int capacity;
    volatile boolean isConsumerWaiting = false; // Shared flag for logging consumer state

    public SharedQueue(int capacity) {
        this.queue = new LinkedList<>();
        this.capacity = capacity;

        // Start logger thread
        Thread loggerThread = new Thread(() -> {
            try {
                while (true) {
                    if (isConsumerWaiting) {
                        System.out.println("Consumer is waiting to consume..." + Instant.ofEpochMilli(System.currentTimeMillis()).toString()
                        );
                    }
                    Thread.sleep(1000); // Log every 1 second
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        loggerThread.setDaemon(true); // Ensure logger thread ends with the main program
        loggerThread.start();
    }

    public Integer poll() throws InterruptedException {
        synchronized (queue) {
            isConsumerWaiting = true; // Set waiting flag
            while (isEmpty()) {
                queue.wait(100); // Consumer waits if queue is empty
            }
            isConsumerWaiting = false; // Reset waiting flag
            Integer value = queue.poll();
            queue.notify(); // Notify producer about available space
            return value;
        }
    }

    public void offer(Integer i) throws InterruptedException {
        synchronized (queue) {
            while (isFull()) {
                queue.wait(); // Producer waits if queue is full
            }
            queue.offer(i);
            queue.notify(); // Notify consumer about available item
        }
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public boolean isFull() {
        return queue.size() >= capacity;
    }
}
