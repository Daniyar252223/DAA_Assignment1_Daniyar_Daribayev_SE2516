package com.algorithm;

public class InsertionSort {
    public static void sort(int[] a,int low,int high, Metrics metrics) {
        for (int i = low + 1; i <= high; i++){
            int key = a[i];
            int j = i -1;
            while ( j >= low) {
                if (metrics != null)
                    metrics.incrementComparison();
                if (a[j] > key) {
                    a[j + 1] = a[j];
                    j--;
                } else {
                    break;
                }
            }
            a[j+1] = key;
        }
    }
}
