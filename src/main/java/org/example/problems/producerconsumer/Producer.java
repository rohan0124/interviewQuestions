package org.example.problems.producerconsumer;

public class Producer implements Runnable {
    private final SharedQueue queue;

    public Producer(SharedQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            try {
                System.out.println("Producing  : " + i);
                queue.offer(i);
                Thread.sleep((long) (Math.random()*10000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
