package byog.lab5;

public class HexWorldSimple {
    /**
     * Create a hexagon of size s
     */
    public static void addHexagon(int s) {
        if (s < 2) {
            throw new IllegalArgumentException("Hexagon must be at least size 2");
        }
        drawTrapezoid(s, false);
        drawTrapezoid(s, true);
    }
    /* Draw trapezoid with given size */
    public static void drawTrapezoid(int s, boolean reversed) {
        int width = s;
        int height = s;
        int maxWidth = width + 2 * (height - 1);
        int whiteSpace = (maxWidth - width) / 2;

        // print reversed trapezoid
        if (reversed) {
            whiteSpace = 0;
            width = maxWidth;
        }

        for (int i = 0; i < s; i++) {
            if (!reversed) {
                printSpace(whiteSpace);
                printGrid(width);
                printSpace(whiteSpace);
                whiteSpace -= 1;
                width += 2;
            } else {
                printSpace(whiteSpace);
                printGrid(width);
                printSpace(whiteSpace);
                whiteSpace += 1;
                width -= 2;
            }
            System.out.println("");
        }
    }

    public static void printSpace(int size) {
        for (int j = 0; j < size; j++) {
            System.out.print(" ");
        }
    }

    public static void printGrid(int size) {
        for (int k = 0; k < size; k++) {
            System.out.print("a");
        }
    }

    public static void main (String[]args){
        int size = 3;
        addHexagon(size);
    }
}
