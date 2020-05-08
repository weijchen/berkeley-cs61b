
public class triangle {

    public static void drawTriangle(int N) {
        for (int i = 1; i < N; i++){
            for (int j = 0; j < i; j++){
                System.out.print("*");
            }
            System.out.println("");
        }
    }

    public static int max(int[] arr) {
        if (arr.length == 1) {
            return arr[0];
        } else {
            int tempMax = arr[0];
            for (int i = 1; i < arr.length; i ++) {
                if (arr[i] > tempMax) {
                    tempMax = arr[i];
                }
            }
            return tempMax;
        }
    }

    public static void windowPosSum(int[] a, int n) {
        int length = a.length;
        for (int i = 0; i < length; i++) {
            if (a[i] > 0) {
                for (int j = 1; j <= n; j++) {
                    if ((i + 1 + j) <= length) {
                        a[i] = a[i] + a[i+j];
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        drawTriangle(5);
        System.out.println("");
        drawTriangle(10);
        System.out.println("");
        int[] numbers = new int[]{9,2,15,2,22,10,6};
        // int[] numbers = new int[]{9};
        System.out.println("Max value is: " + max(numbers));
        System.out.println("");
        int[] a = {1, 2, -3, 4, 5, 4};
        int n = 3;
        windowPosSum(a, n);

        // Should print 4, 8, -3, 13, 9, 4
        System.out.println(java.util.Arrays.toString(a));
    }
}