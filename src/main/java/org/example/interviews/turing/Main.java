package org.example.turing;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello, World!");
        testWith5Threads();


    }

    public static void testWith5Threads() throws InterruptedException {
//        ThreadBlock threadBlock = new ThreadBlock(5);
//        Consumer consumer = new Consumer();
//
//        for( int i = 0; i < 5; i++ ){
//            Thread t1 = threadBlock.getThread();
//            t1.start();
//            consumer.getThreads().add(threadBlock.getThread());
//        }
//        for(Thread thread : consumer.getThreads()){
//            System.out.println(thread.getName());
//        }

        Solution solution = new Solution();
        String input ="ABA";
        int totalUniqueCount = solution.solve(input);
        System.out.println("Total unique count: " + totalUniqueCount);


    }
}