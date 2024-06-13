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
import org.example.project2sem2.Utils.SearchEngine;
import org.example.project2sem2.Utils.LoggedInUser;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;

public class ChatBoxController {

    private User user;

    public ChatBoxController(User user) {
        this.user = user;
    }

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
    private TextField typetextID;

    @FXML
    private Button textfieldEnterID;

    @FXML
    private MenuItem uitloggenID;

    @FXML
    private Label NameID1;

    private ArrayList<Chat> chats = new ArrayList<>();
    private ObservableList<String> chatList;
    private int chatIndex = 0;

    private Chatbot chatbot;
    private SearchEngine searchEngine;

    public void checkText() {
        if (!chats.isEmpty()) {
            Chat chat = chats.get(chatIndex);
            String userQuestion = typetextID.getText();
            textAreaID.appendText("\nQ: " + userQuestion); // Voeg de vraag toe aan de TextArea
            String botAnswer = searchEngine.findAnswer(userQuestion);
            textAreaID.appendText("\nA: " + botAnswer); // Voeg het antwoord toe aan de TextArea
            typetextID.setText("");
            chat.setHistory(textAreaID.getText());
        }
    }


    public void initialize() {
        // Stel de standaardtaal in op Nederlands
        setDutch();

        chatbot = new Chatbot(this, new SearchEngine(), textAreaID, typetextID); // Geef de typetextID door aan de Chatbot klasse
        typetextID.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                chatbot.processText();
                event.consume();
            }
        });

        this.searchEngine = new SearchEngine();

        chatbot = new Chatbot(this, new SearchEngine(), textAreaID, typetextID); // Geef de typetextID door aan de Chatbot klasse
        typetextID.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                checkText();
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
        typetextID.setPromptText("Stel uw vraag.");
        instellingenID.setText("Instellingen");
        uitloggenID.setText("Uitloggen");
        setLoggedInUserText("Ingelogd als: ");
    }

    @FXML
    public void setEnglish() {
        chatlistID.setText("Chat List");
        textAreaID.setPromptText("Here is your conversation");
        typetextID.setPromptText("Ask a question");
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

    @FXML
    void switchToWijzigSettings(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/org/example/project2sem2/wijzig-settings.fxml"));
            Stage stage = (Stage) instellingenID.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


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
