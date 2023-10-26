package edu.hw3;

import org.junit.jupiter.api.Test;
import static edu.hw3.Task2.clusterize;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class Task2Test {

    @Test
    void test() {
        assertThat(clusterize("()")).isEqualTo(new String[] {"()"});
        assertThat(clusterize("()()()")).isEqualTo(new String[] {"()", "()", "()"});
        assertThat(clusterize("((()))")).isEqualTo(new String[] {"((()))"});
        assertThat(clusterize("((()))(())()()(()())")).isEqualTo(new String[] {"((()))", "(())", "()", "()", "(()())"});
        assertThat(clusterize("()()()")).isEqualTo(new String[] {"()", "()", "()"});
        assertThat(clusterize("((())())(()(()()))")).isEqualTo(new String[] {"((())())", "(()(()()))"});
    }

    @Test
    void invalidArgumentTest() {
        assertThat(clusterize("")).isEmpty();
        assertThatThrownBy(() -> clusterize("ackl123")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> clusterize(")()(")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> clusterize("( ( ) )")).isInstanceOf(IllegalArgumentException.class);
    }
}
