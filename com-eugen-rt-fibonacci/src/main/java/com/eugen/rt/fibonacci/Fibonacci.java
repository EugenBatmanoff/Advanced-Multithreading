package com.eugen.rt.fibonacci;

import java.util.concurrent.RecursiveTask;

public class Fibonacci extends RecursiveTask<Long> {
    final int n;

    Fibonacci(int n) {
        this.n = n;
    }

    @Override
    protected Long compute() {
        if (n <= 10) {
            return fib(n);
        }
        Fibonacci f1 = new Fibonacci(n - 1);
        f1.fork();
        Fibonacci f2 = new Fibonacci(n - 2);
        return f2.compute() + f1.join();
    }

    private long fib(int n) {
        long[] fib = new long[n + 1];
        fib[0] = 0;
        if (n > 0) {
            fib[1] = 1;
            for (int i = 2; i <= n; i++) {
                fib[i] = fib[i - 1] + fib[i - 2];
            }
        }
        return fib[n];
    }
}