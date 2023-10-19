package edu.hw2;

import edu.hw2.task1.Expr.*;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {

    @Test void constantTest() {
        var a = new Constant(2);
        var b = new Constant(0);
        var c = new Constant(-5);
        var d = new Constant(4.7);
        var e = new Constant(219316567);

        assertThat(a.evaluate()).isEqualTo(2);
        assertThat(b.evaluate()).isEqualTo(0);
        assertThat(c.evaluate()).isEqualTo(-5);
        assertThat(d.evaluate()).isEqualTo(4.7);
        assertThat(e.evaluate()).isEqualTo(219316567);
    }

    @Test void negateTest() {
        var a = new Negate(new Constant(2));
        var b = new Negate(new Constant(0));
        var c = new Negate(new Constant(-3));

        assertThat(a.evaluate()).isEqualTo(-2);
        assertThat(b.evaluate()).isEqualTo(0);
        assertThat(c.evaluate()).isEqualTo(3);
    }

    @Test void additionTest() {
        var a = new Constant(2);
        var b =  new Constant(3);
        var c = new Constant(0);
        var d = new Negate(new Constant(9));

        var add1 = new Addition(a, b);
        var add2 = new Addition(add1, c);
        var add3 = new Addition(d, add2);
        var add4 = new Addition(add1, add3);

        assertThat(add1.evaluate()).isEqualTo(5);
        assertThat(add2.evaluate()).isEqualTo(5);
        assertThat(add3.evaluate()).isEqualTo(-4);
        assertThat(add4.evaluate()).isEqualTo(1);
    }

    @Test void multiplicationTest() {
        var a = new Constant(2);
        var b =  new Constant(3);
        var c = new Constant(0);
        var d = new Negate(new Constant(9));

        var mult1 = new Multiplication(a, b);
        var mult2 = new Multiplication(mult1, c);
        var mult3 = new Multiplication(d, mult1);
        var mult4 = new Multiplication(mult1, mult3);

        assertThat(mult1.evaluate()).isEqualTo(6);
        assertThat(mult2.evaluate()).isEqualTo(0);
        assertThat(mult3.evaluate()).isEqualTo(-54);
        assertThat(mult4.evaluate()).isEqualTo(-324);
    }

    @Test void exponentTest() {
        var a = new Constant(2);
        var b = new Constant(0);
        var c = new Constant(9);

        var exp1 = new Exponent(a, 5);
        var exp2 = new Exponent(b, 2);
        var exp3 = new Exponent(c, 0.5);
        var exp4 = new Exponent(exp1, 0.2);

        assertThat(exp1.evaluate()).isEqualTo(32);
        assertThat(exp2.evaluate()).isEqualTo(0);
        assertThat(exp3.evaluate()).isEqualTo(3);
        assertThat(exp4.evaluate()).isEqualTo(2);
    }

    @Test void mixedTest() {
        var two = new Constant(2);
        var four = new Constant(4);
        var negOne = new Negate(new Constant(1));
        var sumTwoFour = new Addition(two, four);
        var mult = new Multiplication(sumTwoFour, negOne);
        var exp = new Exponent(mult, 2);
        var res = new Addition(exp, new Constant(1));

        assertThat(res.evaluate()).isEqualTo(37);
    }
}
