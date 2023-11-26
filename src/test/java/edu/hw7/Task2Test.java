package edu.hw7;

import edu.hw7.task2.Task2;
import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {

    @Test
    void test() {
        assertThat(Task2.factorial(0)).isEqualTo(BigInteger.ONE);
        assertThat(Task2.factorial(1)).isEqualTo(BigInteger.ONE);
        assertThat(Task2.factorial(2)).isEqualTo(BigInteger.TWO);
        assertThat(Task2.factorial(3)).isEqualTo(BigInteger.valueOf(6));
        assertThat(Task2.factorial(4)).isEqualTo(BigInteger.valueOf(24));
        assertThat(Task2.factorial(5)).isEqualTo(BigInteger.valueOf(120));
        assertThat(Task2.factorial(10)).isEqualTo(BigInteger.valueOf(3628800));
        assertThat(Task2.factorial(20)).isEqualTo(new BigInteger("2432902008176640000"));
    }
}
