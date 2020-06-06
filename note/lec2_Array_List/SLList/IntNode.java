/**
 * IntNode class:
 * int vale
 * IntNode next
 */

public class IntNode {
    public int value;
    public IntNode next;

    /** Constructor for IntNode */
    public IntNode(int x, IntNode node) {
        value = x;
        next = node;
    }
}