package com.eugen.fjp.quicksort;

import java.util.concurrent.RecursiveAction;

/*  To avoid stack overflow errors when sorting a large array with QuickSort:
    The"tail call elimination" - while loop + sorting the smaller partition first.
    The "dual pivot QuickSort" - using both ends of the array + invokeAll to sort both partitions in parallel
 */
public class JfpQuickSort extends RecursiveAction {
    private final int[] array;
    private final int low;
    private final int high;

    public JfpQuickSort(int[] array, int low, int high) {
        this.array = array;
        this.low = low;
        this.high = high;
    }

    @Override
    protected void compute() {
        if (low < high) {
            int[] pivot = partition(array, low, high);
            invokeAll(new JfpQuickSort(array, low, pivot[0] - 1),
                    new JfpQuickSort(array, pivot[0] + 1, pivot[1] - 1),
                    new JfpQuickSort(array, pivot[1] + 1, high));
        }
    }

    private int[] partition(int[] array, int low, int high) {
        if (array[low] > array[high]) swap(array, low, high);
        int j = low + 1;
        int g = high - 1, k = low + 1, p = array[low], q = array[high];
        while (k <= g) {
            if (array[k] < p) {
                swap(array, k, j);
                ++j;
            } else if (array[k] >= q) {
                while (array[g] > q && k < g) --g;
                swap(array, k, g);
                --g;
                if (array[k] < p) {
                    swap(array, k, j);
                    ++j;
                }
            }
            ++k;
        }
        --j;
        ++g;
        swap(array, low, j);
        swap(array, high, g);
        return new int[]{j, g};
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}