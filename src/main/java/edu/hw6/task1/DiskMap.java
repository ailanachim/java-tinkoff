package edu.hw6.task1;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DiskMap implements Map<String, String> {

    private final Map<String, String> map;
    private Path path;

    public DiskMap(Map<String, String> map, Path path) throws IOException {
        this.map = map;
        this.path = path;

        File file = path.toFile();
        if (!file.exists()) {
            this.path = Files.createFile(path);
        } else {
            loadFromFile();
        }
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public String get(Object key) {
        return map.get(key);
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        var res = map.put(key, value);
        saveToFile();
        return res;
    }

    @Override
    public String remove(Object key) {
        var res = map.remove(key);
        saveToFile();
        return res;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        map.putAll(m);
        saveToFile();
    }

    @Override
    public void clear() {
        map.clear();
        saveToFile();
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        return map.keySet();
    }

    @NotNull
    @Override
    public Collection<String> values() {
        return map.values();
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        return map.entrySet();
    }

    private void saveToFile() {
        try (OutputStream outputStream = Files.newOutputStream(path)) {
            for (Entry<String, String> pair : map.entrySet()) {
                String output =
                    "\"%s\":\"%s\"%s".formatted(pair.getKey(), pair.getValue(), System.lineSeparator());
                outputStream.write(output.getBytes());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadFromFile() {
        try (var inputStream = Files.newInputStream(path)) {
            Scanner scanner = new Scanner(inputStream);

            while (scanner.hasNext()) {
                String input = scanner.nextLine();
                Pattern pattern = Pattern.compile("\"(.*)\":\"(.*)\"");
                Matcher matcher = pattern.matcher(input);

                if (!matcher.matches()) {
                    throw new IllegalStateException("Invalid file format");
                }

                MatchResult result = matcher.toMatchResult();
                String key = result.group(1);
                String value = result.group(2);

                put(key, value);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
