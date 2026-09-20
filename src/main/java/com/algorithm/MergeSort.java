package com.algorithm;

public class MergeSort {
    private static final int CUTOFF = 15;

    public static void sort(int[] a) {
        sort(a, null);
    }

    public static void sort(int[] a, Metrics metrics) {
        if (a == null || a.length <= 1) return;
        int[] helper = new int[a.length];
        if (metrics != null) metrics.enterRecursion();
        sort(a, helper, 0, a.length - 1, metrics);
        if (metrics != null) metrics.exitRecursion();
    }

    private static void sort(int[] a, int[] helper, int low, int high, Metrics metrics) {
        if (high - low + 1 <= CUTOFF) {
            InsertionSort.sort(a, low, high, metrics);
            return;
        }
        int mid = low + (high - low) / 2;
        if (metrics != null) metrics.enterRecursion();
        sort(a, helper, low, mid, metrics);
        if (metrics != null) metrics.exitRecursion();
        if (metrics != null) metrics.enterRecursion();
        sort(a, helper, mid + 1, high, metrics);
        if (metrics != null) metrics.exitRecursion();
        merge(a, helper, low, mid, high, metrics);
    }
    private static void merge(int[] a, int[] helper, int low, int mid, int high,Metrics metrics){
        System.arraycopy(a, low, helper, low, high - low + 1);
        int i = low;
        int j = mid + 1;
        for(int k = low; k <= high; k++) {
            if (i > mid) {
                a[k] = helper[j++];
            } else if (j > high) {
                a[k] = helper[i++];
            } else {
                if (metrics != null) metrics.incrementComparison();
                if (helper[j] < helper[i]) {
                    a[k] = helper[j++];
                } else {
                    a[k] = helper[i++];
                }
            }
        }
    }
}


