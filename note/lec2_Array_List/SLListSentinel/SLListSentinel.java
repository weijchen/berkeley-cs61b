public class SLListSentinel {
    private IntNode sentinel;
    private int size;

    /** Creates an empty SLList */
    public SLListSentinel() {
        sentinel = new IntNode(0, null);
        size = 0;
    }

    /** Traditional SLList */
    public SLListSentinel(int x) {
        sentinel = new IntNode(0, new IntNode(x, null));
        size = 1;
    }

    public void addFirst(int x) {
        sentinel.next = new IntNode(x, sentinel.next);
        size += 1;
    }

    public int getFirst() {
        return sentinel.next.item;
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
            return 0;
        }
        return 1 + size(p.next);
    }

    /** Return size of SLList using recursive */
    public int sizeIterative() {
        IntNode p = sentinel;
        int ret = 0;
        while (p.next != null) {
            ret += 1;
            p = p.next;
        }
        return ret;
    }

    public void printList() {
        IntNode p = sentinel.next;
        for (int i = 0; i < size; i++) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        int x = 5;
        SLListSentinel slist = new SLListSentinel(x);
        System.out.println("First value of slist: " + slist.getFirst());
        slist.addFirst(10);
        System.out.println("First value of slist: " + slist.getFirst());
        slist.addFirst(20);
        System.out.println("First value of slist: " + slist.getFirst());
        slist.printList();

        slist.addLast(30);
        IntNode p = slist.sentinel;
        System.out.println("SList size is: " + slist.size(p));
        System.out.println("SList size is: " + slist.sizeIterative());
        slist.printList();
        slist.addLast(40);
        System.out.println("SList size is: " + slist.size(p));
        System.out.println("SList size is: " + slist.sizeIterative());
        slist.printList();
    }
}