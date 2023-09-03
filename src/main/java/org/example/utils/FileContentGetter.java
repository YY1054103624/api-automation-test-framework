package org.example.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class FileContentGetter {
    private FileContentGetter() {

    }

    public static String getFileContentAsString(String filePath) {
        Path path = Path.of(filePath);
        try {
            return Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException("File doesn't exist.");
        }
    }
}
