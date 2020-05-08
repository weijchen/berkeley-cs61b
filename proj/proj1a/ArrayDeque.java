/* Invariants:
  addLast: The next item we want to add, will go into position size
  size: add one after addLast
 */
public class ArrayDeque<T> {
    /** Docs for ArrayDeque */
    private int size;
    private T[] items;
    private static final double USAGE_RATIO = 0.25;
    public static final int START_SIZE = 8;

    public ArrayDeque() {
        items =  (T[])new Object[START_SIZE];
        size = 0;
    }

    public void resize() {
        int newSize = this.size * 2;
        T[] newArr = (T[])new Object[newSize];
        System.arraycopy(this.items, 0, newArr, 0, this.size);
        items = newArr;
    }

    public void usageResize(double usage) {
        if (usage < USAGE_RATIO) {
            T[] newArr = (T[])new Object[size];
            System.arraycopy(items, 0, newArr, 0, size);
            items = newArr;
        }
    }
    
    public void addFirst(T item) {
        if (size == items.length) {
            resize();
        }
        
        if (size == 0)
            items[0] = item;
        else
            items[size] = item;
        size += 1;
    }
    
    public void addLast(T item) {
        if (size == items.length) {
            resize();
        }
        items[size] = item;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < items.length; i++) {
            System.out.print(items[i] + " ");
        }
        System.out.println("");
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        
        T first = get(0);
        T[] newItems = (T[])new Object[size-1];
        System.arraycopy(items, 1, newItems, 0, size-1);
        size -= 1;
        items = newItems;
        double usage = size/items.length;
        usageResize(usage);
        return first;
    }
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T last = get(size-1);
        items[size - 1] = null;
        size -= 1;
        double usage = size/items.length;
        usageResize(usage);
        return last;
    }
    public T get(int index) {
        if (isEmpty()) {
            return null;
        }
        return items[index];
    }
}