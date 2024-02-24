package com.eugen.rt.fibonacci;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.concurrent.ForkJoinPool;

public class FibonacciTest {
    @Test
    public void testFibonacci() {
        assertEquals(1134903170L, new ForkJoinPool().invoke(new Fibonacci(45)).longValue());
    }
}