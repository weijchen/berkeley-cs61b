package com.company;

/**
 * A type of SLList that is able to remember removed values to prevent memory waste
 * @return VengefulSLList
 * */
public class VengefulSLList<Item> extends SLList<Item> {
    SLList<Item> deletedItems;

    public VengefulSLList() {
        deletedItems = new SLList<Item>();
    }

    public VengefulSLList(Item x) {
        super(x);
        deletedItems = new SLList<Item>();
    }

    public void printLostItems() {
        deletedItems.printList();
    }

    @Override
    public Item removeLast() {
        Item x = super.removeLast();    // calls Superclass's version of removeLast()
        deletedItems.addLast(x);
        return x;
    }

    public static void main(String[] args) {
        VengefulSLList<Integer> vs1 = new VengefulSLList<Integer>();
        vs1.addLast(1);
        vs1.addLast(5);
        vs1.addLast(10);
        vs1.addLast(13);
        // vs1 is now: [1, 5, 10, 13

        vs1.removeLast();
        vs1.removeLast();
        // After deletion, vs1 is: [1, 5]

        // Should print out the numbers of the fallen, namely 10 and 13.
        System.out.print("The fallen are: ");
        vs1.printLostItems();

    }

}
