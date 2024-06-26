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

    private String searchSelector = "ResourceSelector";

    private String searchElastic = "ElasticSearch";

    public SearchEngine(Languages initialLanguage) {
        this.languageCode = initialLanguage;
        keywordSources = new HashMap<>();
        keywordResponses = new HashMap<>();
        resourceSelector = new ResourceSelector(this.languageCode);
        elasticSearch = new ElasticSearch(this.languageCode);
        this.languageCode = initialLanguage;  // Set the initial language code here
        loadKeywordSources();
        loadKeywordResponses();
    }


    private void loadKeywordSources() {
        // Define the source for each keyword
        keywordSources.put(Keywords.JAVA.getKey(), searchSelector);
        keywordSources.put(Keywords.PYTHON.getKey(), searchSelector);
        keywordSources.put(Keywords.LANGUAGE.getKey(), searchSelector);
        keywordSources.put(Keywords.SOCIAL_PLATFORM_APPLICATION.getKey(), searchElastic);
        keywordSources.put(Keywords.FINANCIAL_SYSTEM.getKey(), searchElastic);
        keywordSources.put(Keywords.DOMAIN_MODEL.getKey(), searchElastic);
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

        List<String> results = new ArrayList<>();

        for (String keyword : keywordSources.keySet()) {
            if (query.contains(keyword)) {
                String source = keywordSources.get(keyword);
                String response;

                switch (source) {
                    case "ResourceSelector":
                        response = resourceSelector.getDocumentation(keyword);
                        break;
                    case "ElasticSearch":
                        response = elasticSearch.searchDocumentation(keyword);
                        break;
                    default:
                        response = "No documentation found for the provided keyword.";
                }

                if (!response.contains("No documentation found")) {
                    results.add(response);
                }
            }
        }

        if (results.isEmpty()) {
            results.add("No documentation found for the provided query.");
        }

        return results;
    }

    public void setLanguagecode(Languages language) {
        this.languageCode = language;
        resourceSelector = new ResourceSelector(this.languageCode);
        elasticSearch = new ElasticSearch(this.languageCode);
        loadKeywordResponses(); // Reload responses for the new language
    }

    public List<String> getKeys() {
        return new ArrayList<>(keywordSources.keySet());
    }
}