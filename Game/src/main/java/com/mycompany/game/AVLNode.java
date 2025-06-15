/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.game;

/**
 *
 * @author User
 */
public class AVLNode {
    Item item;
    int height;
    AVLNode left, right;

    public AVLNode(Item item) {
        this.item = item;
        this.height = 1;
    }
}
