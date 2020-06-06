package com.company;

public class SLList<Item> {
    private ItemNode sentinel;
    private int size;

    /** Creates an empty SLList */
    public SLList() {
        sentinel = new ItemNode(0, null);
        size = 0;
    }

    /** Traditional SLList */
    public SLList(Item x) {
        sentinel = new ItemNode(x, new ItemNode(x, null));
        size = 1;
    }

    /** Display the node value in given list */
    public void printList() {
        ItemNode p = sentinel.next;
        for (int i = 0; i < size; i++) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println("");
    }

    /** Insert node to the sentinel position of the list */
    public void addFirst(Item x) {
        sentinel.next = new ItemNode(x, sentinel.next);
        size += 1;
    }

    public Object getFirst() {
        return sentinel.next.item;
    }

    /** Insert node to the last position of the given list */
    public void addLast(Item x) {
        size += 1;

        ItemNode p = sentinel;
        while (p.next != null) {
            p = p.next;
        }
        p.next = new ItemNode(x, null);
    }

    /** Return size of SLList using recursive */
    public int size(ItemNode p) {
        if (p.next == null) {
            return 0;
        }
        return 1 + size(p.next);
    }

    private ItemNode getLastNode() {
        ItemNode p = sentinel;

        while (p.next != null) {
            p = p.next;
        }
        return p;
    }

    public Item removeLast() {
        ItemNode back = getLastNode();
        if (back == sentinel) {
            return null;
        }

        size = size - 1;
        ItemNode p = sentinel;

        while (p.next != back) {
            p = p.next;
        }
        p.next = null;
        return (Item) back.item;
    }

    /** Return size of SLList using recursive */
    public int sizeIterative() {
        ItemNode p = sentinel;
        int ret = 1;
        while (p.next != null) {
            ret += 1;
            p = p.next;
        }
        return ret;
    }

    public static void main(String[] args) {
        int x = 5;
        SLList slist = new SLList(x);
        System.out.println("First item of slist: " + slist.getFirst());
        slist.addFirst(10);
        System.out.println("First item of slist: " + slist.getFirst());
        slist.addFirst(20);
        System.out.println("First item of slist: " + slist.getFirst());
        slist.printList();

        slist.addLast(30);
        ItemNode p = slist.sentinel;
        System.out.println("SList size is: " + slist.size(p));
        System.out.println("SList size is: " + slist.sizeIterative());
        slist.printList();
        slist.addLast(40);
        System.out.println("SList size is: " + slist.size(p));
        System.out.println("SList size is: " + slist.sizeIterative());
        slist.printList();
    }
}