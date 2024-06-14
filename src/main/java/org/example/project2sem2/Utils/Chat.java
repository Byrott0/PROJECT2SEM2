package org.example.project2sem2.Utils;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;

public class Chat {
    @FXML
    private Button chatlistID;
    @FXML
    private Button imageFileChooserID;
    @FXML
    private Button textfieldEnterID;

    @FXML
    private MenuItem accountBeheerID;
    @FXML
    private MenuItem adminID;
    @FXML
    private MenuItem uitloggenID;

    private String title;
    private String history;
    private String name;

    public Chat(String title, String name) {
        this.title = title;
        this.name = name;
        this.history = "";
    }

    public Chat() {
    }

    public String getHistory() {
        return history;
    }

    public String setName(String name){
        return name;
    }
    public String getName() {
        return this.name;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public String toString() {
        return title;
    }

}