package org.example.project2sem2.Utils;



import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public class SearchEngine {
    private Map<List<String>, String> dataMap;
    private Map<String, List<String>> keywordToKeyMap;

    public SearchEngine(Map<List<String>, String> dataMap, Map<String, List<String>> keywordToKeyMap) {
        this.dataMap = dataMap;
        this.keywordToKeyMap = keywordToKeyMap;
    }

    public String findAnswer(String question) {
        for (Map.Entry<List<String>, String> entry : dataMap.entrySet()) {
            if (containsKeywords(question.toLowerCase(), entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }

    public void suggestKeywords(String question) {
        Set<String> suggestions = new HashSet<>();
        for (String key : keywordToKeyMap.keySet()) {
            if (question.contains(key)) {
                suggestions.addAll(keywordToKeyMap.get(key));
            }
        }
        if (!suggestions.isEmpty()) {
            System.out.println("Bedoelde u iets over: " + String.join(", ", suggestions) + "?");
        } else {
            System.out.println("Geen suggesties gevonden.");
        }
    }

    private boolean containsKeywords(String question, List<String> keywords) {
        return keywords.stream().anyMatch(question::contains);
    }
}
