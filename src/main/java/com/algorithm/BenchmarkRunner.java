package com.algorithm;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class BenchmarkRunner {
    private static final int[] SIZES = {1000, 10000, 100000, 1000000};
    private static final String[] INPUT_TYPES = {"random", "sorted", "duplicates"};
    private static final String[] ALGORITHMS = {"MergeSort", "QuickSort", "QuickSelect"};
    private static final int REPETITIONS = 5;

    public static void main(String[] args) {
        List<RunResult> results = new ArrayList<>();
        Random rnd = new Random(12345);

        System.out.println("Starting Benchmark Suite...");

        for (String algo : ALGORITHMS) {
            for (String inputType : INPUT_TYPES) {
                for (int n : SIZES) {
                    List<RunResult> runs = new ArrayList<>();

                    for (int rep = 0; rep < REPETITIONS; rep++) {
                        int[] original = generateInput(n, inputType, rnd);
                        int[] data = original.clone();

                        Metrics metrics = new Metrics();
                        int k = n / 2;

                        metrics.startTimer();
                        if (algo.equals("MergeSort")) {
                            MergeSort.sort(data, metrics);
                        } else if (algo.equals("QuickSort")) {
                            QuickSort.sort(data, metrics);
                        } else if (algo.equals("QuickSelect")) {
                            QuickSelect.select(data, k, metrics);
                        }
                        metrics.stopTimer();

                        runs.add(new RunResult(algo, inputType, n, metrics.getTimeMs(), metrics.getComparisons(), metrics.getMaxDepth()));
                    }

                    runs.sort(Comparator.comparingDouble(r -> r.timeMs));
                    RunResult medianRun = runs.get(REPETITIONS / 2);
                    results.add(medianRun);

                    System.out.printf("Done: %-10s | %-10s | n=%-7d | time=%.3f ms | comps=%d | depth=%d%n",
                            algo, inputType, n, medianRun.timeMs, medianRun.comparisons, medianRun.maxDepth);
                }
            }
        }

        exportToCSV(results, "results.csv");
        ChartGenerator.generatePlots(results);
        System.out.println("Benchmark finished successfully!");
    }

    private static int[] generateInput(int n, String type, Random rnd) {
        int[] arr = new int[n];
        switch (type) {
            case "random":
                for (int i = 0; i < n; i++) arr[i] = rnd.nextInt();
                break;
            case "sorted":
                for (int i = 0; i < n; i++) arr[i] = i;
                break;
            case "duplicates":
                for (int i = 0; i < n; i++) arr[i] = rnd.nextInt(10);
                break;
        }
        return arr;
    }
    private static void exportToCSV(List<RunResult> results, String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println("algorithm,input,n,time_ms,comparisons,max_depth");
            for (RunResult r : results) {
                writer.printf(Locale.US, "%s,%s,%d,%.4f,%d,%d%n",
                        r.algorithm, r.inputType, r.n, r.timeMs, r.comparisons, r.maxDepth);
            }
        } catch (IOException e) {
            System.err.println("Error writing CSV: " + e.getMessage());
        }
    }
    public record RunResult(String algorithm, String inputType, int n, double timeMs, long comparisons, int maxDepth){}
}
