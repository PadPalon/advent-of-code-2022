package ch.neukom.advent2022.util;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;

import org.jetbrains.annotations.Nullable;

public class InputResourceReader implements Closeable, AutoCloseable {
    private final Class<?> clazz;

    private BufferedReader reader;

    public InputResourceReader(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Stream<String> readInput() {
        return readInput("input");
    }

    public Stream<String> readInput(String filename) {
        openReader(filename);

        if (reader == null) {
            return Stream.empty();
        } else {
            return reader.lines();
        }
    }

    public String getFirstLine() {
        return readInput().findFirst().orElseThrow();
    }

    public long getLineCount() {
        return readInput().count();
    }

    private void openReader(String filename) {
        if (reader != null) {
            close();
        }

        InputStream inputStream = clazz.getResourceAsStream(filename);
        if (inputStream == null) {
            System.err.println("Could not read input");
        } else {
            reader = inputStream.bufferedReader();
        }
    }

    @Override
    public void close() throws IOException {
        if (reader != null) {
            reader.close();
        }
    }
}
