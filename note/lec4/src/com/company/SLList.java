package com.company;

public class SLList {
    private IntNode sentinel;
    private int size;

    /** Creates an empty SLList */
    public SLList() {
        sentinel = new IntNode(0, null);
        size = 0;
    }

    /** Traditional SLList */
    public SLList(int x) {
        sentinel = new IntNode(0, null);
        sentinel.next = new IntNode(x, null);
        size = 1;
    }

    public void addFirst(int x) {
        sentinel.next = new IntNode(x, sentinel.next);
        size += 1;
    }

    public int getFirst() {
        return sentinel.next.value;
    }

    public void addLast(int x) {
        size += 1;

        IntNode p = sentinel;
        while (p.next != null) {
            p = p.next;
        }
        p.next = new IntNode(x, null);
    }

    /** Return size of SLList using recursive */
    public int size(IntNode p) {
        if (p.next == null) {
            return 1;
        }

        return 1 + size(p.next);
    }

    /** Return size of SLList using recursive */
    public int sizeIterative() {
        IntNode p = first;
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
        System.out.println("First value of slist: " + slist.getFirst());
        slist.addFirst(10);
        System.out.println("First value of slist: " + slist.getFirst());
        slist.addFirst(20);
        System.out.println("First value of slist: " + slist.getFirst());

        slist.addLast(30);
        IntNode p = slist.first;
        System.out.println("SList size is: " + slist.size(p));
        System.out.println("SList size is: " + slist.sizeIterative());
        slist.addLast(40);
        System.out.println("SList size is: " + slist.size(p));
        System.out.println("SList size is: " + slist.sizeIterative());
    }
}