package com.algorithm;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class QuickSortTest {
    @Test
    void testCorrectnessAgainstArraysSort() {
        Random rnd = new Random(42);
        for (int i = 0; i < 100; i++) {
            int n = rnd.nextInt(500) + 1;
            int[] actual = rnd.ints(n, -10000, 10000).toArray();
            int[] expected = actual.clone();

            Arrays.sort(expected);
            QuickSort.sort(actual);

            assertArrayEquals(expected, actual);
        }
    }

    @Test
    void testRecursionDepthOnSortedArray() {
        int n = 100000;
        int[] sortedArray = new int[n];
        for (int i = 0; i < n; i++) sortedArray[i] = i;

        Metrics metrics = new Metrics();
        QuickSort.sort(sortedArray,metrics);

        double maxAllowedDepth = 2.0 * (Math.log(n) / Math.log(2));
        assertTrue(metrics.getMaxDepth() <= maxAllowedDepth,
                "max depth exceeded bound!");
    }
}
