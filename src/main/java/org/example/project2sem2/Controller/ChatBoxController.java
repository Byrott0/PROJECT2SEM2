package org.example.project2sem2.Controller;

import javafx.event.ActionEvent;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import org.example.project2sem2.Model.User;
import org.example.project2sem2.Utils.Chat;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.project2sem2.Utils.Chatbot;
import org.example.project2sem2.Utils.LoggedInUser;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;

public class ChatBoxController {

    @FXML
    private Label NameID;

    @FXML
    private Button chatlistID;

    @FXML
    private MenuButton instellingenID;

    @FXML
    private ListView<String> listviewID;

    @FXML
    private TextArea textAreaID;

    @FXML
    private TextField textfield;

    @FXML
    private MenuItem uitloggenID;

    @FXML
    private Label NameID1;

    private ArrayList<Chat> chats = new ArrayList<>();
    private ObservableList<String> chatList;
    private int chatIndex = 0;

    public void initialize() {
        // Stel de standaardtaal in op Nederlands
        setDutch();

        // Stel een handler in voor het indrukken van de Enter-toets in het tekstveld
        textfield.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                Chatbot.checkText();
                event.consume();
            }
        });

        // Voeg een nieuwe conversatie toe aan de lijst en initialiseer de lijstweergave
        chatList = listviewID.getItems();
        chatList.add("New Conversation");
        Chat chat = new Chat("New Chat " + (chatList.size() - 1));
        chats.add(chat);

        // Stel een listener in voor het wijzigen van de selectie in de lijstweergave
        listviewID.getSelectionModel().selectedIndexProperty().addListener((observableValue, number, t1) -> {
            chatIndex = observableValue.getValue().intValue();
            textAreaID.setText(chats.get(chatIndex).getHistory());
        });
    }

    private void setLoggedInUserText(String prefix) {
        User loggedInUser = LoggedInUser.getInstance().getUser();
        if (loggedInUser != null) {
            NameID1.setText(prefix + loggedInUser.getUsername());
        }
    }

    @FXML
    public void setDutch() {
        chatlistID.setText("Chatlijst");
        textAreaID.setPromptText("Hier komt uw gesprek te staan.");
        textfield.setPromptText("Stel uw vraag.");
        instellingenID.setText("Instellingen");
        uitloggenID.setText("Uitloggen");
        setLoggedInUserText("Ingelogd als: ");
    }

    @FXML
    public void setEnglish() {
        chatlistID.setText("Chat List");
        textAreaID.setPromptText("Here is your conversation");
        textfield.setPromptText("Ask a question");
        instellingenID.setText("Settings");
        uitloggenID.setText("Logout");
        setLoggedInUserText("Logged in as: ");
    }

    public void addChat() {
        chats.get(chatIndex).setHistory(textAreaID.getText());
        textAreaID.setText(null);
        chatList.add("New Chat");
        Chat chat = new Chat("New Chat " + (chatList.size() - 1));
        chats.add(chat);
        chatIndex = chats.size() - 1;
    }

//public void checkText() {
//   Chat chat = chats.get(chatIndex);
//   if (textfield.getPromptText().equals("Stel uw vraag.") || textfield.getPromptText().equals("Ask a question")) {
//       chat.setTitle(textfield.getText());
//       chatList.set(chatIndex, chat.toString());
//   }
//   changeTextField("Q:", textfield.getText());
//   changeTextField("A:", "Response");
//   textfield.setText(null);
//   chat.setHistory(textAreaID.getText());
//
//
//ublic void changeTextField(String sender, String text) {
//   textAreaID.appendText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss ")) + sender + " " + text + "\n");
//   textAreaID.appendText("\n");
//

    @FXML
    public void LogOutfunction(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Conformatie Uitloggen");
        alert.setHeaderText(null);
        alert.setContentText("Weet u zeker dat u wilt uitloggen?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            chats.clear();
            chatList.clear();
            textAreaID.clear();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/project2sem2/login.fxml"));
            Parent root = fxmlLoader.load();

            Stage currentStage = (Stage) instellingenID.getScene().getWindow();
            Scene scene = currentStage.getScene();
            scene.setRoot(root);

            LoggedInUser.getInstance().setUser(null);
        } else {
            alert.close();
        }
    }
}
