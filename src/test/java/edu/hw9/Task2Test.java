package edu.hw9;

import edu.hw9.task2.Directory;
import edu.hw9.task2.File;
import edu.hw9.task2.FileVisitor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {

    static Directory root;
    static Directory[] directories;

    @BeforeAll
    static void initDirectories() {
        int n1 = 1000;
        List<File> files1 = new ArrayList<>(n1);
        for (int i = 0; i < n1; i++) {
            files1.add(new File("document_" + i + ".docx", i));
        }

        Directory directory1 = new Directory("Documents", files1.toArray(new File[0]), new Directory[] {});

        int n2 = 10;
        List<File> files2 = new ArrayList<>(n2);
        for (int i = 0; i < n2; i++) {
            files2.add(new File("photo_" + i + ".jpg", i * 15));
        }

        Directory directory2 = new Directory("Pictures", files2.toArray(new File[0]), new Directory[] {});

        int n3 = 3;
        List<File> files3 = new ArrayList<>(n3);
        for (int i = 0; i < n3; i++) {
            files3.add(new File("video_" + i + ".mov", i * 100));
        }

        Directory directory3 = new Directory("Movies", files3.toArray(new File[0]), new Directory[] {});

        int n4 = 5;
        List<File> files4 = new ArrayList<>(n4);
        for (int i = 0; i < n4; i++) {
            files4.add(new File("file_" + i + ".pdf", i * 10));
        }

        Directory directory4 =
            new Directory("Data", files4.toArray(new File[0]), new Directory[] {directory2, directory3});

        int n5 = 1000;
        List<File> files5 = new ArrayList<>(n5);
        for (int i = 0; i < n5; i++) {
            files5.add(new File("text_" + i + ".txt", i));
        }

        root = new Directory("Common", files5.toArray(new File[0]), new Directory[] {directory1, directory4});
        directories = new Directory[] {directory1, directory2, directory3, directory4};
    }

    @Test
    void directorySizeFilterTest() {
        var result = FileVisitor.findDirectories(root, directory -> directory.files().length >= 1000);

        assertThat(result).containsOnly(root, directories[0]);
    }

    @Test
    void fileSizeFilterTest() {
        var result = FileVisitor.findFiles(root, file -> file.size() == 10);

        assertThat(result).containsOnly(
            new File("document_10.docx", 10),
            new File("file_1.pdf", 10),
            new File("text_10.txt", 10)
        );
    }

    @Test
    void fileExtensionFilterTest() {
        var result = FileVisitor.findFiles(root, file -> file.name().endsWith(".mov"));

        assertThat(result).containsOnly(
            new File("video_0.mov", 0),
            new File("video_1.mov", 100),
            new File("video_2.mov", 200)
        );
    }
}
