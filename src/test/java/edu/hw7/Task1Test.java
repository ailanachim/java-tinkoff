package edu.hw7;

import edu.hw7.task1.Counter;
import edu.hw7.task1.Incrementer;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {

    @Test
    void test() throws InterruptedException {
        Counter counter = new Counter(0);

        Incrementer thread1 = new Incrementer(counter, 25000);
        Incrementer thread2 = new Incrementer(counter, 25000);
        Incrementer thread3 = new Incrementer(counter, 25000);
        Incrementer thread4 = new Incrementer(counter, 25000);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();

        assertThat(counter.value()).isEqualTo(100000);
    }
}
