package org.example.project2sem2.Utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.util.Callback;
import org.example.project2sem2.Controller.ChatBoxController;
import org.example.project2sem2.Model.User;

import java.io.IOException;

public class SceneSwitcher {

    public static void switchScene(ActionEvent event, String fxmlFile) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(SceneSwitcher.class.getResource("/org/example/project2sem2/" + fxmlFile));
            fxmlLoader.setControllerFactory(new Callback<Class<?>, Object>() {
                @Override
                public Object call(Class<?> param) {
                    if (param == ChatBoxController.class) {
                        User loggedInUser = LoggedInUser.getInstance().getUser();
                        return new ChatBoxController(loggedInUser);
                    }
                    try {
                        return param.getDeclaredConstructor().newInstance();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            Parent newSceneParent = fxmlLoader.load();
            Scene newScene = new Scene(newSceneParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(newScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //
}