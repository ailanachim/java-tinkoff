package edu.hw3.task8;

import java.util.Iterator;
import java.util.List;

public class BackwardIterator<E> implements Iterator<E> {
    private final List<E> collection;
    int cursor;

    public BackwardIterator(List<E> list) {
        collection = list;
        cursor = list.size() - 1;
    }

    @Override
    public boolean hasNext() {
        return cursor > 0;
    }

    @Override
    public E next() {
        return collection.get(cursor--);
    }
}
