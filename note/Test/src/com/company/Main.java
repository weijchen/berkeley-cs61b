package com.company;

public class Main {
    public static void main(String[] args) throws Exception {

        int[] arr = new int[] {6, 3, 7, 2, 8, 1};
        int[] ans = new int[] {1, 2, 3, 6, 7, 8};

        // Sort.sort(arr);
        Sort.printArr(arr);
        Sort.sortRecursive(arr);
        Sort.printArr(arr);
        Sort.checkSort(arr, ans);
    }
}