package com.algorithm;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class QuickSelectTest {
    @Test
    void testCorrectnessAgainstSortedArrays() {
        Random rnd = new Random(42);
        for (int i = 0; i < 100; i++) {
            int n = rnd.nextInt(500) + 1;
            int[] arr = rnd.ints(n, -10000, 10000).toArray();
            int[] sorted = arr.clone();
            Arrays.sort(sorted);

            int k = rnd.nextInt(n);
            int selected = QuickSelect.select(arr, k);

            assertEquals(sorted[k], selected);
        }
    }

    @Test
    void testInvalidInputs() {
        int[] arr = new int[]{10, 20, 30};
        assertThrows(IllegalArgumentException.class, () -> QuickSelect.select(null,0));
        assertThrows(IllegalArgumentException.class, () -> QuickSelect.select(arr, -1));
        assertThrows(IllegalArgumentException.class, () -> QuickSelect.select(arr, 3));
    }
}
