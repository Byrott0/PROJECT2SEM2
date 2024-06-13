package org.example.project2sem2.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchEngine {
    private List<String> javaKeywords;
    private List<String> pythonKeywords;
    private List<String> languageKeywords;
    private List<String> socialPlatformApplicationKeywords;
    private List<String> financialSystemKeywords;
    private List<String> domainModelKeywords;
    private Map<String, String> keywordResponses;


    public SearchEngine() {
        // Initialiseer de lijsten...
        javaKeywords = new ArrayList<>();
        pythonKeywords = new ArrayList<>();
        languageKeywords = new ArrayList<>();
        socialPlatformApplicationKeywords = new ArrayList<>();
        financialSystemKeywords = new ArrayList<>();
        domainModelKeywords = new ArrayList<>();

        FileProcessor fileProcessor = new FileProcessor();
        keywordResponses = new HashMap<>();
        keywordResponses.put("java", fileProcessor.loadDataFromFile("src/main/resources/files/java.txt"));
        keywordResponses.put("python", fileProcessor.loadDataFromFile("src/main/resources/files/python.txt"));
        keywordResponses.put("language", fileProcessor.loadDataFromFile("src/main/resources/files/language.txt"));
        keywordResponses.put("social-platform-application", fileProcessor.loadDataFromFile("src/main/resources/files/socialplatformapplication.txt"));
        keywordResponses.put("financial-system", fileProcessor.loadDataFromFile("src/main/resources/files/financialsystem.txt"));
        keywordResponses.put("domain-model", fileProcessor.loadDataFromFile("src/main/resources/files/domainmodel.txt"));
    }

    public String findKey(String question) {
        String[] keys = {"java","python","language","social-platform-application","financial-system", "domain-model"};
        for (String key : keys)
            if (question.contains(key)){
                return key;
            }
        return null;
    }

    public String findAnswer(String question) {
        String key = findKey(question);
        if (key != null) {
            String response = keywordResponses.get(key);
            return response != null ? response : "Geen antwoord gevonden voor de sleutel " + key;
        } else {
            return "Geen sleutel gevonden voor de vraag: " + question;
        }
    }

    public String getResponse(String input) {
        return findAnswer(input);
    }
}