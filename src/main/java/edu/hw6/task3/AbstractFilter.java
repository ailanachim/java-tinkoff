package edu.hw6.task3;

import java.io.FileInputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface AbstractFilter extends DirectoryStream.Filter<Path> {

    default AbstractFilter and(AbstractFilter other) {
        return entry -> this.accept(entry) && other.accept(entry);
    }

    static AbstractFilter largerThan(long size) {
        return entry -> entry.toFile().length() > size;
    }

    static AbstractFilter lessThan(long size) {
        return entry -> entry.toFile().length() < size;
    }

    static AbstractFilter isReadable() {
        return Files::isReadable;
    }

    static AbstractFilter isWritable() {
        return Files::isWritable;
    }

    static AbstractFilter isExecutable() {
        return Files::isExecutable;
    }

    static AbstractFilter isRegularFile() {
        return Files::isRegularFile;
    }

    static AbstractFilter magicNumber(byte... numbers) {
        return entry -> {
            try (FileInputStream fileInputStream = new FileInputStream(entry.toFile())) {
                byte[] read = fileInputStream.readNBytes(numbers.length);
                return Arrays.equals(read, numbers);
            }
        };
    }

    static AbstractFilter globMatches(String glob) {
        return entry -> {
            FileSystem fs = entry.getFileSystem();
            final PathMatcher matcher = fs.getPathMatcher("glob:" + glob);
            return matcher.matches(entry.getFileName());
        };
    }

    static AbstractFilter regexContains(String regex) {
        return entry -> {
            final Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(entry.toString());
            return matcher.find();
        };
    }
}
