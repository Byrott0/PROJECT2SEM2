package org.example.project2sem2.Utils;

public enum Keywords {
    JAVA("java"),
    PYTHON("python"),
    LANGUAGE("language"),
    SOCIAL_PLATFORM_APPLICATION("social platform application"),
    FINANCIAL_SYSTEM("financial system"),
    DOMAIN_MODEL("domain model");

    private final String key;

    Keywords(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}