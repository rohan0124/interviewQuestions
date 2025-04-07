package org.example.turing;

import java.util.ArrayList;
import java.util.List;

public class Consumer {
    List<Thread> threads = new ArrayList<>();

    public List<Thread> getThreads() {
        return threads;
    }

    public void setThreads(List<Thread> threads) {
        this.threads = threads;
    }
    void release(Thread thread) {
        thread.notifyAll();
    }
}
