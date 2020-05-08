public class IntNode {
    public IntNode next;
    public int item;

    public IntNode(){
       next = null;
       item = 0;
    }

    public IntNode(int item, IntNode next) {
        this.next = next;
        this.item = item;
    }
}
