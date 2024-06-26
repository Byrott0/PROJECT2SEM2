package org.example.project2sem2.Utils;

import java.util.*;

public class ElasticSearch {
    private Map<String, String> searchResponses;
    private FileProcessor fileProcessor;

    private Languages language;

    public ElasticSearch(Languages language) {
        this.language = language;
        searchResponses = new HashMap<>();
        fileProcessor = new FileProcessor();
        loadSearchResponses();
    }

    private void loadSearchResponses() {
        // Load your predefined responses here
        searchResponses.put(Keywords.SOCIAL_PLATFORM_APPLICATION.getKey(), fileProcessor.loadDataFromFile("src/main/resources/files/" + language + "/" + Keywords.SOCIAL_PLATFORM_APPLICATION.getKey() + ".txt"));
        searchResponses.put(Keywords.FINANCIAL_SYSTEM.getKey(), fileProcessor.loadDataFromFile("src/main/resources/files/" + language + "/" + Keywords.FINANCIAL_SYSTEM.getKey() + ".txt"));
        searchResponses.put(Keywords.DOMAIN_MODEL.getKey(), fileProcessor.loadDataFromFile("src/main/resources/files/" + language + "/" + Keywords.DOMAIN_MODEL.getKey() + ".txt"));
    }

    public String searchDocumentation(String keyword) {
        return searchResponses.getOrDefault(keyword, "No documentation found for the provided keyword.");
    }
}
