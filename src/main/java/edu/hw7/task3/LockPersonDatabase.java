package edu.hw7.task3;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockPersonDatabase implements PersonDatabase {
    private final Map<Integer, Person> persons = new HashMap<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    public void add(Person person) {
        lock.writeLock().lock();
        try {
            persons.putIfAbsent(person.id(), person);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void delete(int id) {
        lock.writeLock().lock();
        try {
            persons.remove(id);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public List<Person> findByName(String name) {
        List<Person> founds = new LinkedList<>();

        lock.readLock().lock();
        try {
            for (Person person : persons.values()) {
                if (Objects.equals(person.name(), name)) {
                    founds.add(person);
                }
            }
        } finally {
            lock.readLock().unlock();
        }

        return founds;
    }

    @Override
    public List<Person> findByAddress(String address) {
        List<Person> founds = new LinkedList<>();

        lock.readLock().lock();
        try {
            for (Person person : persons.values()) {
                if (Objects.equals(person.address(), address)) {
                    founds.add(person);
                }
            }
        } finally {
            lock.readLock().unlock();
        }

        return founds;
    }

    @Override
    public List<Person> findByPhone(String phone) {
        List<Person> founds = new LinkedList<>();

        lock.readLock().lock();
        try {
            for (Person person : persons.values()) {
                if (Objects.equals(person.phoneNumber(), phone)) {
                    founds.add(person);
                }
            }
        } finally {
            lock.readLock().unlock();
        }

        return founds;
    }
}
