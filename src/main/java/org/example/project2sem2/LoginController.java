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
import java.sql.*;

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
        // Maak verbinding met de database
        String url = "jdbc:sqlite:identifier.sqlite";
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                // Maak een SQL-query om de gebruiker op te zoeken
                String sql = "SELECT * FROM users WHERE name = ? AND password = ?";

                // Voer de query uit
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, username);
                    pstmt.setString(2, password);
                    try (ResultSet rs = pstmt.executeQuery()) {
                        // Als er een resultaat is, dan is de gebruikersnaam en het wachtwoord geldig
                        if (rs.next()) {
                            return true;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // Als we hier komen, dan is de gebruikersnaam en het wachtwoord niet geldig
        return false;
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
//