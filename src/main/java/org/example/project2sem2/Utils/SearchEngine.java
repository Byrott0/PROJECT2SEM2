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
//test

    public SearchEngine() {
        javaKeywords = new ArrayList<>();
        pythonKeywords = new ArrayList<>();
        languageKeywords = new ArrayList<>();
        socialPlatformApplicationKeywords = new ArrayList<>();
        financialSystemKeywords = new ArrayList<>();
        domainModelKeywords = new ArrayList<>();

        FileProcessor fileProcessor = new FileProcessor();
        keywordResponses = new HashMap<>();
        keywordResponses.put("java", fileProcessor.loadDataFromFile("src/main/resources/files/java(nl).txt"));
        keywordResponses.put("python", fileProcessor.loadDataFromFile("src/main/resources/files/python(nl).txt"));
        keywordResponses.put("language", fileProcessor.loadDataFromFile("src/main/resources/files/language(nl).txt"));
        keywordResponses.put("social-platform-application", fileProcessor.loadDataFromFile("src/main/resources/files/socialplatformapplication(nl).txt"));
        keywordResponses.put("financial-system", fileProcessor.loadDataFromFile("src/main/resources/files/financialsystem(nl).txt"));
        keywordResponses.put("domain-model", fileProcessor.loadDataFromFile("src/main/resources/files/domainmodel(nl).txt"));
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
            return response != null ? response : "Geen antwoord gevonden voor: " + key;
        } else {
            return "Geen data gevonden voor: " + question;
        }
    }

    public String getResponse(String input) {
        String key = findKey(input);
        if (key != null) {
            return keywordResponses.get(key);
        }
        return findAnswer(input);
    }
}