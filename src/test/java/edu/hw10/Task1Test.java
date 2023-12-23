package edu.hw10;

import edu.hw10.task1.RandomObjectGenerator;
import java.lang.reflect.InvocationTargetException;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {

    @Test
    void primitiveTypesTest()
        throws InvocationTargetException, IllegalAccessException, InstantiationException {
        var generator = new RandomObjectGenerator();

        int i = (int) generator.nextObject(int.class);
        boolean b = (boolean) generator.nextObject(boolean.class);
        float f = (float) generator.nextObject(float.class);
        double d = (double) generator.nextObject(double.class);
        long l = (long) generator.nextObject(long.class);
        char c = (char) generator.nextObject(char.class);
    }

    @Test
    void recordTest()
        throws InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        var generator = new RandomObjectGenerator();

        Person person1 = (Person) generator.nextObject(Person.class);
        Person person2 = (Person) generator.nextObject(Person.class, "create");

        assertThat(person1.name()).isNotNull();
        assertThat(person1.age()).isGreaterThanOrEqualTo(10);
        assertThat(person1.height()).isLessThanOrEqualTo(2);

        assertThat(person2.name()).isNotNull();
        assertThat(person2.age()).isGreaterThanOrEqualTo(10);
        assertThat(person2.height()).isLessThanOrEqualTo(2);
    }

    @Test
    void pojoTest()
        throws InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        var generator = new RandomObjectGenerator();

        MyClass o1 = (MyClass) generator.nextObject(MyClass.class);
        MyClass o2 = (MyClass) generator.nextObject(MyClass.class, "create");

        assertThat(o1.name()).isNotNull();
        assertThat(o1.value()).isBetween(0, 100);

        assertThat(o2.name()).isNotNull();
        assertThat(o2.value()).isBetween(0, 100);
    }
}
