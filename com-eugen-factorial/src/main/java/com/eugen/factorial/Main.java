package com.eugen.factorial;

import java.math.BigInteger;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        var n = 100000;

        var start = System.currentTimeMillis();
        var resultSingleThread = factorialSingleThread(n);
        var end = System.currentTimeMillis();
        System.out.println("Single thread time: " + (end - start) + " ms");

        var pool = new ForkJoinPool();
        var task = new FactorialCounter(n);

        start = System.currentTimeMillis();
        var resultForkJoin = pool.invoke(task);
        end = System.currentTimeMillis();
        System.out.println("Fork-Join time: " + (end - start) + " ms");

        // Verify results
        if (resultSingleThread.equals(resultForkJoin)) {
            System.out.println("Results match.");
        } else {
            System.out.println("Results do not match.");
        }
    }

    public static BigInteger factorialSingleThread(int n) {
        var result = BigInteger.ONE;
        for (var i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
}
