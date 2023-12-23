package edu.hw10.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public record Query(Object[] args, Object result, long time) {

    public void saveToDisc(Path path) throws IOException {
        String s = """
            %d
            %s
            %s
            """.formatted(time, Arrays.toString(args), result.toString());

        Files.write(path, s.getBytes());
    }
}
