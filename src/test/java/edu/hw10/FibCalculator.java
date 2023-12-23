package edu.hw10;

import edu.hw10.task2.Cache;

public interface FibCalculator {
    @Cache()
    long fib(int number);
}

