package edu.hw9.task2;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.function.Predicate;

public class FileVisitor {

    private FileVisitor() {
    }

    public static Directory[] findDirectories(Directory src, Predicate<Directory> predicate) {
        if (src == null || predicate == null) {
            throw new IllegalArgumentException();
        }

        List<Directory> founds = Collections.synchronizedList(new LinkedList<>());
        try (ForkJoinPool executor = new ForkJoinPool()) {
            var task = new DirectoryTask(src, predicate, founds);
            executor.execute(task);
        }

        return founds.toArray(new Directory[0]);
    }

    public static File[] findFiles(Directory src, Predicate<File> predicate) {
        if (src == null || predicate == null) {
            throw new IllegalArgumentException();
        }

        List<File> founds = Collections.synchronizedList(new LinkedList<>());
        try (ForkJoinPool executor = new ForkJoinPool()) {
            var task = new FileTask(src, predicate, founds);
            executor.execute(task);
        }

        return founds.toArray(new File[0]);
    }

    static class DirectoryTask extends RecursiveAction {

        Directory src;
        Predicate<Directory> predicate;
        List<Directory> founds;

        DirectoryTask(Directory src, Predicate<Directory> predicate, List<Directory> founds) {
            this.src = src;
            this.predicate = predicate;
            this.founds = founds;
        }

        @Override
        protected void compute() {
            if (predicate.test(src)) {
                founds.add(src);
            }

            for (var dir : src.directories()) {
                var task = new DirectoryTask(dir, predicate, founds);
                task.fork();
            }
        }
    }

    static class FileTask extends RecursiveAction {

        Directory src;
        Predicate<File> predicate;
        List<File> founds;

        FileTask(Directory src, Predicate<File> predicate, List<File> founds) {
            this.src = src;
            this.predicate = predicate;
            this.founds = founds;
        }

        @Override
        protected void compute() {
            for (var dir : src.directories()) {
                var task = new FileTask(dir, predicate, founds);
                task.fork();
            }

            for (var file : src.files()) {
                if (predicate.test(file)) {
                    founds.add(file);
                }
            }
        }
    }
}
