package org.example.project2sem2.Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class FileProcessor {

    public String loadDataFromFile(String filePath) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + filePath);
            e.printStackTrace();
        }
        return content.toString().trim();
    }
}





//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        Map<String, String> dataMap = new HashMap<>();
//        loadData("src/java.txt", "java", dataMap);
//        loadData("src/python.txt", "python", dataMap);
//        loadData("src/language.txt", "language", dataMap);
//        loadData("src/socialplatformapplication.txt", "social-platform-application", dataMap);
//        loadData("src/financialsystem.txt", "financial-system", dataMap);
//        loadData("src/domainmodel.txt", "domain-model", dataMap);
//
//        List<String> javaKeywords = List.of("java");
//        List<String> pythonKeywords = List.of("python");
//        List<String> Languageskeywords = List.of("language");
//        List <String> socialPlatformApplicationKeywords = List.of("social-platform-application");
//        List <String> financialSystemKeywords = List.of("financial-system");
//        List <String> domainmodelKeywords = List.of("domain-model");
//
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Stel een vraag: ");
//        while (true) {
//            String question = scanner.nextLine().trim().toLowerCase();
//            if (question.equals("exit")) {
//                break;
//            }
//
//            String key = null;
//            if (containsKeyword(question, javaKeywords)) {
//                key = "java";
//            } else if (containsKeyword(question, pythonKeywords)) {
//                key = "python";
//            }
//            else if (containsKeyword(question, Languageskeywords)) {
//                key = "language";
//            }
//            else if (containsKeyword(question, socialPlatformApplicationKeywords)) {
//                key = "social-platform-application";
//            }
//            else if (containsKeyword(question, financialSystemKeywords)) {
//                key = "financial-system";
//            }
//            else if (containsKeyword(question, domainmodelKeywords)) {
//                key = "domain-model";
//            }
//
//
//            if (key != null) {
//                String value = dataMap.get(key);
//                if (value != null) {
//                    // Replace commas with newlines for the value
//                    value = value.replace("|", "\n");
//                    System.out.println(value);
//                } else {
//                    System.out.println("Geen gegevens gevonden voor de vraag: " + question);
//                }
//            } else {
//                System.out.println("Geen gegevens gevonden voor de vraag: " + question);
//            }
//        }
//        scanner.close();
//    }
//
//    private static void loadData(String filePath, String key, Map<String, String> dataMap) {
//        StringBuilder content = new StringBuilder();
//        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                content.append(line).append(" ");
//            }
//            dataMap.put(key, content.toString().trim());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static boolean containsKeyword(String question, List<String> keywords) {
//        for (String keyword : keywords) {
//            if (question.contains(keyword)) {
//                return true;
//            }
//        }
//        return false;
//    }
//}
