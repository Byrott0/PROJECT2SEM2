package org.example.project2sem2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Chat {
    @FXML
    private Button chatlistID;
    @FXML
    private Button imageFileChooserID;
    @FXML
    private Button textfieldEnterID;

    private String ChatBot;
    private String ResponsiveMessage;

    public Chat(String ResponsiveMessage, String ChatBot) {
        this.ResponsiveMessage = ResponsiveMessage;
        this.ChatBot = ChatBot;
    }


    public void addChat(ActionEvent actionEvent) {
    }

    public void openFileChooser(ActionEvent actionEvent) {
    }

    public void checkText(ActionEvent actionEvent) {
    }

    public void goToAccountBeheerScherm(ActionEvent actionEvent) {
    }

    public void goToAdminPage(ActionEvent actionEvent) {
    }

    public void switchToLoginPage(ActionEvent actionEvent) {
    }
}