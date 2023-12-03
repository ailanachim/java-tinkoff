package edu.hw8.task2;

import java.util.concurrent.atomic.AtomicInteger;

public class FiboCalculator {

    public int calculate(int n, int threadsCount) throws Exception {
        if (n < 0) {
            throw new IllegalArgumentException();
        }

        final AtomicInteger result = new AtomicInteger(0);
        try (ThreadPool executor = new FixedThreadPool(threadsCount)) {
            executor.execute(new Task(n, executor, result));
            executor.start();
        }

        return result.get();
    }

    class Task implements Runnable {

        int n;
        ThreadPool executor;
        AtomicInteger result;

        Task(int n, ThreadPool executor, AtomicInteger result) {
            this.n = n;
            this.executor = executor;
            this.result = result;
        }

        @Override
        public void run() {
            if (n <= 1) {
                result.incrementAndGet();
            } else {
                executor.execute(new Task(n - 1, executor, result));
                executor.execute(new Task(n - 2, executor, result));
            }
        }
    }
}


