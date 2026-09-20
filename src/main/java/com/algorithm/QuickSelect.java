package com.algorithm;
import java.util.Random;
public class QuickSelect {
    private static final Random RND = new Random(42);

    public static int select(int[] a, int k) {
        return select(a, k, null);
    }

    public static int select(int[] a, int k, Metrics metrics) {
        if (a == null || a.length == 0) {
            throw new IllegalArgumentException("Array must not be null or empty.");
        }
        if (k < 0 || k >= a.length) {
            throw new IllegalArgumentException("Index k out of bounds: " + k);
        }

        if (metrics != null) metrics.enterRecursion();
        int result = select(a, 0, a.length - 1, k, metrics);
        if (metrics != null) metrics.exitRecursion();
        return result;
    }

    private static int select(int[] a, int low, int high, int k, Metrics metrics) {
        if (low == high) {
            return a[low];
        }

        int pivotIndex = low + RND.nextInt(high - low + 1);
        int pivot = a[pivotIndex];

        QuickSort.swap(a, low, pivotIndex);
        int lt = low;
        int i = low + 1;
        int gt = high;

        while (i <= gt) {
            if (metrics != null) metrics.incrementComparison();
            int cmp = Integer.compare(a[i], pivot);
            if (cmp < 0) {
                QuickSort.swap(a, lt++, i++);
            } else if (cmp > 0) {
                QuickSort.swap(a, i, gt--);
            } else {
                i++;
            }
        }

        if (k >= lt && k <= gt) {
            return a[k];
        } else if (k < lt) {
            if (metrics != null) metrics.enterRecursion();
            int res = select(a, low, lt - 1, k, metrics);
            if (metrics != null) metrics.exitRecursion();
            return res;
        } else {
            if (metrics != null) metrics.enterRecursion();
            int res = select(a, gt + 1, high, k, metrics);
            if (metrics != null) metrics.exitRecursion();
            return res;
        }
    }
}
