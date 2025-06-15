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

public class Inventory {
    private final int maxCapacity = 10;
    private LinkedList<Item> items;
    private AVLTree bst;

    public Inventory() {
        items = new LinkedList<>();
        bst = new AVLTree();
    }

    public boolean push(Item item) {
        if (items.size() >= maxCapacity)
            return false;

        items.add(item);
        bst.insert(item);
        return true;
    }

    public Item pop(String name) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(name)) {
                items.remove(item);
                return item;
            }
        }
        return null;
    }

    public void displayInventory() {
        Map<String, Integer> counts = new HashMap<>();
        for (Item item : items) {
            counts.put(item.getName(), counts.getOrDefault(item.getName(), 0) + 1);
        }
        counts.forEach((k, v) -> System.out.println(k + " x" + v));
    }

    public boolean useItem(String name) {
        Item item = pop(name);
        if (item != null) {
            System.out.println(name + " kullanildi.");
            return true;
        } else {
            System.out.println(name + " bulunamadi.");
            return false;
        }
    }

    public Item searchItem(String name) {
        return bst.search(name);
    }

    public int getSize() {
        return items.size();
    }

    public int getTotalPower() {
        return items.stream().mapToInt(Item::getPower).sum();
    }

    // ✅ Bu metod Kristalkoy guc oyunu icin gereklidir
    public List<Item> getItems() {
        return new ArrayList<>(items);
    }
}
