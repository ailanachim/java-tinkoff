package edu.hw7.task2;

import java.math.BigInteger;
import java.util.Optional;
import java.util.stream.Stream;

public class Task2 {

    private Task2() {
    }

    public static BigInteger factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Number can't be negative");
        }

        Optional<BigInteger> result = Stream.iterate(BigInteger.ONE, x -> x.add(BigInteger.ONE))
            .limit(n)
            .parallel()
            .reduce(BigInteger::multiply);
        return result.orElse(BigInteger.ONE);
    }
}
