package org.example.project2sem2.Utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;

public class SceneSwitcher {

    public static void switchScene(ActionEvent event, String fxmlFile) {
        try {
            // Adjusted path to reflect the resources directory
            Parent newSceneParent = FXMLLoader.load(SceneSwitcher.class.getResource("/org/example/project2sem2/" + fxmlFile));
            Scene newScene = new Scene(newSceneParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(newScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

