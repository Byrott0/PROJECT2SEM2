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
    private int chatIndex;
    private List<Chat> chats;
    private List<String> chatList;
    private TextArea textAreaID;
    private SearchEngine searchEngine;

    private FileProcessor fileProcessor = new FileProcessor();

    public Chatbot(ChatBoxController chatBoxController, SearchEngine searchEngine, TextArea textAreaID, TextField textfield) {
        this.chat = new Chat();
        this.chatBoxController = chatBoxController;
        this.searchEngine = searchEngine;
        this.fileProcessor = new FileProcessor();
        this.chats = new ArrayList<>();
        this.textAreaID = textAreaID;
        this.textfield = textfield;
    }

    public void checkText() {
        if (!chats.isEmpty()) {
            Chat chat = chats.get(chatIndex);
            if (textfield.getPromptText().equals("Stel uw vraag.") || textfield.getPromptText().equals("Ask a question")) {
                chat.setTitle(textfield.getText());
                chatList.set(chatIndex, chat.toString());
            }
            String userQuestion = textfield.getText();
            changeTextField("\nQ: ", userQuestion); // Gebruik changeTextField om de vraag toe te voegen aan de TextArea
            String botAnswer = searchEngine.findAnswer(userQuestion);
            changeTextField("\nA: ", botAnswer); // Gebruik changeTextField om het antwoord toe te voegen aan de TextArea
            textfield.setText("");
            chat.setHistory(textAreaID.getText());
        }
    }

    private void changeTextField(String prefix, String text) {
        textAreaID.appendText(prefix + " response " + text + "\n");
    }
}