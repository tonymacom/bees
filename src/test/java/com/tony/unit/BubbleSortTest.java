package com.tony.unit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class BubbleSortTest {

    @Test
    public void testNullArray() {
        int[] arr = null;
        BubbleSort.bubbleSort(arr);
        // No assertion needed, should not throw exception
    }

    @Test
    public void testEmptyArray() {
        int[] arr = {};
        BubbleSort.bubbleSort(arr);
        assertArrayEquals(new int[]{}, arr);
    }

    @Test
    public void testSortedArray() {
        int[] arr = {1, 2, 3, 4, 5};
        BubbleSort.bubbleSort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }

    @Test
    public void testReverseSortedArray() {
        int[] arr = {5, 4, 3, 2, 1};
        BubbleSort.bubbleSort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }

    @Test
    public void testRandomArray() {
        int[] arr = {9, 3, 7, 1, 5};
        int[] expected = {1, 3, 5, 7, 9};
        BubbleSort.bubbleSort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    public void testArrayWithDuplicates() {
        int[] arr = {3, 1, 5, 3, 2};
        int[] expected = {1, 2, 3, 3, 5};
        BubbleSort.bubbleSort(arr);
        assertArrayEquals(expected, arr);
    }
}
