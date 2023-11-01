package edu.project2;

import java.util.List;
import java.util.ListIterator;

public class Path implements Iterable<Coordinate> {

    private final List<Coordinate> path;

    public Path(List<Coordinate> path) {
        if (path == null || path.isEmpty()) {
            throw new IllegalArgumentException();
        }

        ListIterator<Coordinate> iterator = path.listIterator();
        Coordinate coord = iterator.next();

        while (iterator.hasNext()) {
            Coordinate next = iterator.next();
            if (!coord.isNear(next)) {
                throw new IllegalArgumentException();
            }

            coord = next;
        }

        this.path = path;
    }

    public ListIterator<Coordinate> iterator() {
        return path.listIterator();
    }

    public ListIterator<Coordinate> iterator(int index) {
        return path.listIterator(index);
    }

    public int length() {
        return path.size();
    }
}
