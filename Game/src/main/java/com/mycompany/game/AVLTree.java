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

public class AVLTree {
    private AVLNode root;

    public AVLTree() {
        root = null;
    }

    private int height(AVLNode node) {
        return node == null ? 0 : node.height;
    }

    private int getBalance(AVLNode node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    private AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    private AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    public void insert(Item item) {
        root = insert(root, item);
    }

    private AVLNode insert(AVLNode node, Item item) {
        if (node == null) return new AVLNode(item);

        if (item.getName().compareToIgnoreCase(node.item.getName()) < 0)
            node.left = insert(node.left, item);
        else
            node.right = insert(node.right, item);

        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balance = getBalance(node);

        if (balance > 1 && item.getName().compareToIgnoreCase(node.left.item.getName()) < 0)
            return rightRotate(node);
        if (balance < -1 && item.getName().compareToIgnoreCase(node.right.item.getName()) > 0)
            return leftRotate(node);
        if (balance > 1 && item.getName().compareToIgnoreCase(node.left.item.getName()) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && item.getName().compareToIgnoreCase(node.right.item.getName()) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    public Item search(String name) {
        AVLNode found = search(root, name);
        return found != null ? found.item : null;
    }

    private AVLNode search(AVLNode node, String name) {
        if (node == null || node.item.getName().equalsIgnoreCase(name))
            return node;

        if (name.compareToIgnoreCase(node.item.getName()) < 0)
            return search(node.left, name);
        else
            return search(node.right, name);
    }

    public List<Item> toList() {
        List<Item> result = new ArrayList<>();
        inOrder(root, result);
        return result;
    }

    private void inOrder(AVLNode node, List<Item> list) {
        if (node != null) {
            inOrder(node.left, list);
            list.add(node.item);
            inOrder(node.right, list);
        }
    }
}
