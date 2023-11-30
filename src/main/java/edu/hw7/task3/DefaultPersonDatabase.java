package edu.hw7.task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DefaultPersonDatabase implements PersonDatabase {

    private final Map<Integer, Person> persons = new HashMap<>();

    @Override
    public void add(Person person) {
        persons.putIfAbsent(person.id(), person);
    }

    @Override
    public void delete(int id) {
        persons.remove(id);
    }

    @Override
    public List<Person> findByName(String name) {
        List<Person> founds = new ArrayList<>();

        for (Person person : persons.values()) {
            if (Objects.equals(person.name(), name)) {
                founds.add(person);
            }
        }

        return founds;
    }

    @Override
    public List<Person> findByAddress(String address) {
        List<Person> founds = new ArrayList<>();

        for (Person person : persons.values()) {
            if (Objects.equals(person.address(), address)) {
                founds.add(person);
            }
        }

        return founds;
    }

    @Override
    public List<Person> findByPhone(String phone) {
        List<Person> founds = new ArrayList<>();

        for (Person person : persons.values()) {
            if (Objects.equals(person.phoneNumber(), phone)) {
                founds.add(person);
            }
        }

        return founds;
    }
}
