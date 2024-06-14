package org.example.project2sem2.Utils;

import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

class FileProcessorTest {

    @Test
    void loadDataFromFile() throws IOException {
        // Arrange
        String expectedContent = "voeg iets toe";// Pas dit aan naar de werkelijke verwachte inhoud van je bestand
        File tempFile = File.createTempFile("testfile", ".txt");// Correcte relatieve pad zonder 'src/main/resources/'
        tempFile.deleteOnExit(); // Ensure the file is deleted after the test

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write(expectedContent);
        }

        FileProcessor fileProcessor = new FileProcessor();

        // Act
        String actualContent = fileProcessor.loadDataFromFile(tempFile.getAbsolutePath());

        // Assert
        assertEquals(expectedContent, actualContent);
    }

    @Test
    void loadDataFromFile_fileNotFound() {
        // Arrange
        FileProcessor fileProcessor = new FileProcessor();

        // Act
        String result = fileProcessor.loadDataFromFile("nonexistentfile.txt");

        // Assert
        assertEquals("", result);
    }
}
