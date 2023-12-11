package edu.hw8.task2;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class FixedThreadPool implements ThreadPool {

    private final Worker[] threads;
    private final Queue<Runnable> queue = new LinkedBlockingQueue<>();

    public FixedThreadPool(int threadsCount) {
        threads = new Worker[threadsCount];
        for (int i = 0; i < threadsCount; i++) {
            threads[i] = new Worker();
        }
    }

    @Override
    public void start() {
        for (var thread : threads) {
            thread.start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        if (runnable == null) {
            throw new IllegalArgumentException();
        }
        queue.add(runnable);
    }

    @Override
    public void close() throws InterruptedException {
        for (var thread : threads) {
            thread.interrupt();
        }

        for (var thread : threads) {
            thread.join();
        }
    }

    class Worker extends Thread {

        @Override
        public void run() {
            while (!isInterrupted() || !queue.isEmpty()) {
                Runnable task = queue.poll();
                if (task != null) {
                    task.run();
                }
            }
        }
    }
}
