package org.example.project2sem2.Utils;

import javafx.event.ActionEvent;
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

    public Chat(String title) {
        this.title = title;
        this.history = "";
    }

    public Chat() {
        
    }

    public String getHistory() {
        return history;
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

    @FXML
    public void addChat(ActionEvent actionEvent) {
        // ...
    }

    @FXML
    public void openFileChooser(ActionEvent actionEvent) {
        // ...
    }

    @FXML
    public void checkText(ActionEvent actionEvent) {
        // ...
    }

    @FXML
    public void goToAccountBeheerScherm(ActionEvent actionEvent) {
        // ...
    }

    @FXML
    public void goToAdminPage(ActionEvent actionEvent) {
        // ...
    }

    @FXML
    public void switchToLoginPage(ActionEvent actionEvent) {
        // ...
    }

    public void answerQuestion(String question) {
    }
}