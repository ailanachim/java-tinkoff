package edu.hw6.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task2 {

    private Task2() {
    }

    public static Path cloneFile(Path path) throws IOException {
        if (path == null) {
            throw new IllegalArgumentException("path is null");
        }

        Path copy = path;

        do {
            copy = Path.of(getNewFilename(copy.toString()));
        } while (copy.toFile().exists());

        Files.copy(path, copy);
        return copy;
    }

    private static String getNewFilename(String filename) {
        final Pattern original = Pattern.compile("(.*)(\\..*)");
        final Pattern copied = Pattern.compile("(.*)( — копия(?: \\((\\d+)\\))?)(\\..*)");

        Matcher matcher = copied.matcher(filename);
        if (matcher.matches()) {
            MatchResult matchResult = matcher.toMatchResult();
            final int nameGroup = 1;
            final int copyGroup = 2;
            final int copyNumberGroup = 3;
            final int extensionGroup = 4;

            String name = matchResult.group(nameGroup);
            String extension = matchResult.group(extensionGroup);

            return name + getNextCopyInfo(matchResult.group(copyGroup), matchResult.group(copyNumberGroup)) + extension;
        }

        matcher = original.matcher(filename);
        if (matcher.matches()) {
            MatchResult matchResult = matcher.toMatchResult();
            String name = matchResult.group(1);
            String extension = matchResult.group(2);
            return name + getNextCopyInfo(null, null) + extension;
        }

        return filename + getNextCopyInfo(null, null);
    }

    private static String getNextCopyInfo(String copyInfo, String copyNumber) {
        if (copyInfo == null) {
            return " — копия";
        }
        if (copyNumber == null) {
            return " — копия (2)";
        }

        int number = Integer.parseInt(copyNumber);
        number++;
        return " — копия (%d)".formatted(number);
    }
}
