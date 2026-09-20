package com.algorithm;

public class Metrics {
    private long comparisons;
    private int currentDepth;
    private int maxDepth;
    private long startTimeNs;
    private long elapsedTimeNs;

    public Metrics() {
        reset();
    }

    public void reset() {
        this.comparisons = 0;
        this.currentDepth = 0;
        this.maxDepth = 0;
        this.startTimeNs = 0;
        this.elapsedTimeNs = 0;
    }

    public void incrementComparison() {
        this.comparisons++;
    }
    public void enterRecursion(){
        this.currentDepth++;
        if (this.currentDepth > this.maxDepth){
            this.maxDepth = this.currentDepth;
        }
    }
    public void exitRecursion(){
        if(this.currentDepth > 0){
            this.currentDepth--;
        }
    }
    public void startTimer(){
        this.startTimeNs = System.nanoTime();
    }
    public void stopTimer(){
        this.elapsedTimeNs = System.nanoTime() - this.startTimeNs;
    }
    public double getTimeMs() {
        return this.elapsedTimeNs / 1_000_000.0;
    }
    public long getComparisons(){
        return comparisons;
    }
    public int getMaxDepth(){
        return maxDepth;
    }
}
