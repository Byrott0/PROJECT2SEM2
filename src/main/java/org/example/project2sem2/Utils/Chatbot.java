package org.example.project2sem2.Utils;

import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import org.example.project2sem2.Controller.ChatBoxController;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

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
        this.textAreaID = textAreaID;
        this.typetextID = typetextID;
    }


    public void processText() {
        checkText();
    }


    private void checkText() {
        if (!chats.isEmpty()) {
            Chat chat = chats.get(chatIndex);
            String userQuestion = typetextID.getText();
            textAreaID.appendText("\nQ: " + userQuestion); // Voeg de vraag toe aan de TextArea
            String botAnswer = searchEngine.getResponse(userQuestion); // Gebruik de getResponse methode
            String[] words = botAnswer.split(" "); // Split the response into words

            SequentialTransition st = new SequentialTransition();
            for (String word : words) {
                PauseTransition pause = new PauseTransition(Duration.millis(150));
                pause.setOnFinished(e -> textAreaID.appendText(" " + word)); // Print each word after the pause
                st.getChildren().add(pause);
            }
            st.play();

            typetextID.setText("");
            chat.setHistory(textAreaID.getText());
        }
    }
}