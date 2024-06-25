package org.example.project2sem2.Controller;

import javafx.scene.control.Alert;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.project2sem2.Utils.DbUserQueries;
import org.example.project2sem2.Utils.SceneSwitcher;

public class LoginController {

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    void handleLoginButtonAction(ActionEvent event) {
        String password = passwordField.getText();
        String username = usernameField.getText();

        boolean loginSuccessful = DbUserQueries.login(username, password);

        if (loginSuccessful) {
            SceneSwitcher.switchScene(event, "chat-box.fxml");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Fout");
            alert.setHeaderText(null);
            alert.setContentText("kon niet inloggen, probeer opnieuw");
            alert.showAndWait();
        }
    }

    @FXML
    void switchToSignup(ActionEvent event) {
        SceneSwitcher.switchScene(event, "sign-up.fxml");
    }
}
