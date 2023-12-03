package edu.hw6;

import edu.hw6.task1.DiskMap;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {

    private static final Path PATH = Path.of("diskmap.txt");

    @Test
    void test() throws IOException {
        DiskMap diskMap = new DiskMap(new HashMap<>(), PATH);

        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");
        diskMap.put("key3", "value3");

        DiskMap copyDiskMap = new DiskMap(new HashMap<>(), PATH);

        assertThat(copyDiskMap.get("key1")).isEqualTo("value1");
        assertThat(copyDiskMap.get("key2")).isEqualTo("value2");
        assertThat(copyDiskMap.get("key3")).isEqualTo("value3");
    }

    @AfterAll
    static void deleteFile() throws IOException {
        Files.deleteIfExists(PATH);
    }
}
