import java.awt.*;

public class SLList {
    private IntNode first;
    private int size;

    /** Creates an empty SLList */
    public SLList() {
        first = new IntNode(0, null);
        size = 0;
    }

    /** Traditional SLList */
    public SLList(int x) {
        first = new IntNode(x, null);
        size = 1;
    }

    /** Display the node value in given list */
    public void printList() {
        IntNode p = this.first;
        for (int i = 0; i < this.size; i++) {
            System.out.print(p.value + " ");
            p = p.next;
        }
        System.out.println("");
    }

    /** Insert node to the first position of the list */
    public void addFirst(int x) {
        first = new IntNode(x, first);
        size += 1;
    }

    public int getFirst() {
        return first.value;
    }

    /** Insert node to the last position of the given list */
    public void addLast(int x) {
        size += 1;

        IntNode p = first;
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
        slist.printList();

        slist.addLast(30);
        IntNode p = slist.first;
        System.out.println("SList size is: " + slist.size(p));
        System.out.println("SList size is: " + slist.sizeIterative());
        slist.printList();
        slist.addLast(40);
        System.out.println("SList size is: " + slist.size(p));
        System.out.println("SList size is: " + slist.sizeIterative());
        slist.printList();
    }
}