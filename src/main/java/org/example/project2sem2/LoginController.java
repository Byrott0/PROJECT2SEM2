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

public class LoginController {


    @FXML
    private Button LoginButton;

    private String Username;
    private String Email;
    private String Password;

    private Stage stage;
    private Scene scene;
    private FXMLLoader root;



    public LoginController(String Username, String Email, String Password) {
        this.Username = Username;
        this.Email = Email;
        this.Password = Password;
    }


    public void SwitchToSignUp(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Sign-up.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void SwitchToChatbox(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ChatBox.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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

    public boolean testlogin() {
        if (Username.equals("test") && (Password.equals("test"))) {
            return true;
        }
        return false;
    }

    @FXML
    public void onLoginButtonClick(ActionEvent event) throws IOException {
        if (testlogin()) {
            SwitchToChatbox(event);
        }
    }
}