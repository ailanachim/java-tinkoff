package edu.project2;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class GeneratorTest {

    static Arguments[] generators() {
        return new Arguments[] {Arguments.of(new RecursiveBacktrackingGenerator()),
            Arguments.of(new KruskalGenerator())};
    }

    @ParameterizedTest()
    @MethodSource("generators")
    void generatorTest(Generator generator) {
        assertThat(generator.generate(10, 15)).isNotNull();
        assertThatThrownBy(() -> generator.generate(2, 3)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> generator.generate(0, 0)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> generator.generate(-3, -5)).isInstanceOf(IllegalArgumentException.class);
    }
}
