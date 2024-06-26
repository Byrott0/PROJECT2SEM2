package org.example.project2sem2.Utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class SearchEngineTest {

    private SearchEngine searchEngine;

    @BeforeEach
    public void setUp() {
        searchEngine = new SearchEngine(Languages.UK); // Adjusted to initialize with a language
    }

    @Test
    public void testValidSearchQuery() {
        assertTrue(searchEngine.search("java").stream().anyMatch(result -> result.contains("Java is a high-level")));
    }

    @Test
    public void testEmptySearchQuery() {
        assertTrue(searchEngine.search("").isEmpty());
    }

    @Test
    public void testSearchWithSpecialCharacters() {
        assertTrue(searchEngine.search("@#$%").contains("No documentation found for the provided query."));
    }

    @Test
    public void testSearchWithMultipleConditions() {
        assertFalse(searchEngine.search("java").isEmpty()); // Valid query
        assertTrue(searchEngine.search("").isEmpty()); // Empty query
        assertTrue(searchEngine.search("java @#$%").stream().anyMatch(result -> result.contains("Java is a high-level")));
        assertTrue(searchEngine.search("@#$% java").stream().anyMatch(result -> result.contains("Java is a high-level")));
    }

    @Test
    public void testFindKey() {
        // Adjusted to check for the correct responses based on keywords
        assertTrue(searchEngine.search("How to program in java?").stream().anyMatch(result -> result.contains("Java is a high-level")));
        assertTrue(searchEngine.search("What's new in python?").stream().anyMatch(result -> result.contains("Python is an interpreted")));
        assertTrue(searchEngine.search("What is a programming language?").stream().anyMatch(result -> result.contains("Java is a high-level")));
        assertTrue(searchEngine.search("How to build a social platform application?").stream().anyMatch(result -> result.contains("This module contains the core")));
        assertTrue(searchEngine.search("Tell me about the financial system").stream().anyMatch(result -> result.contains("This module contains the core")));
        assertTrue(searchEngine.search("Explain the domain model").stream().anyMatch(result -> result.contains("This module contains the core")));
        assertTrue(searchEngine.search("How is the weather today?").stream().anyMatch(result -> result.contains("No documentation found")));
    }

    @Test
    public void findKey_WithJavaKeyword_ReturnsJava() {
        // Arrange
        String question = "How to program in java?";

        // Act
        List<String> results = searchEngine.search(question);

        System.out.println(results);

        // Assert
        assertTrue(results.stream().anyMatch(result -> result.contains("Java is a high-level, object-oriented programming language")));
    }

    @Test
    public void findKey_NoKeyword_ReturnsNull() {
        // Arrange
        String question = "How is the weather today?"; // No keywords present

        // Act
        List<String> results = searchEngine.search(question);

        // Assert
        assertTrue(results.contains("No documentation found for the provided query."));
    }

    @Test
    public void findAnswer_WithPythonKeyword_ReturnsPythonResponse() {

        String question = "What's new in python?";


        assertTrue(searchEngine.search(question).stream().anyMatch(result -> result.contains("Python is an interpreted")));
    }

    @Test
    public void findAnswer_NoMatchingKeyword_ReturnsNoDataFoundMessage() {
        // Arrange
        String question = "What is the weather today?";
        String expectedResponse = "No documentation found for the provided query.";

        // Act
        List<String> results = searchEngine.search(question);

        // Assert
        assertTrue(results.contains(expectedResponse));
    }

    @Test
    public void getResponse_ValidKeywordQuery_ReturnsCorrectResponse() {

        String input = "Tell me about financial system";

        assertTrue(searchEngine.search(input).stream().anyMatch(result -> result.contains("This module contains")));
    }

    @Test
    public void getResponse_NoKeywordQuery_ReturnsNoDataFoundMessage() {

        String input = "How many countries are in Europe?";
        String expectedResponse = "No documentation found for the provided query.";


        List<String> results = searchEngine.search(input);


        assertTrue(results.contains(expectedResponse));
    }
}
