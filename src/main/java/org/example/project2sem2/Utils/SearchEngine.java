package org.example.project2sem2.Utils;

import org.example.project2sem2.Controller.ChatBoxController;

import java.util.*;

public class SearchEngine {
    private Map<String, Map<String, String>> keywordResponses;

    ChatBoxController chatboxcontroller = new ChatBoxController();
    private String languageCode = chatboxcontroller.getLanguage();


    public SearchEngine() {
        keywordResponses = new HashMap<>();
        loadKeywordResponses();
    }

    private void loadKeywordResponses() {
        String[] keys = {"java", "python", "language", "social platform application", "financial system", "domain model"};
        FileProcessor fileProcessor = new FileProcessor();

        for (String key : keys) {
            Map<String, String> responses = new HashMap<>();//madikh notes
            responses.put("nl", fileProcessor.loadDataFromFile("src/main/resources/files/" + key + "(nl).txt"));
            responses.put("en", fileProcessor.loadDataFromFile("src/main/resources/files/" + key + "(en).txt"));
            keywordResponses.put(key, responses);
        }
    }

    public List<String> search(String query) {
        if (query == null || query.trim().isEmpty()) {
            return Collections.emptyList();
        }
        // A simple search implementation that returns a list of matching keys
        List<String> results = new ArrayList<>();
        for (String key : keywordResponses.keySet()) {
            if (key.contains(query.toLowerCase())) {
                results.add(key);
            }
        }
        return results;
    }

    public String findKey(String question) {
        String[] keys = {"java", "python", "language", "social platform application", "financial system", "domain model"};
        for (String key : keys) {
            if (question.contains(key)) {
                return key;
            }
        }
        return null;
    }

    public List<String> getKeys() {
        return Arrays.asList("java", "python", "language", "social platform application", "financial system", "domain model");
    }

    public String findAnswer(String question) {
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

    public String getResponse(String input) {
        String key = findKey(input);
        if (key != null) {
            Map<String, String> languageResponses = keywordResponses.get(key);
            if (languageResponses != null) {
                return languageResponses.get(languageCode);
            }
        }
        return findAnswer(input);
    }

    public void setLanguagecode(String languagecode) {
        this.languageCode = languagecode;
    }

    public String getLanguagecode() {
        return languageCode;
    }
}
