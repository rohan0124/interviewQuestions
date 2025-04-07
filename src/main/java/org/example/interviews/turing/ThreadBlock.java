package org.example.turing;

public class ThreadBlock {
    int sizeLimit;
    int currentCount;

    public ThreadBlock(int sizeLimit) {
        this.sizeLimit = sizeLimit;
        this.currentCount = 0;
    }
    Thread getThread(){
        if(currentCount > sizeLimit){
            throw new OutOfMemoryError();
        }

        Thread thread = new  Thread("Thread " + currentCount++);
        return thread;
    }


}

