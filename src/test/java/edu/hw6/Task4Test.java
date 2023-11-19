package edu.hw6;

import edu.hw6.task4.Task4;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static edu.hw6.task4.Task4.writeToFile;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {

    @Test
    void test() throws IOException {
        Path path = Path.of("temp.txt");
        Files.createFile(path);

        writeToFile(path);

        assertThat(Files.readAllBytes(path)).isEqualTo(Task4.TEXT.getBytes());

        Files.deleteIfExists(path);
    }
}
