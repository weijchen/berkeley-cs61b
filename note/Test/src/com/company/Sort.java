package com.company;
/** Selection sort algorithm
 * @return sorted array
 */

public class Sort {
    /* Selection sort using iterative */
    public static void sort(int[] sortArr) {
        int length = sortArr.length;
        for (int i = 0; i < length; i++) {
            int minTemp = i;

            for (int j = i; j < length; j++) {
                 if (sortArr[minTemp] > sortArr[j]) {
                    minTemp = j;
                }
            }
            swap(sortArr, i, minTemp);
        }
    }

    public static void sortRecursive(int[] sortArr) {
        sortRecursive(sortArr, 0);
    }

    /* Selection sort using recursive */
    public static void sortRecursive(int[] sortArr, int x) {
        if (x == sortArr.length) {
            return;
        }
        int min = findSmallest(sortArr, x);        
        swap(sortArr, x, min);
        sortRecursive(sortArr, x+1);
    }

    /* Checker function for checking two arrays have the equal value */
    public static void checkSort(int[] checkArr, int[] ansArr) {
        for (int i = 0; i < checkArr.length; i++) {
            if (checkArr[i] != ansArr[i]) {
                System.out.println("Error: item at position " + i + " is not equal. " + checkArr[i] + " != " + ansArr[i]);
                return;
            }
        }
    }
    /* Swap two items in the given array */
    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static int findSmallest(int[] arr) {
        int length = arr.length;
        int min = 0;
        for (int i = 0; i < length; i++) {
            if (arr[min] > arr[i]) {
                min = i;
            }
        }
        return min;
    }

    public static int findSmallest(int[] arr, int start) {
        int length = arr.length;
        int min = start;
        for (int i = start; i < length; i++) {
            if (arr[min] > arr[i]) {
                min = i;
            }
        }
        return min;
    }

    /* Print the elements in the given array */
    public static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("");
    }
}