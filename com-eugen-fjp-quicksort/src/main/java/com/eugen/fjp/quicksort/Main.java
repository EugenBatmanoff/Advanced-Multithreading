package com.eugen.fjp.quicksort;

import java.util.concurrent.ForkJoinPool;
import java.util.Random;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        var random = new Random();
        var array = IntStream.generate(() -> random.nextInt(10)).limit(1_000_000).toArray();
        var pool = new ForkJoinPool();
        var task = new JfpQuickSort(array, 0, array.length - 1);
        pool.invoke(task);
        verifyThatArrayIsSorted(array);

    }

    private static void verifyThatArrayIsSorted(int[] array) {
        for (var i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                System.out.println("Array is not sorted");
                return;
            }
        }
        System.out.println("Array is sorted");
    }
}