package org.example.project2sem2.Utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;

public class Chat {
    private String name;

    public Chat() {
        this.name = "New Chat";
    }

    public Chat(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
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
}