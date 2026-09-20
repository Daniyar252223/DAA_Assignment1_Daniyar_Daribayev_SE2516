# Design and analysis of algorithms Readme File

This project implements and benchmarks core divide-and-conquer algorithms in Java 21.
## Included Algorithms
* **MergeSort**: Hybrid MergeSort with InsertionSort cutoff ($k = 15$) and reusable helper array optimization.
* **QuickSort**: 3-way partitioning (Dijkstra's Dutch National Flag) with randomized pivot selection and bounded recursion depth limit ($O(\log n)$ stack depth).
* **QuickSelect**: Randomized 3-way selection algorithm for finding the $k$-th smallest elem

## Project structure
* src/main/java/com.algorithm/'
* src/test/java/com.algorithm/'
* "*png" this contains all .png files and generated plots for time complexity, recursion depth, and asymptotic ratio checks.
## How to Run 
You can run code in BenchmarkRunner.java 
## How to Run Tests
You can run every sort tests in their files and check that they passed or failed to test.