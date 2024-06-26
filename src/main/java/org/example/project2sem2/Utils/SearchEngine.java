package org.example.project2sem2.Utils;

import org.example.project2sem2.Controller.ChatBoxController;

import java.util.*;

public class SearchEngine {
    private Map<String, Map<String, String>> keywordResponses;

    ChatBoxController chatboxcontroller = new ChatBoxController();
    private Languages languageCode;

    public SearchEngine() {
        keywordResponses = new HashMap<>();
        loadKeywordResponses();
    }

    private void loadKeywordResponses() {
        this.languageCode = chatboxcontroller.getLanguage();
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
        for (String key : keywordResponses.keySet()) {
            if (key.contains(query.toLowerCase())) {
                results.add(key);
            }
        }
        return results;
    }

    public String findKey(String question) {
        for (Keywords keyword : Keywords.values()) {
            if (question.contains(keyword.getKey())) {
                return keyword.getKey();
            }
        }
        return null;
    }

    public List<String> getKeys() {
        List<String> keys = new ArrayList<>();
        for (Keywords keyword : Keywords.values()) {
            keys.add(keyword.getKey());
        }
        return keys;
    }

    public String findAnswer(String question) {
        if (question == null || question.trim().isEmpty()) {
            return "No data found for the question: " + question;
        }

        return findAnswerByKeyAndLanguage(findKey(question), languageCode);
    }

    public String findAnswerByKeyAndLanguage(String key, Languages languageCode) {
        if (!keywordResponses.containsKey(key)) {
            return "";
        }
        String filepath = "src/main/resources/files/" + languageCode + "/" + key + ".txt";
        return new FileProcessor().loadDataFromFile(filepath);
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

    public void setLanguagecode(Languages languagecode) {
        this.languageCode = languagecode;
    }
}
