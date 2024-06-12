package org.example.project2sem2.Utils;

import java.util.List;

public class SearchEngine {
    private List<String> javaKeywords;
    private List<String> pythonKeywords;
    private List<String> languageKeywords;
    private List<String> socialPlatformApplicationKeywords;
    private List<String> financialSystemKeywords;
    private List<String> domainModelKeywords;

    public String findKey(String question) {
        String key = null;
        if (containsKeyword(question, javaKeywords)) {
            key = "java";
        } else if (containsKeyword(question, pythonKeywords)) {
            key = "python";
        } else if (containsKeyword(question, languageKeywords)) {
            key = "language";
        } else if (containsKeyword(question, socialPlatformApplicationKeywords)) {
            key = "social-platform-application";
        } else if (containsKeyword(question, financialSystemKeywords)) {
            key = "financial-system";
        } else if (containsKeyword(question, domainModelKeywords)) {
            key = "domain-model";
        }
        return key;
    }

    private boolean containsKeyword(String question, List<String> keywords) {
        // Implement the method...
        return true;
    }
}