package com.algorithm;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class MergeSortTest {
    @Test
    void testCorrectnessAgainstArraysSort() {
        Random rnd = new Random(42);
        for (int i = 0; i < 100; i++) {
            int n = rnd.nextInt(500) + 1;
            int[] actual = rnd.ints(n, -10000, 10000).toArray();
            int[] expected = actual.clone();

            Arrays.sort(expected);
            MergeSort.sort(actual);

            assertArrayEquals(expected, actual);
        }
    }
    @Test
    void testEdgeCases(){
        int[] empty = new int[0];
        MergeSort.sort(empty);
        assertEquals(0, empty.length);

        int[] single = new int[]{42};
        MergeSort.sort(single);
        assertEquals(42, single[0]);

        int[] equal = new int[]{5,5,5,5,5};
        MergeSort.sort(equal);
        assertArrayEquals(new int[]{5,5,5,5,5}, equal);
    }
}
