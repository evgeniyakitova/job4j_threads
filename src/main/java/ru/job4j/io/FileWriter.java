package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public final class FileWriter {
    private final File file;

    public FileWriter(File file) {
        this.file = file;
    }

    public void saveContent(String content) throws IOException {
        synchronized (file) {
            Files.writeString(file.toPath(), content);
        }
    }
}
