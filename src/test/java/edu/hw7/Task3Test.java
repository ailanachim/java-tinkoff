package edu.hw7;

import edu.hw7.task3.LockPersonDatabase;
import edu.hw7.task3.Person;
import edu.hw7.task3.PersonDatabase;
import edu.hw7.task3.SyncPersonDatabase;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {

    static Stream<PersonDatabase> databases() {
        return Stream.of(new SyncPersonDatabase(), new LockPersonDatabase());
    }

    @ParameterizedTest
    @MethodSource("databases")
    void addTest(PersonDatabase db) throws InterruptedException {
        Person person = new Person(1, "Tom", "Saint-Petersburg", "8912840194");
        Person person2 = new Person(2, "John", "Saint-Petersburg", "891285687");
        Person person3 = new Person(3, "Steve", "Moscow", "8985478665");

        Thread thread = new Thread(() -> db.add(person));
        Thread thread2 = new Thread(() -> db.add(person2));
        Thread thread3 = new Thread(() -> db.add(person3));
        Thread thread4 = new Thread(() -> db.add(person3));

        thread.start();
        thread2.start();
        thread3.start();
        thread4.start();

        thread.join();
        thread2.join();
        thread3.join();
        thread4.join();

        assertThat(db.findByName(person.name())).isEqualTo(List.of(person));
        assertThat(db.findByName(person2.name())).isEqualTo(List.of(person2));
        assertThat(db.findByName(person3.name())).isEqualTo(List.of(person3));

        assertThat(db.findByAddress(person.address())).isEqualTo(List.of(person, person2));
        assertThat(db.findByAddress(person2.address())).isEqualTo(List.of(person, person2));
        assertThat(db.findByAddress(person3.address())).isEqualTo(List.of(person3));

        assertThat(db.findByPhone(person.phoneNumber())).isEqualTo(List.of(person));
        assertThat(db.findByPhone(person2.phoneNumber())).isEqualTo(List.of(person2));
        assertThat(db.findByPhone(person3.phoneNumber())).isEqualTo(List.of(person3));
    }

    @ParameterizedTest
    @MethodSource("databases")
    void deleteTest(PersonDatabase db) throws InterruptedException {
        Person person = new Person(1, "Tom", "Saint-Petersburg", "8912840194");
        Person person2 = new Person(2, "John", "Saint-Petersburg", "891285687");
        Person person3 = new Person(3, "Steve", "Moscow", "8985478665");

        db.add(person);
        db.add(person2);
        db.add(person3);

        Thread thread = new Thread(() -> db.delete(person.id()));
        Thread thread2 = new Thread(() -> db.delete(person2.id()));
        Thread thread3 = new Thread(() -> db.delete(person3.id()));
        Thread thread4 = new Thread(() -> db.delete(person3.id()));

        thread.start();
        thread2.start();
        thread3.start();
        thread4.start();

        thread.join();
        thread2.join();
        thread3.join();
        thread4.join();

        assertThat(db.findByName(person.name())).isEqualTo(List.of());
        assertThat(db.findByName(person2.name())).isEqualTo(List.of());
        assertThat(db.findByName(person3.name())).isEqualTo(List.of());

        assertThat(db.findByAddress(person.address())).isEqualTo(List.of());
        assertThat(db.findByAddress(person2.address())).isEqualTo(List.of());
        assertThat(db.findByAddress(person3.address())).isEqualTo(List.of());

        assertThat(db.findByPhone(person.phoneNumber())).isEqualTo(List.of());
        assertThat(db.findByPhone(person2.phoneNumber())).isEqualTo(List.of());
        assertThat(db.findByPhone(person3.phoneNumber())).isEqualTo(List.of());
    }

    @ParameterizedTest
    @MethodSource("databases")
    void mixTest(PersonDatabase db) throws InterruptedException {
        Person person = new Person(1, "Tom", "Saint-Petersburg", "8912840194");
        Person person2 = new Person(2, "John", "Saint-Petersburg", "891285687");
        Person person3 = new Person(3, "Steve", "Moscow", "8985478665");

        db.add(person);
        db.add(person3);

        Thread thread = new Thread(() -> db.findByAddress(person2.address()));
        Thread thread2 = new Thread(() -> db.add(person2));
        Thread thread3 = new Thread(() -> db.findByName(person.name()));
        Thread thread4 = new Thread(() -> db.delete(person.id()));
        Thread thread5 = new Thread(() -> db.add(person2));
        Thread thread6 = new Thread(() -> db.delete(person3.id()));
        Thread thread7 = new Thread(() -> db.findByPhone(person3.phoneNumber()));

        thread.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        thread7.start();

        thread.join();
        thread2.join();
        thread3.join();
        thread4.join();
        thread5.join();
        thread6.join();
        thread7.join();

        assertThat(db.findByName(person.name())).isEqualTo(List.of());
        assertThat(db.findByName(person2.name())).isEqualTo(List.of(person2));
        assertThat(db.findByName(person3.name())).isEqualTo(List.of());

        assertThat(db.findByAddress(person.address())).isEqualTo(List.of(person2));
        assertThat(db.findByAddress(person2.address())).isEqualTo(List.of(person2));
        assertThat(db.findByAddress(person3.address())).isEqualTo(List.of());

        assertThat(db.findByPhone(person.phoneNumber())).isEqualTo(List.of());
        assertThat(db.findByPhone(person2.phoneNumber())).isEqualTo(List.of(person2));
        assertThat(db.findByPhone(person3.phoneNumber())).isEqualTo(List.of());
    }
}
