package org.example.turing;

import java.util.LinkedList;
import java.util.Queue;

public class StackUsingQueue<T> {
    Queue<T> inputQueue;
    Queue<T> temp;
    public StackUsingQueue() {
        inputQueue = new LinkedList<T>();
        temp = new LinkedList<T>();
    }
    void push(T value) {
        inputQueue.add(value);
    }
    T pop() {
        if(inputQueue.isEmpty()) {
            return null;
        }
        T value = inputQueue.poll();

        while(!inputQueue.isEmpty()) {
            temp.add(value);
            value = inputQueue.poll();

        }
        this.inputQueue =temp;
        this.temp = new LinkedList<T>();
        return value;
    }
}
