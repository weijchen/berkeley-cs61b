package synthesizer;

import javax.swing.table.TableStringConverter;

public abstract class AbstractBoundedQueue<T> implements IBoundedQueue<T> {
    protected int fillCount;
    protected int capacity;

    public int capacity() {
        return capacity;
    };
    public int fillCount() {
        return fillCount;
    };
    public boolean isEmpty() {
        return IBoundedQueue.super.isEmpty();
    }
    public boolean isFull() {
        return IBoundedQueue.super.isFull();
    }
    public abstract T peek();
    public abstract T dequeue();
    public abstract void enqueue(T x);

}
