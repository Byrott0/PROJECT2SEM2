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
            String vraag = scanner.nextLine().trim();
            if (vraag.equalsIgnoreCase("exit")) {
                System.out.println("Exiting...");
                break;
            }

            chat.BeantwoordVraag(vraag);
        }

        scanner.close();
    }
}
