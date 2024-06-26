package org.example.project2sem2.Utils;

import java.nio.file.*;
import java.io.IOException;

public class FileProcessor {
    public String loadDataFromFile(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            return "Error loading file: " + filePath;
        }
    }
}
