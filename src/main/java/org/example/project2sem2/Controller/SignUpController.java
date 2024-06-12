package org.example.project2sem2.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.project2sem2.Model.User;
import org.example.project2sem2.Utils.Database;

import java.io.IOException;

public class SignUpController {

    @FXML
    private Button IsAlGebruiker;

    @FXML
    private Button MeldAanKnop;

    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    void AlEenAccountGehad(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/org/example/project2sem2/login.fxml"));
            Stage stage = (Stage) IsAlGebruiker.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void accountgemaaktNaarLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String email = emailField.getText();

        User user = new User(username, password, email);
        boolean isSignedUp = Database.signup(user);

        if (isSignedUp) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/org/example/project2sem2/login.fxml"));
                Stage stage = (Stage) MeldAanKnop.getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Sign Up Error");
            alert.setHeaderText(null);
            alert.setContentText("Could not sign up. Please try again.");
            alert.showAndWait();
        }
    }
}