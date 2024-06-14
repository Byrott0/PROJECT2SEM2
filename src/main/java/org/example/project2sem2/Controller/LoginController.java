package org.example.project2sem2.Controller;

import org.example.project2sem2.Model.User;
import org.example.project2sem2.Utils.Database;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.project2sem2.Utils.LoggedInUser;
import org.example.project2sem2.Utils.SceneSwitcher;

public class LoginController {

    @FXML
    private Button loginButton;

    @FXML
    private Button naarSignUp;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    void handleLoginButtonAction(ActionEvent event) {
        String password = passwordField.getText();
        String username = usernameField.getText();

        Database.login(username, password);

        // If login is successful, create the singleton user instance
        if (LoggedInUser.getInstance() != null) {
            SceneSwitcher.switchScene(event, "chat-box.fxml");
        } else {
            // Handle unsuccessful login, display an error message, etc.
        }
    }


    @FXML
    void switchToSignup(ActionEvent event) {
        SceneSwitcher.switchScene(event, "sign-up.fxml");
    }

}
