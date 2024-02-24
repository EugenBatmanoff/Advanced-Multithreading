package com.eugen.producerconsumer.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    public static void main(String[] args) {

        BlockingQueue<Integer> bqueue
                = new ArrayBlockingQueue<>(4);

        Producer p1 = new Producer(bqueue);
        Consumer c1 = new Consumer(bqueue);

        Thread pThread = new Thread(p1);
        Thread cThread = new Thread(c1);

        pThread.start();
        cThread.start();
    }
}

