package edu.hw8.task2;

import java.util.LinkedList;
import java.util.Queue;

public class FixedThreadPool implements ThreadPool {

    private final Worker[] threads;
    private final Queue<Runnable> queue = new LinkedList<>();
    private volatile boolean isWorking;

    public FixedThreadPool(int threadsCount) {
        threads = new Worker[threadsCount];
        for (int i = 0; i < threadsCount; i++) {
            threads[i] = new Worker();
        }
    }

    @Override
    public void start() {
        isWorking = true;

        for (var thread : threads) {
            thread.start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        if (runnable == null) {
            throw new IllegalArgumentException();
        }

        synchronized (queue) {
            queue.add(runnable);
        }
    }

    @Override
    public void close() throws InterruptedException {
        isWorking = false;

        for (var thread : threads) {
            thread.join();
        }
    }

    class Worker extends Thread {

        @Override
        public void run() {
            Runnable task;

            while (isWorking || !queue.isEmpty()) {
                if (queue.isEmpty()) {
                    continue;
                }
                synchronized (queue) {
                    task = queue.poll();
                }
                if (task != null) {
                    task.run();
                }
            }
        }
    }
}
