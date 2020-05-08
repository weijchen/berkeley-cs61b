public class Deque<T> implements IDeque<T>{
    /** Doc for DLLNode */
    public class DLLNode<T> {
        public T item;
        private DLLNode<T> prev;
        private DLLNode<T> next;

        public DLLNode(T item, DLLNode prev, DLLNode next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }

        public DLLNode<T> getNext() {
            return this.next;
        }

        public DLLNode<T> getPrev() {
            return this.prev;
        }
    }

    /** Docs for LinkedListDeque */
    public DLLNode<T> sentinel;
    private int size;

    public Deque() {
        sentinel = new DLLNode(null, new DLLNode(null, null, null), new DLLNode(null, null, null));
        size = 0;
    }

    /** Add first item to the front of deque */
    @Override
    public void addFirst(T item) {
        if (isEmpty()) {
            sentinel.next = new DLLNode(item, sentinel, sentinel);
            sentinel.prev = sentinel.next;
        } else {
            sentinel.next = new DLLNode(item, sentinel, sentinel.next);
            sentinel.next.next.prev = sentinel.next;
        }
        size += 1;
    }

    @Override
    public void addLast(T item) {
        if (isEmpty()) {
            sentinel.next = new DLLNode(item, sentinel, sentinel);
            sentinel.prev = sentinel.next;
        } else {
            sentinel.prev = new DLLNode(item, sentinel.prev, sentinel);
            sentinel.prev.prev.next = sentinel.prev;
        }
        size += 1;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0)
            return true;
        else
            return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        if (isEmpty()) {
            System.out.println("Empty list, no nodes to be printed!");
        } else {
            DLLNode<T> p = sentinel.next;
            while (p.item != null) {
                System.out.print(p.item + " ");
                p = p.next;
            }
        }
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            System.out.println("Empty list, no nodes to be removed!");
            return null;
        } else {
            DLLNode<T> p = sentinel.next;
            sentinel.next = p.next;
            p.next.prev = sentinel;
            size -= 1;
            return p.item;
        }
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            System.out.println("Empty list, no nodes to be removed!");
            return null;
        } else {
            DLLNode<T> p = sentinel.prev;
            p = p.prev;
            p.prev.next = sentinel;
            size -= 1;
            return p.item;
        }
    }

    @Override
    public T get(int index) {
        if (isEmpty()) {
            System.out.println("Empty list, no nodes to be got!");
            return null;
        } else {
            DLLNode<T> p = sentinel;
            while (index >= 0) {
                p = p.next;
                index -= 1;
            }
            return p.item;
        }
    }

    public T getRecursive(int index) {
        if (isEmpty() || index - 1 < 0) {
            System.out.println("Error index, no nodes to be got!");
            return null;
        } else {
            return getRecursive(index, sentinel);
        }
    }

    public T getRecursive(int i, DLLNode<T> node) {
        if (i == 0) {
            return node.next.item;
        } else {
            return getRecursive(i - 1, node.next);
        }
    }
}