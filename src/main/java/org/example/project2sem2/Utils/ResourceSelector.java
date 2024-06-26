package org.example.project2sem2.Utils;

import java.util.*;

public class ResourceSelector {
    private Map<String, String> documentationResponses;
    private FileProcessor fileProcessor;
    private Languages language;

    public ResourceSelector(Languages language) {
        this.language = language;
        documentationResponses = new HashMap<>();
        fileProcessor = new FileProcessor();
        loadDocumentationResponses();
    }

    private void loadDocumentationResponses() {
        documentationResponses.put(Keywords.JAVA.getKey(), fileProcessor.loadDataFromFile("src/main/resources/files/" + language + "/" + Keywords.JAVA.getKey() + ".txt"));
        documentationResponses.put(Keywords.PYTHON.getKey(),fileProcessor.loadDataFromFile("src/main/resources/files/" + language + "/" + Keywords.PYTHON.getKey() + ".txt"));
        documentationResponses.put(Keywords.LANGUAGE.getKey(), fileProcessor.loadDataFromFile("src/main/resources/files/" + language + "/" + Keywords.LANGUAGE.getKey() + ".txt"));
    }

    public String getDocumentation(String keyword) {
        return documentationResponses.getOrDefault(keyword, "No documentation found for the provided keyword.");
    }
}