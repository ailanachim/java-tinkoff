package edu.hw8.task2;

import java.util.concurrent.atomic.AtomicInteger;

public class FiboCalculator {

    private final AtomicInteger result = new AtomicInteger(0);
    private final FixedThreadPool executor;

    public FiboCalculator(int threadsCount) {
        executor = new FixedThreadPool(threadsCount);
    }

    public int calculate(int n) throws InterruptedException {
        if (n < 0) {
            throw new IllegalArgumentException();
        }

        executor.execute(new Task(n));
        executor.start();
        executor.close();

        return result.get();
    }

    class Task implements Runnable {

        int n;

        Task(int n) {
            this.n = n;
        }

        @Override
        public void run() {
            if (n <= 1) {
                result.incrementAndGet();
            } else {
                executor.execute(new Task(n - 1));
                executor.execute(new Task(n - 2));
            }
        }
    }
}


