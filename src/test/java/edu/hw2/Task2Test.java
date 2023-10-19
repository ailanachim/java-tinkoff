package edu.hw2;

import org.junit.jupiter.api.Test;
import edu.hw2.task2.Rectangle;
import edu.hw2.task2.Square;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {

    @Test void rectangleTest() {
        var rect1 = new Rectangle(10, 20);
        var rect2 = new Rectangle(5, 3);
        var rect3 = new Rectangle(4, 6);

        assertThat(rect1.area()).isEqualTo(200);
        assertThat(rect2.area()).isEqualTo(15);
        assertThat(rect3.area()).isEqualTo(24);
    }

    @Test void squareTest() {
        var square1 = new Square(10);
        var square2 = new Square(3);
        var square3 = new Square(1);

        assertThat(square1.area()).isEqualTo(100);
        assertThat(square2.area()).isEqualTo(9);
        assertThat(square3.area()).isEqualTo(1);
    }
}
