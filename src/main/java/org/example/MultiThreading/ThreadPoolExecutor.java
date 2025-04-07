package org.example.MultiThreading;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class ThreadPoolExecutor {
    public static void main(String[] args) throws InterruptedException {

        int count = Runtime.getRuntime().availableProcessors();
        System.out.println("creating threadpool of size : "+(count-1));
        ThreadPool threadPool = ThreadPool.getInstance(count-1);

        // Submitting tasks to ThreadPool
        for (int i = 1; i <= 100; i++) {
            Task task = new Task("Task-" + i);
            threadPool.submitTask(task);
        }

        System.out.println("threadpool awaiting completion ");
        threadPool.awaitCompletion();
        // Shutting down the thread pool
        threadPool.shutdown();
        System.out.println("threadpool shutdown completed");
    }

}
class ThreadPool{
    private static ThreadPool instance;
    BlockingQueue<Runnable> queue;
    AtomicBoolean isShutdownInitiated;
    final Thread[] workers;
    private final CountDownLatch latch;

    private ThreadPool(int noOfThreads){
        queue = new LinkedBlockingQueue<>();
        workers = new Thread[noOfThreads];
        latch = new CountDownLatch(noOfThreads);
        isShutdownInitiated = new AtomicBoolean(false);
        for(int i=0; i<noOfThreads; i++){
            workers[i] = new Consumer(queue,isShutdownInitiated,latch);
            workers[i].start();
        }
    }
    public static ThreadPool getInstance(int noOfThreads){
        if(instance == null){
            instance = new ThreadPool(noOfThreads);
        }
        return instance;
    }
    public void submitTask(Runnable task){
        if(!isShutdownInitiated.get()){
            queue.add(task);
        }
    }
    public void shutdown(){
        isShutdownInitiated.set(true);
        for(Thread worker : workers){
            worker.interrupt();
        }
    }
    public void awaitCompletion() throws InterruptedException {
        while ( latch.getCount() > 0) {
            Thread.sleep(100); // Small delay to prevent busy waiting
        }
    }
}

class Consumer extends Thread{
    BlockingQueue<Runnable> queue;
    AtomicBoolean isShutdownInitiated;
    private final CountDownLatch latch;

    Consumer(BlockingQueue queue, AtomicBoolean isShutdownInitiated, CountDownLatch latch){
        this.queue = queue;
        this.isShutdownInitiated = isShutdownInitiated;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            while (!isShutdownInitiated.get() || !queue.isEmpty()) {
                try {
                    Runnable task = queue.take(); // Block until a task is available
                    task.run();
                } catch (InterruptedException e) {
                    if (isShutdownInitiated.get() && queue.isEmpty()) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            }
        } finally {
            latch.countDown();
        }
    }
}
class Task implements Runnable{
    private final String name;

    Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name + " task is running");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();        }
        System.out.println(name + " task is completed");
    }
}