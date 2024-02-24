package com.eugen.ra.sumofsquares;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        double[] array = new double[500_000_000];
        Random rand = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextDouble();
        }

        ForkJoinPool pool = new ForkJoinPool();

        long start = System.currentTimeMillis();
        double sumOfSquaresForkJoin = sumOfSquares(pool, array);
        long end = System.currentTimeMillis();
        System.out.println("ForkJoin sum of squares: " + sumOfSquaresForkJoin);
        System.out.println("ForkJoin time: " + (end - start) + " ms");

        start = System.currentTimeMillis();
        double sumOfSquaresLinear = sumOfSquaresLinear(array);
        end = System.currentTimeMillis();
        System.out.println("Linear sum of squares: " + sumOfSquaresLinear);
        System.out.println("Linear time: " + (end - start) + " ms");
    }

    static double sumOfSquares(ForkJoinPool pool, double[] array) {
        int n = array.length;
        Applyer a = new Applyer(array, 0, n, null);
        pool.invoke(a);
        return a.result;
    }

    static double sumOfSquaresLinear(double[] array) {
        double sum = 0;
        for (double v : array) {
            sum += v * v;
        }
        return sum;
    }
}