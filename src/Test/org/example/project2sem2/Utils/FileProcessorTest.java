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
        String expectedContent = "This is a test content.";
        File tempFile = File.createTempFile("testfile", ".txt");
        tempFile.deleteOnExit(); // Zorg ervoor dat het bestand na de test wordt verwijderd

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
