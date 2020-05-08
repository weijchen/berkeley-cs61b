package com.company;

/**
 * IntNode class:
 * int vale
 * IntNode next
 */
public class ItemNode<Item> {
    public Item item;
    public ItemNode next;

    /** Constructor for IntNode */
    public ItemNode(Item item, ItemNode next) {
        this.item = item;
        this.next = next;
    }
}