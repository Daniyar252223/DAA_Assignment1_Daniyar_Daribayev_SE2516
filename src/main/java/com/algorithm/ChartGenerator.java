package com.algorithm;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ChartGenerator {
    public static void generatePlots(List<BenchmarkRunner.RunResult> results) {
        generateTimeChart(results);
        generateDepthChart(results);
        generateRatioChart(results);
        System.out.println("Plots created successfully!");
    }

    private static void generateTimeChart(List<BenchmarkRunner.RunResult> results) {
        XYSeriesCollection dataset = new XYSeriesCollection();

        for (BenchmarkRunner.RunResult r : results) {
            String key = r.algorithm() + " (" + r.inputType() + ")";

            int index = dataset.getSeriesIndex(key);
            XYSeries series;
            if (index < 0) {
                series = new XYSeries(key);
                dataset.addSeries(series);
            } else {
                series = dataset.getSeries(index);
            }

            series.add(r.n(), r.timeMs());
        }

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Execution Time vs Array Size (n)",
                "n (Array Size)",
                "Time (ms)",
                dataset
        );

        saveChart(chart, "time_vs_n.png");
    }

    private static void generateDepthChart(List<BenchmarkRunner.RunResult> results) {
        XYSeriesCollection dataset = new XYSeriesCollection();

        for (BenchmarkRunner.RunResult r : results) {
            String key = r.algorithm() + " (" + r.inputType() + ")";

            int index = dataset.getSeriesIndex(key);
            XYSeries series;
            if (index < 0) {
                series = new XYSeries(key);
                dataset.addSeries(series);
            } else {
                series = dataset.getSeries(index);
            }

            series.add(r.n(), r.maxDepth());
        }

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Max Recursion Depth vs Array Size (n)",
                "n (Array Size)",
                "Max Depth",
                dataset
        );

        saveChart(chart, "depth_vs_n.png");
    }

    private static void generateRatioChart(List<BenchmarkRunner.RunResult> results) {
        XYSeriesCollection dataset = new XYSeriesCollection();

        for (BenchmarkRunner.RunResult r : results) {
            String key = r.algorithm() + " (" + r.inputType() + ")";

            int index = dataset.getSeriesIndex(key);
            XYSeries series;
            if (index < 0) {
                series = new XYSeries(key);
                dataset.addSeries(series);
            } else {
                series = dataset.getSeries(index);
            }

            double ratio;
            if (r.algorithm().equals("QuickSelect")) {
                ratio = (double) r.comparisons() / r.n();
            } else {
                ratio = (double) r.comparisons() / (r.n() * (Math.log(r.n()) / Math.log(2)));
            }

            series.add(r.n(), ratio);
        }

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Asymptotic Ratio Verification (Comparisons / g(n))",
                "n (Array Size)",
                "Ratio",
                dataset
        );

        saveChart(chart, "ratio_vs_n.png");
    }
    private static void saveChart(JFreeChart chart, String fileName) {
        try {
            ChartUtils.saveChartAsPNG(new File(fileName), chart, 800, 600);
        } catch (IOException e) {
            System.err.println("Error saving " + fileName + ": " + e.getMessage());
        }
    }
}
