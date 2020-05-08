package synthesizer;

import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public static void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
        arb.enqueue("dog");
        arb.enqueue("cat");
        arb.enqueue("hi");
        arb.enqueue("you");
        Object x = arb.dequeue();
        arb.enqueue("dog");
        arb.enqueue("dog2");
        arb.enqueue("dog3");
        arb.enqueue("dog4");
        arb.enqueue("dog5");
        arb.enqueue("dog6");
        arb.enqueue("dog7");
        x = arb.dequeue();
        Object peekItem = arb.peek();
        System.out.println(peekItem);

//        arb.printArray();
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        someTest();

//        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
