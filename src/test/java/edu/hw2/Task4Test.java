package edu.hw2;

import edu.hw2.task4.CallingInfo;
import org.junit.jupiter.api.Test;
import static edu.hw2.task4.Task4.callingInfo;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {

    @Test void test() {
        var info1 = callingInfo();
        var info2 = call();

        assertThat(info1.className()).isEqualTo("edu.hw2.Task4Test");
        assertThat(info1.methodName()).isEqualTo("test");

        assertThat(info2.className()).isEqualTo("edu.hw2.Task4Test");
        assertThat(info2.methodName()).isEqualTo("call");
    }

    CallingInfo call() {
        return callingInfo();
    }
}
