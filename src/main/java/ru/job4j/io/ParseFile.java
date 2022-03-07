package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.util.function.Predicate;

public final class ParseFile {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }


    public String getAllContent() throws IOException {
        synchronized (file) {
            return Files.readString(file.toPath());
        }
    }

    public String getContentWithoutUnicode(Predicate<Character> filter) throws IOException {
        synchronized (file) {
            StringBuilder output = new StringBuilder();
            int data;
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                while ((data = bufferedReader.read()) != -1) {
                    char character = (char) data;
                    if (filter.test(character)) {
                        output.append(character);
                    }
                }
            }
            return output.toString();
        }
    }
}
