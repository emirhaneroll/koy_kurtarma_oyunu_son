/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.game;

/**
 *
 * @author User
 */
public class Village {
    private String name;
    private AVLTree inventory;
    private boolean isLiberated;

    public Village(String name) {
        this.name = name;
        this.inventory = new AVLTree();
        this.isLiberated = false;
    }

    public void addItem(Item item) {
        inventory.insert(item);
    }

    public boolean hasItem(String name) {
        return inventory.search(name) != null;
    }

    public String getName() {
        return name;
    }

    public boolean isLiberated() {
        return isLiberated;
    }

    public void liberate() {
        isLiberated = true;
    }

    public AVLTree getInventory() {
        return inventory;
    }
}

