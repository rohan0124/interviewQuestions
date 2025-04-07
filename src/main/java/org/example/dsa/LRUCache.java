package org.example.dsa;
import java.util.concurrent.*;

class LRUCache {
    ConcurrentHashMap<Integer,Node> map;
    int capacity;
    Node first;
    Node last;

    public LRUCache(int capacity) {
        map = new ConcurrentHashMap<>();
        this.capacity=capacity;
        first = new Node(-1,-1);
        last = new Node(-1,-1);
        last.prev= first;
        first.next=last;
    }

    public int get(int key) {
        Node node = map.compute(key,(k,val)-> val);
        if(node==null){
            return -1;
        }else{
            removeConnection(node);
            addToFront(node);
            return node.value;
        }
    }

    public void put(int key, int value) {
        Node node = map.compute(key,(k,val)-> val==null? new Node(key,value): val);
        if(node.next!=null){
            node.value=value;
            removeConnection(node);
            addToFront(node);
        }else{
            addToFront(node);
        }
        checkAndEvict();
    }
    void checkAndEvict(){
        if(map.size()>capacity){
            Node toDelete = first.next;
            removeConnection(toDelete);
            map.compute(toDelete.key,(k,v)->null);
        }
    }
    void removeConnection(Node node){
        node.prev.next= node.next;
        node.next.prev = node.prev;
        node.next=null;
        node.prev=null;
    }
    void addToFront(Node node){
        last.prev.next=node;
        node.prev= last.prev;
        last.prev= node;
        node.next= last;
    }
    class Node{
        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
