package org.example;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        Set<Character> set = new HashSet<>(List.of('a','e','i','o','u'));
        System.out.println(set);
    }
}