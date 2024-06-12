package org.example.project2sem2.Utils;

import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import org.example.project2sem2.Controller.ChatBoxController;

import java.util.List;
import java.util.Scanner;

public class Chatbot {
    private Chat chat;
    private ChatBoxController chatBoxController;
    private TextField textfield;
    private int chatIndex;
    private List<Chat> chats;
    private List<String> chatList;
    private TextArea textAreaID;
    private SearchEngine searchEngine; // Voeg deze regel toe

    private FileProcessor fileProcessor = new FileProcessor(); // Voeg deze regel toe

    public Chatbot(ChatBoxController chatBoxController, SearchEngine searchEngine) { // Voeg SearchEngine als parameter toe
        this.chat = new Chat();
        this.chatBoxController = chatBoxController;
        this.searchEngine = searchEngine; // Initialiseer de SearchEngine
        this.fileProcessor = new FileProcessor(); // Voeg deze regel toe
    }

    // ...

    public void checkText() {
        Chat chat = chats.get(chatIndex);
        if (textfield.getPromptText().equals("Stel uw vraag.") || textfield.getPromptText().equals("Ask a question")) {
            chat.setTitle(textfield.getText());
            chatList.set(chatIndex, chat.toString());
        }
        String userQuestion = textfield.getText();
        String botAnswer = searchEngine.findAnswer(userQuestion); // Gebruik de SearchEngine om het antwoord te vinden
        changeTextField("Q:", userQuestion);
        changeTextField("A:", botAnswer);
        textfield.setText(null);
        chat.setHistory(textAreaID.getText());
    }


}