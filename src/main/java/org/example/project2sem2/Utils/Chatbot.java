package org.example.project2sem2.Utils;

import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import org.example.project2sem2.Controller.ChatBoxController;

import java.util.ArrayList;
import java.util.List;

public class Chatbot implements SetObserver {
    private Chat chat;
    private ChatBoxController chatBoxController;
    private TextField typetextID;
    private List<Chat> chats;
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


    @Override
    public void update(String info) {
        textAreaID.appendText("Settings updated with info: " + info + "\n");
    }
}