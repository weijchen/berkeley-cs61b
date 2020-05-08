/** A class for palindrome operations */
public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new Deque<Character>();
        int length = word.length();
        for (int i = 0; i < length; i ++) {
            Character ele = word.charAt(i);
            deque.addLast(ele);
        }
        return deque;
    }

    public boolean isPalindrome(Deque deque) {
        int length = deque.size();
        if (length <= 1) {
            return true;
        }
        int traverseCount = (int)Math.ceil(length/2.0);
        Deque.DLLNode forward = deque.sentinel;
        Deque.DLLNode backward = deque.sentinel;

        for (int i = 0; i < traverseCount; i++) {
            if (!forward.getPrev().item.equals(backward.getNext().item)) {
                return false;
            }
            forward = forward.getPrev();
            backward = backward.getNext();
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        return true;
    }
}
