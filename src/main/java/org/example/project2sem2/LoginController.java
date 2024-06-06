package org.example.project2sem2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    public Button naarSignUp;
    private String Username;
    private String Email;
    private String Password;

    private Stage stage;
    private Scene scene;
    private FXMLLoader root;

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;


    public LoginController() {
    }

    public LoginController(String Username, String Email, String Password) {
        this.Username = Username;
        this.Email = Email;
        this.Password = Password;
    }

    public void switchToSignup(ActionEvent event) throws IOException {
        Parent otherScreenParent = FXMLLoader.load(getClass().getResource("Sign-up.fxml"));
        Scene otherScreenScene = new Scene(otherScreenParent);

        // Hier krijgen we de stage informatie
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(otherScreenScene);
        window.show();
    }

    public void initialize() {
        loginButton.setOnAction(this::handleLoginButtonAction);
    }

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (isValidCredentials(username, password)) {
            LoginNaarChatbox(event);
        } else {
            // Toon een foutmelding aan de gebruiker
        }
    }

    private boolean isValidCredentials(String username, String password) {
        // Voer hier de logica uit om de gebruikersnaam en het wachtwoord te controleren
        // Dit is een dummy implementatie
        return "admin".equals(username) && "password".equals(password);
    }

    private void LoginNaarChatbox(ActionEvent event) {
        try {
            Parent chatBoxParent = FXMLLoader.load(getClass().getResource("ChatBox.fxml"));
            Scene chatBoxScene = new Scene(chatBoxParent);

            // Hier krijgen we de stage informatie
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(chatBoxScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}