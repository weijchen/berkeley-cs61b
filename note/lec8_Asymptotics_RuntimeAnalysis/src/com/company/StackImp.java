package com.company;

import java.util.LinkedList;
import java.util.List;

public class StackImp {

    /* Inheriting, should know what is going on in the parent class */
    public static class ExtensionStack<Item> extends LinkedList<Item> {
        public void push(Item x) {
            add(x);
        }
    }

    /* Delegation is accomplished by passing in a class, do not want the be a version of the superclass */
    public class DelegationStack<Item> {
        private LinkedList<Item> L = new LinkedList<>();
        public void push(Item x) {
            L.add(x);
        }
    }

    public class StackAdapter<Item> {
        private List L;
        public StackAdapter(List<Item> worker) {    // suitable for any class that implements the List interface (Linked List, ArrayList, etc)
            L = worker;
        }
        public void push(Item x) {
            L.add(x);
        }
    }
}