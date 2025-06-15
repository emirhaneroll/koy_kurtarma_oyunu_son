/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.game;

/**
 *
 * @author User
 */
import java.util.*;

public class VillageQueue {
    private Queue<Village> queue;

    public VillageQueue() {
        queue = new LinkedList<>();
    }

    public void enqueue(Village v) {
        queue.add(v);
    }

    public Village dequeue() {
        return queue.poll();
    }

    public Village peek() {
        return queue.peek();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int size() {
        return queue.size();
    }

    public List<Village> getAll() {
        return new ArrayList<>(queue);
    }
}
