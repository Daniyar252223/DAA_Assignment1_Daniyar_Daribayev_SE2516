package com.algorithm;
import java.util.Random;
public class QuickSort {
    private static final Random RND = new Random(42);

    public static void sort(int[] a) {
        sort(a, null);
    }

    public static void sort(int[] a, Metrics metrics) {
        if (a == null || a.length <= 1) return;
        if (metrics != null) metrics.enterRecursion();
        sort(a, 0, a.length - 1, metrics);
        if (metrics != null) metrics.exitRecursion();
    }

    private static void sort(int[] a, int low, int high, Metrics metrics) {
        while (low < high) {
            int pivotIndex = low + RND.nextInt(high - low + 1);
            int pivot = a[pivotIndex];

            // 3-way partition (< pivot, = pivot, > pivot)
            swap(a, low, pivotIndex);
            int lt = low;
            int i = low + 1;
            int gt = high;

            while (i <= gt) {
                if (metrics != null) metrics.incrementComparison();
                int cmp = Integer.compare(a[i], pivot);
                if (cmp < 0) {
                    swap(a, lt++, i++);
                } else if (cmp > 0) {
                    swap(a, i, gt--);
                } else {
                    i++;
                }
            }
            int leftSize = lt - low;
            int rightSize = high - gt;

            if (leftSize < rightSize) {
                if (leftSize > 0) {
                    if (metrics != null) metrics.enterRecursion();
                    sort(a, low, lt - 1, metrics);
                    if (metrics != null) metrics.exitRecursion();
                }
                low = gt + 1;
            } else {
                if (rightSize > 0) {
                    if (metrics != null) metrics.enterRecursion();
                    sort(a, gt + 1, high, metrics);
                    if (metrics != null) metrics.exitRecursion();
                }
                high = lt - 1;
            }
        }
    }
    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
