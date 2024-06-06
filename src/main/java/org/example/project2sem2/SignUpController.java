package org.example.project2sem2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    private Stage stage;
    private Scene scene;
    private FXMLLoader root;

    @FXML
    public void onMeldAanButtonClick(ActionEvent actionEvent) {
        // Voer hier de actie uit die je wilt doen wanneer de "Meld aan" knop wordt ingedrukt
    }

    @FXML
    public void onIkBenAlEenGebruikerButtonClick(ActionEvent actionEvent) {
        // Voer hier de actie uit die je wilt doen wanneer de "Ik ben al een gebruiker" knop wordt ingedrukt
    }

    public void SwitchToLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("LoginController.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    stage.show();
    }
}