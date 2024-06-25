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
import org.example.project2sem2.Utils.DbUserQueries;

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

        if (DbUserQueries.getUser(username) != null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Aanmeld Fout");
            alert.setHeaderText(null);
            alert.setContentText("Gebruikersnaam bestaat al, kies een andere");
            alert.showAndWait();
        } else {
            User user = new User(username, password, email);
            boolean isSignedUp = DbUserQueries.signup(user);

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
                alert.setTitle("Aanmeld Fout");
                alert.setHeaderText(null);
                alert.setContentText("Kon niet aanmelden, probeer opnieuw");
                alert.showAndWait();
            }
        }
    }
}