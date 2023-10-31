package edu.hw3;

import edu.hw3.tack7.MyComparator;
import org.junit.jupiter.api.Test;
import java.util.TreeMap;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {

    @Test
    void test() {
        TreeMap<Integer, String> tree = new TreeMap<>(new MyComparator<>());
        tree.put(null, "test");

        assertThat(tree.containsKey(null)).isTrue();
        assertThat(tree.get(null)).isEqualTo("test");

        tree.put(1, "a");
        tree.put(2, "b");
        tree.put(3, "c");
        tree.put(null, "Hello");

        assertThat(tree.get(null)).isEqualTo("Hello");
        assertThat(tree.get(1)).isEqualTo("a");
        assertThat(tree.get(2)).isEqualTo("b");
        assertThat(tree.get(3)).isEqualTo("c");
    }
}
