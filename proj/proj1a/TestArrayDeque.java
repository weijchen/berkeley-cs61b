import javax.rmi.ssl.SslRMIClientSocketFactory;

public class TestArrayDeque {
    public static void main(String[] args) {
        ArrayDeque<String> deque = new ArrayDeque();

        /* Testing Add/Remove/Size/isEmpty */
        System.out.println(deque.size());
        System.out.println(deque.isEmpty());
        deque.addFirst("5");
        deque.printDeque();
        deque.addFirst("10");
        deque.printDeque();
        deque.addFirst("20");
        deque.printDeque();
        deque.addLast("30");
        deque.printDeque();
        deque.addLast("30");
        deque.printDeque();
        System.out.println(deque.size());
        System.out.println(deque.isEmpty());
        
        String rm = deque.removeFirst();
        System.out.println("Remove is: " + rm);
        deque.printDeque();
        System.out.println(deque.size());
        rm = deque.removeLast();
        System.out.println("Remove is: " + rm);
        deque.printDeque();
        System.out.println(deque.size());
    }
}