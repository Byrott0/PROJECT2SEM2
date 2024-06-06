package org.example.project2sem2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SignUpController {
    @FXML
    private TextField usernameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    @FXML
    public void onMeldAanButtonClick(ActionEvent actionEvent) {
        // Voer hier de actie uit die je wilt doen wanneer de "Meld aan" knop wordt ingedrukt
    }

    @FXML
    public void onIkBenAlEenGebruikerButtonClick(ActionEvent actionEvent) {
        // Voer hier de actie uit die je wilt doen wanneer de "Ik ben al een gebruiker" knop wordt ingedrukt
    }
}