package synthesizer;
// TODO: Make sure to make this class a part of the synthesizer package
// package <package name>;
import java.util.Iterator;

//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        first = 0;
        last = 0;
        fillCount = 0;
        this.capacity = capacity;
        rb = (T[]) new Object[capacity];
    }

    /** Change last to 0 when two cases
     * 1) last is at the last position
     * 2) last is at zero and fillCount is zero
     * */

    private int addOne(int x) {
        if (x + 1 == this.capacity) {
            return 0;
        }
        return x + 1;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }

        rb[last] = x;
        last = addOne(last);
        fillCount += 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
//    last     0 1 2 3
//    fillCount 1 2 3 4
//             n n n n
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T placeholder = rb[first];
        rb[first] = null;
        first = addOne(first);
        fillCount -= 1;
        return placeholder;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        // TODO: Return the first item. None of your instance variables should change.
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }

    @Override
    public Iterator<T> iterator() {
        return new BufferIterator();
    }

    private class BufferIterator implements Iterator<T>{
        private int magicWizard = 0;


        @Override
        public boolean hasNext() {
            return magicWizard == fillCount();
        }

        @Override
        public T next() {
            T returnValue = rb[magicWizard];
            magicWizard += 1;
            return returnValue;
        }
    }

    // TODO: When you get to part 5, implement the needed code to support iteration.
}
