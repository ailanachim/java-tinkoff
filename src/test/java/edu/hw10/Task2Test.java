package edu.hw10;

import edu.hw10.task2.CacheProxy;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class Task2Test {

    @Test
    void cacheProxyTest() {
        FibCalculator mock = Mockito.mock(FibCalculator.class);
        Mockito.when(mock.fib(1)).thenReturn(1L);

        FibCalculator proxy = CacheProxy.create(mock, FibCalculator.class);

        proxy.fib(1);
        proxy.fib(1);
        proxy.fib(1);
        proxy.fib(1);
        proxy.fib(1);

        Mockito.verify(mock, Mockito.times(1)).fib(1);
    }
}
