package org.example.project2sem2.Utils;

import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import org.example.project2sem2.Controller.ChatBoxController;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Chatbot {
    private Chat chat;
    private ChatBoxController chatBoxController;
    private TextField textfield;
    private TextField typetextID;
    private int chatIndex;
    private List<Chat> chats;
    private List<String> chatList;
    private TextArea textAreaID;
    private SearchEngine searchEngine;

    private FileProcessor fileProcessor = new FileProcessor();

    public Chatbot(ChatBoxController chatBoxController, SearchEngine searchEngine, TextArea textAreaID, TextField typetextID) {
        this.chat = new Chat();
        this.chatBoxController = chatBoxController;
        this.searchEngine = searchEngine;
        this.fileProcessor = new FileProcessor();
        this.chats = new ArrayList<>();
        this.textAreaID = textAreaID; // Initialiseer de textAreaID
        this.typetextID = typetextID; // Initialiseer de typetextID
    }


    public void processText() {
        checkText();
    }

    private void checkText() {
        if (!chats.isEmpty()) {
            Chat chat = chats.get(chatIndex);
            String userQuestion = typetextID.getText();
            textAreaID.appendText("\nQ: " + userQuestion); // Voeg de vraag toe aan de TextArea
            String botAnswer = searchEngine.findAnswer(userQuestion);
            textAreaID.appendText("\nA: " + botAnswer); // Voeg het antwoord toe aan de TextArea
            typetextID.setText("");
            chat.setHistory(textAreaID.getText());
        }
    }

    private void changeTextField(String prefix, String text) {
        textAreaID.appendText(prefix + " response " + text + "\n");
    }
}