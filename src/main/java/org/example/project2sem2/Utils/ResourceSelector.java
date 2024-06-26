package org.example.project2sem2.Utils;

import java.util.*;

public class ResourceSelector {
    private Map<String, String> documentationResponses;
    private FileProcessor fileProcessor;

    public ResourceSelector() {
        documentationResponses = new HashMap<>();
        fileProcessor = new FileProcessor();
        loadDocumentationResponses();
    }

    private void loadDocumentationResponses() {
        // Load your predefined responses here
        documentationResponses.put("java", fileProcessor.loadDataFromFile("src/main/resources/files/ResourceSelector/java.txt"));
        documentationResponses.put("python", fileProcessor.loadDataFromFile("src/main/resources/files/ResourceSelector/python.txt"));
        documentationResponses.put("language", fileProcessor.loadDataFromFile("src/main/resources/files/ResourceSelector/language.txt"));
        // Add more predefined responses as needed
    }

    public String getDocumentation(String keyword) {
        return documentationResponses.getOrDefault(keyword, "No documentation found for the provided keyword.");
    }

    public String findKey(String keyword) {
        return documentationResponses.containsKey(keyword) ? keyword : null;
    }

    public List<String> getKeys() {
        return new ArrayList<>(documentationResponses.keySet());
    }

    public String findAnswer(String keyword) {
        return getDocumentation(keyword);
    }

    public Map<String, String> getResponses() {
        return documentationResponses;
    }
}
