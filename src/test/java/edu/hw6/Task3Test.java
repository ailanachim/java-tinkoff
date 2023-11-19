package edu.hw6;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static edu.hw6.task3.AbstractFilter.globMatches;
import static edu.hw6.task3.AbstractFilter.isExecutable;
import static edu.hw6.task3.AbstractFilter.isReadable;
import static edu.hw6.task3.AbstractFilter.isRegularFile;
import static edu.hw6.task3.AbstractFilter.isWritable;
import static edu.hw6.task3.AbstractFilter.largerThan;
import static edu.hw6.task3.AbstractFilter.lessThan;
import static edu.hw6.task3.AbstractFilter.magicNumber;
import static edu.hw6.task3.AbstractFilter.regexContains;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {

    private static final Path DIR = Path.of("src/test/java/edu/hw6/test_files");
    private static final Path EXECUTABLE = Path.of("src/test/java/edu/hw6/test_files/alg.exe");
    private static final Path PICTURE = Path.of("src/test/java/edu/hw6/test_files/bird.png");
    private static final Path TEXT_FILE = Path.of("src/test/java/edu/hw6/test_files/example_text.txt");

    static Arguments[] filters() {
        File file1 = EXECUTABLE.toFile();
        file1.setReadable(false);
        file1.setWritable(false);
        file1.setExecutable(true);

        File file2 = PICTURE.toFile();
        file2.setReadable(true);
        file2.setWritable(false);
        file2.setExecutable(false);

        File file3 = TEXT_FILE.toFile();
        file3.setReadable(true);
        file3.setWritable(true);
        file3.setExecutable(false);

        List<Path> executables = Stream.of(EXECUTABLE, PICTURE, TEXT_FILE)
            .filter(Files::isExecutable).toList();

        List<Path> readables = Stream.of(EXECUTABLE, PICTURE, TEXT_FILE)
            .filter(Files::isReadable).toList();

        List<Path> writables = Stream.of(EXECUTABLE, PICTURE, TEXT_FILE)
            .filter(Files::isWritable).toList();

        return new Arguments[] {
            Arguments.of(isRegularFile(), List.of(EXECUTABLE, PICTURE, TEXT_FILE)),
            Arguments.of(isReadable(), readables),
            Arguments.of(isWritable(), writables),
            Arguments.of(isExecutable(), executables),
            Arguments.of(largerThan(10000), List.of(EXECUTABLE, PICTURE)),
            Arguments.of(lessThan(10000), List.of(TEXT_FILE)),
            Arguments.of(magicNumber((byte) 0x89, (byte) 'P', (byte) 'N', (byte) 'G'), List.of(PICTURE)),
            Arguments.of(globMatches("*.png"), List.of(PICTURE)),
            Arguments.of(regexContains("_text"), List.of(TEXT_FILE)),

            Arguments.of(
                isRegularFile()
                    .and(isReadable())
                    .and(isWritable())
                    .and(lessThan(100))
                    .and(largerThan(10))
                    .and(globMatches("*.txt"))
                    .and(regexContains("_")),
                List.of(TEXT_FILE)
            ),
        };
    }

    @ParameterizedTest
    @MethodSource("filters")
    void test(DirectoryStream.Filter<Path> filter, List<Path> expected) throws IOException {
        List<Path> list = new ArrayList<>();

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(DIR, filter)) {
            entries.forEach(list::add);
        }

        Collections.sort(list);

        assertThat(list).isEqualTo(expected);
    }
}
