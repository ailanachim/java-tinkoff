package edu.hw7.task3;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SyncPersonDatabase implements PersonDatabase {

    private final Map<Integer, Person> persons = new HashMap<>();

    @Override
    public synchronized void add(Person person) {
        persons.putIfAbsent(person.id(), person);
    }

    @Override
    public synchronized void delete(int id) {
        persons.remove(id);
    }

    @Override
    public synchronized List<Person> findByName(String name) {
        List<Person> founds = new LinkedList<>();

        for (Person person : persons.values()) {
            if (Objects.equals(person.name(), name)) {
                founds.add(person);
            }
        }

        return founds;
    }

    @Override
    public synchronized List<Person> findByAddress(String address) {
        List<Person> founds = new LinkedList<>();

        for (Person person : persons.values()) {
            if (Objects.equals(person.address(), address)) {
                founds.add(person);
            }
        }

        return founds;
    }

    @Override
    public synchronized List<Person> findByPhone(String phone) {
        List<Person> founds = new LinkedList<>();

        for (Person person : persons.values()) {
            if (Objects.equals(person.phoneNumber(), phone)) {
                founds.add(person);
            }
        }

        return founds;
    }
}
