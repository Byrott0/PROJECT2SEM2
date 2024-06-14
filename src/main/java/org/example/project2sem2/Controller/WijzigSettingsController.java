package org.example.project2sem2.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.example.project2sem2.Model.Settings;
import org.example.project2sem2.Model.User;
import org.example.project2sem2.Utils.Database;
import org.example.project2sem2.Utils.LoggedInUser;

import java.io.IOException;
import java.util.Optional;

public class WijzigSettingsController {

    @FXML
    private TextField currentEmailField;

    @FXML
    private TextField currentUsernameField;

    @FXML
    private TextField currentPasswordField;

    @FXML
    private TextField newEmailField;

    @FXML
    private TextField newUsernameField;

    @FXML
    private TextField newPasswordField;

    @FXML
    private Button saveButton;

    @FXML
    private Button backButton;


    private org.example.project2sem2.Model.Settings Settings;

    @FXML
    public void initialize() {
        User loggedInUser = LoggedInUser.getInstance().getUser();
        if (loggedInUser != null) {
            currentEmailField.setText(loggedInUser.getEmail());
            currentUsernameField.setText(loggedInUser.getUsername());
            currentPasswordField.setText(loggedInUser.getPassword());
        }
    }

    @FXML
    private void handleSaveButton(MouseEvent event) {
        String newEmail = newEmailField.getText();
        String newUsername = newUsernameField.getText();
        String newPassword = newPasswordField.getText();

        User loggedInUser = LoggedInUser.getInstance().getUser();
        if (loggedInUser != null) {
            String oldUsername = loggedInUser.getUsername();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Are you sure you want to save changes?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                if (!newEmail.isEmpty()) {
                    loggedInUser.setEmail(newEmail);
                    Settings.Wijzig();
                }
                if (!newUsername.isEmpty()) {
                    // Check if the new username already exists in the database
                    if (Database.getUser(newUsername) != null) {
                        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                        errorAlert.setTitle("Aanmeld Fout");
                        errorAlert.setHeaderText(null);
                        errorAlert.setContentText("Gebruikersnaam bestaat al, kies een andere");
                        errorAlert.showAndWait();
                        return; // Exit the method early
                    } else {
                        loggedInUser.setUsername(newUsername);
                        Settings.Wijzig();
                    }
                }
                if (!newPassword.isEmpty()) {
                    loggedInUser.setPassword(newPassword);
                    Settings.Wijzig();
                }

                if (Database.updateUser(loggedInUser, oldUsername)) {
                    currentEmailField.setText(loggedInUser.getEmail());
                    currentUsernameField.setText(loggedInUser.getUsername());
                    currentPasswordField.setText(loggedInUser.getPassword());
                }
            } else {

            }
        }
    }

    @FXML
    private void handleBackButton(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/project2sem2/chat-box.fxml"));
            loader.setControllerFactory(new Callback<Class<?>, Object>() {
                @Override
                public Object call(Class<?> param) {
                    if (param == ChatBoxController.class) {
                        User loggedInUser = LoggedInUser.getInstance().getUser();
                        return new ChatBoxController(loggedInUser);
                    }
                    return null;
                }
            });
            Parent chatbox = loader.load();
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(new Scene(chatbox));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}