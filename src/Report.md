# Design and analysis of algorithms Report file.
## 1. Asymptotic Complexity Summary

| Algorithm | Best Case | Average Case | Worst Case | Space Complexity |
|---|---|---|---|---|
| **MergeSort** | $\Theta(n \log n)$ | $\Theta(n \log n)$ | $\Theta(n \log n)$ | $O(n)$ |
| **QuickSort** | $\Theta(n \log n)$ | $\Theta(n \log n)$ | $O(n^2)$ | $O(\log n)$ |
| **QuickSelect** | $\Theta(n)$ | $\Theta(n)$ | $O(n^2)$ | $O(\log n)$ |
| **InsertionSort** | $\Theta(n)$ | $\Theta(n^2)$ | $\Theta(n^2)$ | $O(1)$ |

---

## 2. Recurrences & Master Theorem

### 2.1 MergeSort
* **Recurrence:** $T(n) = 2T(n/2) + \Theta(n)$
* **Master Theorem:** $a=2, b=2 \implies n^{\log_2 2} = n^1$. Since $f(n) = \Theta(n)$, Case 2 applies.
* **Result:** $T(n) = \Theta(n \log n)$.

### 2.2 QuickSort
* **Average Case:** $T(n) = 2T(n/2) + \Theta(n) \implies \Theta(n \log n)$ (Case 2).
* **Worst Case:** $T(n) = T(n-1) + \Theta(n) \implies \Theta(n^2)$ (occurs only with terrible pivots).

### 2.3 QuickSelect
* **Recurrence:** $T(n) = T(n/2) + \Theta(n)$
* **Master Theorem:** $a=1, b=2 \implies n^{\log_2 1} = n^0 = 1$. Since $f(n) = \Theta(n)$, Case 3 applies.
* **Result:** $T(n) = \Theta(n)$.

## 3. Key Findings & Empirical Results

1. **InsertionSort Cutoff (k <= 15):**
    * Using InsertionSort for arrays smaller than 15 elements avoided overhead from unnecessary recursive calls.

2. **3-Way Partitioning in QuickSort:**
    * Handled duplicate elements efficiently. Sorting $1,000,000$ duplicate values took only $\approx 15 \text{ ms}$.

3. **Recursion Stack Depth Control:**
    * Always recursing on the smaller subarray limited the max depth to $14$ for $n = 1,000,000$, keeping memory usage strictly $O(\log n)$.

4. **QuicSelect Efficiency:**
    * Finding the median (k = n/2) executed in linear time $O(n)$, taking around $15 \text{ ms }$ for $1,000,000$ elements.
