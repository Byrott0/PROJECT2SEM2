package org.example.project2sem2;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUpController {

    public Button naarSignUp;
    private String Username;
    private String Email;
    private String Password;

    private Stage stage;
    private Scene scene;
    private FXMLLoader root;

    public SignUpController() {
    }

    public SignUpController(String Username, String Email, String Password) {
        this.Username = Username;
        this.Email = Email;
        this.Password = Password;
    }

    public void accountgemaaktNaarLogin(ActionEvent event) throws IOException {
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

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public void setEmail(String email) {
        Email = email;

    }

    public void setPassword(String password) {
        Password = password;
    }
}