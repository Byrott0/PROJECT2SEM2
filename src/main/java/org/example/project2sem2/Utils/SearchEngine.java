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
        String[] keys = {"java", "python", "language", "social platform application", "financial system", "domain model"};
        FileProcessor fileProcessor = new FileProcessor();

        for (String key : keys) {
            Map<String, String> responses = new HashMap<>();
            responses.put(languageCode.toString(), fileProcessor.loadDataFromFile("src/main/resources/files/" + languageCode + "/"+ key + ".txt"));

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
        if (question == null || question.trim().isEmpty()) {
            return "No data found";
        }

        return findAnswerByKeyAndLanguage(question, languageCode);
    }

    public String findAnswerByKeyAndLanguage(String key, Languages languageCode) {
        if (!keywordResponses.containsKey(key)) {
            return "";
        }
        String filepath = "src/main/resources/files/" + languageCode + "/" + key + ".txt";
        return new FileProcessor().loadDataFromFile(filepath);
    }

    // Haal als eerst alle references hierin weg en daarna de methodes, dit word niet meer gebruikt. Een map is dan ook niet meer nodig
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
