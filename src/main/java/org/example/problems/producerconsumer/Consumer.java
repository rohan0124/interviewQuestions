package org.example.problems.producerconsumer;

public class Consumer implements Runnable {
    final private SharedQueue queue;
    public Consumer(SharedQueue queue) {
        this.queue = queue;
    }
    @Override
    public void run() {
        for(int i =1; i<=10; i++) {
            try {
                System.out.println("Consuming :" +  queue.poll() );
                Thread.sleep((long) (Math.random()*1000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
