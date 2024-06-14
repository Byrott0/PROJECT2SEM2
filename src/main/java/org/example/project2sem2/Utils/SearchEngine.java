package org.example.project2sem2.Utils;

import java.util.*;

public class SearchEngine {
    private Map<String, Map<String, String>> keywordResponses;

    public SearchEngine() {
        keywordResponses = new HashMap<>();
        loadKeywordResponses();
    }

    private void loadKeywordResponses() {
        String[] keys = {"java", "python", "language", "social platform application", "financial system", "domain model"};
        FileProcessor fileProcessor = new FileProcessor();

        for (String key : keys) {
            Map<String, String> responses = new HashMap<>();
            responses.put("nl", fileProcessor.loadDataFromFile("src/main/resources/files/" + key + "(nl).txt"));
            responses.put("en", fileProcessor.loadDataFromFile("src/main/resources/files/" + key + "(en).txt"));
            keywordResponses.put(key, responses);
        }
    }

    public String findKey(String question) {
        String[] keys = {"java", "python", "language", "social-platform-application", "financial-system", "domain-model"};
        for (String key : keys) {
            if (question.contains(key)) {
                return key;
            }
        }
        return null;
    }

    public List<String> getKeys() {
        return Arrays.asList("java", "python", "language", "social-platform-application", "financial-system", "domain-model");
    }

    public String findAnswer(String question, String languageCode) {
        String key = findKey(question);
        if (key != null) {
            Map<String, String> languageResponses = keywordResponses.get(key);
            if (languageResponses != null) {
                String response = languageResponses.get(languageCode);
                return response != null ? response : "No answer found for: " + key;
            } else {
                return "No data found for: " + key;
            }
        } else {
            return "No data found for: " + question;
        }
    }

    public String getResponse(String input, String languageCode) {
        String key = findKey(input);
        if (key != null) {
            Map<String, String> languageResponses = keywordResponses.get(key);
            if (languageResponses != null) {
                return languageResponses.get(languageCode);
            }
        }
        return findAnswer(input, languageCode);
    }
}
