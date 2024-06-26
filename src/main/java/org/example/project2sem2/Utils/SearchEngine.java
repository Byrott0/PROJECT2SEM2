package org.example.project2sem2.Utils;

import java.nio.file.*;
import java.io.IOException;
import java.util.*;

public class SearchEngine {
    private Map<String, String> keywordSources; // Map to hold the source for each keyword
    private Map<String, Map<String, String>> keywordResponses;
    private Languages languageCode;
    private ResourceSelector resourceSelector;
    private ElasticSearch elasticSearch;

    public SearchEngine(Languages initialLanguage) {
        keywordSources = new HashMap<>();
        keywordResponses = new HashMap<>();
        resourceSelector = new ResourceSelector();
        elasticSearch = new ElasticSearch();
        this.languageCode = initialLanguage;  // Set the initial language code here
        loadKeywordSources();
        loadKeywordResponses();
    }

    private void loadKeywordSources() {
        // Define the source for each keyword
        keywordSources.put(Keywords.JAVA.getKey(), "ResourceSelector");
        keywordSources.put(Keywords.PYTHON.getKey(), "ResourceSelector");
        keywordSources.put(Keywords.LANGUAGE.getKey(), "ResourceSelector");
        keywordSources.put(Keywords.SOCIAL_PLATFORM_APPLICATION.getKey(), "ElasticSearch");
        keywordSources.put(Keywords.FINANCIAL_SYSTEM.getKey(), "ElasticSearch");
        keywordSources.put(Keywords.DOMAIN_MODEL.getKey(), "ElasticSearch");
        // Add more keywords and their respective sources as needed
    }

    private void loadKeywordResponses() {
        FileProcessor fileProcessor = new FileProcessor();

        for (Keywords keyword : Keywords.values()) {
            String key = keyword.getKey();
            Map<String, String> responses = new HashMap<>();
            responses.put(languageCode.toString(), fileProcessor.loadDataFromFile("src/main/resources/files/" + languageCode + "/" + key + ".txt"));
            keywordResponses.put(key, responses);
        }
    }

    public List<String> search(String query) {
        if (query == null || query.trim().isEmpty()) {
            return Collections.emptyList();
        }

        String source = keywordSources.get(query);
        if (source == null) {
            return Collections.singletonList("No documentation found for the provided keyword.");
        }

        List<String> results = new ArrayList<>();
        String response;

        switch (source) {
            case "ResourceSelector":
                response = resourceSelector.getDocumentation(query);
                break;
            case "ElasticSearch":
                response = elasticSearch.searchDocumentation(query);
                break;
            default:
                response = "No documentation found for the provided keyword.";
        }

        if (!response.contains("No documentation found")) {
            results.add(loadResponseFromFile(query, source));
        } else {
            results.add(response);
        }

        return results;
    }

    private String loadResponseFromFile(String keyword, String source) {
        String filePath = "src/main/resources/files/" + languageCode + "/" + source + "/" + keyword + ".txt";
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            return "Error loading file: " + filePath;
        }
    }

    public void setLanguagecode(Languages language) {
        this.languageCode = language;
        loadKeywordResponses(); // Reload responses for the new language
    }

    public List<String> getKeys() {
        return new ArrayList<>(keywordSources.keySet());
    }
}
