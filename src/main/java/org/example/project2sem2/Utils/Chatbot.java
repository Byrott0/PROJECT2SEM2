package org.example.project2sem2.Utils;
import java.util.Scanner;

public class Chatbot {
    private Chat chat;

    public Chatbot() {
        chat = new Chat();
    }

    public void startChat() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Stel een vraag (type 'exit' om te stoppen):");

        while (true) {
            String question = scanner.nextLine().trim();
            if (question.equalsIgnoreCase("exit")) {
                System.out.println("Exiting...");
                break;
            }

            chat.answerQuestion(question);
        }

        scanner.close();
    }
}
