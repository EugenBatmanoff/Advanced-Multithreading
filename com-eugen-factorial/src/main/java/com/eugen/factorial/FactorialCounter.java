package com.eugen.factorial;

import java.math.BigInteger;

import java.util.concurrent.RecursiveTask;

public class FactorialCounter extends RecursiveTask<BigInteger> {
    private final int start;
    private final int n;

    public FactorialCounter(int n) {
        this(1, n);
    }

    public FactorialCounter(int start, int n) {
        this.start = start;
        this.n = n;
    }

    @Override
    protected BigInteger compute() {
        if (n - start <= 20) { // Directly calculate for small ranges
            var result = BigInteger.ONE;
            for (var i = start; i <= n; i++) {
                result = result.multiply(BigInteger.valueOf(i));
            }
            return result;
        } else {
            var mid = (start + n) / 2;
            var left = new FactorialCounter(start, mid);
            var right = new FactorialCounter(mid + 1, n);
            right.fork();
            return left.compute().multiply(right.join());
        }
    }
}