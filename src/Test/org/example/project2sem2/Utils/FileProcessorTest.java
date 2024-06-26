package org.example.project2sem2.Utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileProcessorTest {

    private File existingFile;
    private File noReadPermissionsFile;

    @BeforeEach
    void setUp() throws IOException {
        // Create a temporary existing file
        existingFile = File.createTempFile("existingFile", ".txt");
        existingFile.deleteOnExit();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(existingFile))) {
            writer.write("test content");
        }

        // Create a temporary file with no read permissions
        noReadPermissionsFile = File.createTempFile("noReadPermissionsFile", ".txt");
        noReadPermissionsFile.deleteOnExit();
        noReadPermissionsFile.setReadable(false);
    }

    @Test
    void loadDataFromFile() throws IOException {
        // Arrange
        String expectedContent = "voeg iets toe"; // Adjust this to the actual expected content of your file
        File tempFile = File.createTempFile("testfile", ".txt");
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

    @Test
    void loadDataFromFile_noReadPermissions() {
        // Arrange
        FileProcessor fileProcessor = new FileProcessor();

        // Act
        String result = fileProcessor.loadDataFromFile(noReadPermissionsFile.getAbsolutePath());

        // Assert
        assertEquals("", result);
    }

    // Multiple Condition Coverage
    @Test
    void testLoadDataFromFileWithMultipleConditions() {
        FileProcessor fileProcessor = new FileProcessor();

        // Condition: File does not exist
        assertEquals("", fileProcessor.loadDataFromFile("nonexistentfile.txt"));

        // Condition: File exists but no read permissions
        assertEquals("", fileProcessor.loadDataFromFile(noReadPermissionsFile.getAbsolutePath()));

        // Condition: File exists and has read permissions
        assertEquals("test content", fileProcessor.loadDataFromFile(existingFile.getAbsolutePath()));
    }
}
