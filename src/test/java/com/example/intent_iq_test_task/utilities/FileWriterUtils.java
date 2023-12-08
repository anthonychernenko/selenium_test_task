package com.example.intent_iq_test_task.utilities;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileWriterUtils {

    public static void writeToFile(String data, String filePath) {
        try {
            Path path = Paths.get(filePath);
            Files.deleteIfExists(path);
            Files.write(path, data.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException("Failed to write to file", e);
        }
    }
}
