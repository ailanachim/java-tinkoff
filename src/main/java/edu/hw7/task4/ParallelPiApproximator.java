package edu.hw7.task4;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class ParallelPiApproximator extends PiApproximator {

    private final AtomicInteger circleCount = new AtomicInteger(0);
    private final Thread[] threads;

    public ParallelPiApproximator(int pointCount, int threadCount) {
        super(pointCount);
        if (threadCount <= 0) {
            throw new IllegalArgumentException("thread count must be positive");
        }

        threads = new Thread[threadCount];

        for (int i = 0; i < threadCount - 1; i++) {
            threads[i] = new Simulator(totalCount / threadCount);
        }
        threads[threadCount - 1] = new Simulator(totalCount / threadCount + totalCount % threadCount);
    }

    @Override
    public double calculatePi() throws InterruptedException {
        circleCount.set(0);

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        final int coefficient = 4;
        return coefficient * (double) circleCount.get() / totalCount;
    }

    class Simulator extends Thread {

        private final int simulations;

        Simulator(int simulations) {
            this.simulations = simulations;
        }

        @Override
        public void run() {
            ThreadLocalRandom random = ThreadLocalRandom.current();

            int count = 0;
            for (int j = 0; j < simulations; j++) {
                double x = random.nextDouble(-1, 1);
                double y = random.nextDouble(-1, 1);

                if (x * x + y * y <= 1) {
                    count++;
                }
            }

            circleCount.addAndGet(count);
        }
    }
}
