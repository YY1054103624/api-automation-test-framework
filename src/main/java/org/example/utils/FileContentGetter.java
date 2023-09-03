package org.example.utils;

import org.example.exceptions.FrameworkFileIOException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * This class is for getting the content of a specific file.
 * <p>
* 2023/9/3
* @author Yong Yang
* @version 1.0
* @since 1.0
*/
public final class FileContentGetter {
    private FileContentGetter() {

    }

    /**
     * Get the content of a request body file.
     * @param filePath the path of a file.
     * @return file content as {@link java.lang.String}.
     */
    public static String getFileContentAsString(String filePath) {
        Path path = Path.of(filePath);
        try {
            return Files.readString(path);
        } catch (IOException e) {
            throw new FrameworkFileIOException("There's an io exception", e);
        }
    }
}
