package org.example.project2sem2.Utils;

import java.util.*;

public class ElasticSearch {
    private Map<String, String> searchResponses;
    private FileProcessor fileProcessor;

    public ElasticSearch() {
        searchResponses = new HashMap<>();
        fileProcessor = new FileProcessor();
        loadSearchResponses();
    }

    private void loadSearchResponses() {
        // Load your predefined responses here
        searchResponses.put("social platform application", fileProcessor.loadDataFromFile("src/main/resources/files/ElasticSearch/social_platform_application.txt"));
        searchResponses.put("financial system", fileProcessor.loadDataFromFile("src/main/resources/files/ElasticSearch/financial_system.txt"));
        searchResponses.put("domain model", fileProcessor.loadDataFromFile("src/main/resources/files/ElasticSearch/domain_model.txt"));
        // Add more predefined responses as needed
    }

    public String searchDocumentation(String keyword) {
        return searchResponses.getOrDefault(keyword, "No documentation found for the provided keyword.");
    }

    public String findKey(String keyword) {
        return searchResponses.containsKey(keyword) ? keyword : null;
    }

    public List<String> getKeys() {
        return new ArrayList<>(searchResponses.keySet());
    }

    public String findAnswer(String keyword) {
        return searchDocumentation(keyword);
    }

    public Map<String, String> getResponses() {
        return searchResponses;
    }
}
