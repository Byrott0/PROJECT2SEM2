package org.example.project2sem2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUpController {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    public Button naarSignUp;

    private DataBaseSignUp dataBaseSignUp = new DataBaseSignUp();

    private Stage stage;
    private Scene scene;
    private FXMLLoader root;

    public SignUpController() {
    }

    public void accountgemaaktNaarLogin(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        dataBaseSignUp.insertIntoCredentials(username, password, email);

        Parent otherScreenParent = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene otherScreenScene = new Scene(otherScreenParent);

        // Hier krijgen we de stage informatie
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(otherScreenScene);
        window.show();
    }

    public void AlEenAccountGehad(ActionEvent event) throws IOException {
        Parent otherScreenParent = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene otherScreenScene = new Scene(otherScreenParent);

        // Hier krijgen we de stage informatie
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(otherScreenScene);
        window.show();
    }
}