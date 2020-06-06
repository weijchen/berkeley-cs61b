
public class pascalsTriangle {
    public static void main(String[] args) {
        int[][] pascalsTriangle;
        pascalsTriangle = new int[4][];
        int[] rowZero = pascalsTriangle[0];

        pascalsTriangle[0] = new int[]{1};
        pascalsTriangle[1] = new int[]{1,1};
        pascalsTriangle[2] = new int[]{1,2,1};
        pascalsTriangle[3] = new int[]{1,3,3,1};
        int[] rowTwo = pascalsTriangle[2];
        rowTwo[1] = -5;

        int[][] matrix;
        matrix = new int[4][];
        matrix = new int[4][4];

        int[][] pascalAgain = new int[][]{{1}, {1,1}, {1,2,1}, {1,3,3,1}};

        /** Exercise 2.4.1 */
        int[][] x = {{1,2,3}, {4,5,6}, {7,8,9}};
        // each box of x store a reference to a int[]

        int[][] z = new int[3][];
        // z copies the reference to int[]
        z[0] = x[0];    // 1,2,3
        z[1] = x[1];    // 4,5,6
        z[2] = x[2];    // 7,8,9
        z[0][0] = -z[0][0]; // 1 -> -1  :  will change x at the same time

        int[][] w = new int[3][3];
        System.arraycopy(x[0], 0, w[0], 0, 3);  // -1,2,3
        System.arraycopy(x[1], 0, w[1], 0, 3);  // 4,5,6
        System.arraycopy(x[2], 0, w[2], 0, 3);  // 7,8,9
        w[0][0] = -w[0][0]; // -1 -> 1
    }
}