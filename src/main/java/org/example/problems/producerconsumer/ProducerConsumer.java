package org.example.problems.producerconsumer;

public class ProducerConsumer {
    public static void main(String[] args) {
        testProducerConsumer();
    }
    public static void testProducerConsumer() {
        SharedQueue queue = new SharedQueue(5);
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);

        producerThread.start();
        consumerThread.start();
    }
}
