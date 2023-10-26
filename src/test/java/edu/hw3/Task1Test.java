package edu.hw3;

import org.junit.jupiter.api.Test;
import static edu.hw3.Task1.atbash;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class Task1Test {

    @Test
    void test() {
        assertThat(atbash("a")).isEqualTo("z");
        assertThat(atbash("d")).isEqualTo("w");
        assertThat(atbash("abc")).isEqualTo("zyx");
        assertThat(atbash("Hello world!")).isEqualTo("Svool dliow!");
        assertThat(atbash("Any fool can write code that a computer can understand." +
            " Good programmers write code that humans can understand. ― Martin Fowler"))
            .isEqualTo("Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw." +
                " Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi");

        assertThat(atbash("")).isEqualTo("");
        assertThatThrownBy(() -> atbash(null)).isInstanceOf(IllegalArgumentException.class);
    }
}
