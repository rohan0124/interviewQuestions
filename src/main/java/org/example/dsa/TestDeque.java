package org.example.dsa;

import java.util.ArrayDeque;

public class TestDeque {
    /**
     * offer  - offerLast
     * poll - pollFirst
     * push - addFirst
     * pop - removeFirst
     * peek - peekFirst
     * peekLast
     */
    public static void main(String[] args) {
        ArrayDeque<String> deque = new ArrayDeque<>();
        deque.offer("a");
        print(deque);
        deque.offer("b");
        deque.offer("c");
        print(deque);
        deque.offerLast("d");
        print(deque);
        deque.poll();
        System.out.println(deque.peek());
        print(deque);
        deque.push("k");
        print(deque);

    }
    static void print(ArrayDeque<String> deque) {
        System.out.println(deque);
    }

}
