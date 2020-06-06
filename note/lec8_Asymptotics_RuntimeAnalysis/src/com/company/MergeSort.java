package com.company;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MergeSort {

}

@Test
public static void main(String[] args) {
    int[] arr = new int[]{20, 3, 5, 1, 10, 2, 30};
    int[] expected = new int[]{1, 2, 3, 5, 10, 20, 30};

    assertEquals(arr, expected);

}

