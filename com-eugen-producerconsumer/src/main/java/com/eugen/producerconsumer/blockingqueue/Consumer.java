package com.eugen.producerconsumer.blockingqueue;

import java.util.concurrent.BlockingQueue;

class Consumer implements Runnable {
    BlockingQueue<Integer> obj;
    int taken = -1;

    public Consumer(BlockingQueue<Integer> obj) {
        this.obj = obj;
    }

    @Override
    public void run() {
        while (taken != 4) {
            try {
                taken = obj.take();
                System.out.println("Consumed " + taken);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}