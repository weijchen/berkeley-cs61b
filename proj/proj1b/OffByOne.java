/** A class for off-by-1 comparators */
//public class OffByOne implements CharacterComparator {
//
//    @Override
//    public boolean equalChars(char x, char y) {
//        int diff = x - y;
//        if (diff == 1 || diff*(-1) == 1) {
//            return true;
//        }
//        return false;
//    }
//
//}

public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(y - x) == 1;
    }
}