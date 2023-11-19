package edu.hw6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static edu.hw6.task2.Task2.cloneFile;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {

    private static final Path PATH = Path.of("Tinkoff Bank Biggest Secret.txt");
    private static final Path COPY1 = Path.of("Tinkoff Bank Biggest Secret — копия.txt");
    private static final Path COPY2 = Path.of("Tinkoff Bank Biggest Secret — копия (2).txt");
    private static final Path COPY3 = Path.of("Tinkoff Bank Biggest Secret — копия (3).txt");
    private static final byte[] CONTENT = "Hello, world!".getBytes();

    @BeforeAll
    static void createFile() throws IOException {
        Files.write(PATH, CONTENT);
    }

    @Test
    void test() throws IOException {
        Path copy1 = cloneFile(PATH);
        Path copy2 = cloneFile(PATH);
        Path copy3 = cloneFile(PATH);

        assertThat(copy1).isEqualTo(COPY1);
        assertThat(copy2).isEqualTo(COPY2);
        assertThat(copy3).isEqualTo(COPY3);

        assertThat(Files.readAllBytes(copy1)).isEqualTo(CONTENT);
        assertThat(Files.readAllBytes(copy2)).isEqualTo(CONTENT);
        assertThat(Files.readAllBytes(copy3)).isEqualTo(CONTENT);
    }

    @AfterAll
    static void deleteFile() throws IOException {
        Files.deleteIfExists(PATH);
        Files.deleteIfExists(COPY1);
        Files.deleteIfExists(COPY2);
        Files.deleteIfExists(COPY3);
    }
}
