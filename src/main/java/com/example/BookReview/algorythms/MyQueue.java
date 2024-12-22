package com.example.BookReview.algorythms;

import java.util.*;

public class MyQueue {


    public static void simpleQueue() {
        Queue<String> pending = new LinkedList<>();

        Collection<String> strings = new ArrayList<>(Arrays.asList("Apple", "Banana", "Cherry"));

        // Enqueue
        pending.offer("A");
        pending.offer("B");
        pending.offer("C");

        pending.offer("D");
        pending.add("E");
        pending.addAll(strings);

        // Dequeue
        System.out.println("Dequeue: " + pending.poll()); // Gibt "A" zurück

        // Peek
        System.out.println("Peek: " + pending.peek()); // Gibt "B" zurück

        // Remaining elements in the queue
        System.out.println("Remaining Queue: " + pending); // Gibt [B, C] zurück
    }




}
