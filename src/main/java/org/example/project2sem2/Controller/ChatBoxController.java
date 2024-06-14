package org.example.project2sem2.Controller;

import javafx.event.ActionEvent;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import org.example.project2sem2.Model.User;
import org.example.project2sem2.Utils.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
    private TextField typetextID;

    @FXML
    private Button textfieldEnterID;

    @FXML
    private MenuItem uitloggenID;

    @FXML
    private Label NameID1;

    @FXML
    private TextField newSubjectID;

    @FXML
    private Button changeSubjectButtonID;

    private ArrayList<Chat> chats = new ArrayList<>();
    private ObservableList<String> chatList;

    private String language;

    private int chatIndex = 0;

    private Chatbot chatbot;
    private SearchEngine searchEngine;

    private User loggedInUser;

    public ChatBoxController(User loggedInUser) { // Add this constructor
        this.loggedInUser = loggedInUser;
    }

    public void checkText() {
        if (!chats.isEmpty()) {
            Chat chat = chats.get(chatIndex);
            String userQuestion = typetextID.getText();
            textAreaID.appendText("\nQ: " + userQuestion); // Voeg de vraag toe aan de TextArea
            String botAnswer = searchEngine.findAnswer(userQuestion, language);
            textAreaID.appendText("\nA: " + botAnswer); // Voeg het antwoord toe aan de TextArea
            typetextID.setText("");
            chat.setHistory(textAreaID.getText());


            String chatName = userQuestion;
            chatList.set(chatIndex, chatName);
            chat.setName(chatName);
        }
    }

    public void changeSubject() {
        String newSubject = newSubjectID.getText();
        setChatSubject(newSubject);
        newSubjectID.clear();
    }

    public void setChatSubject(String newSubject) {
        if (!chats.isEmpty()) {
            // Update the chat name in the chat list
            chatList.set(chatIndex, newSubject);
            // Update the chat name in the chats
            chats.get(chatIndex).setName(newSubject);
        }
    }

    public void initialize() {
        setDutch();


        chatList = listviewID.getItems();

        loadChatsForLoggedInUser();



        chatbot = new Chatbot(this, new SearchEngine(), textAreaID, typetextID); // Pass the typetextID to the Chatbot class
        typetextID.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                chatbot.processText();
            }
        });

        this.searchEngine = new SearchEngine();


        List<String> keywords = searchEngine.getKeys();


        typetextID.textProperty().addListener((observable, oldValue, newValue) -> {
            for (String keyword : keywords) {
                if (newValue.contains(keyword)) {
                    typetextID.setStyle("-fx-text-fill: green;");
                    return;
                }
            }
            typetextID.setStyle("-fx-text-fill: black;");
        });

        listviewID.getSelectionModel().selectedIndexProperty().addListener((observableValue, number, t1) -> {
            chatIndex = observableValue.getValue().intValue();
            if (chatIndex >= 0 && chatIndex < chats.size()) {
                textAreaID.setText(chats.get(chatIndex).getHistory());
            }
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
        newSubjectID.setPromptText("Nieuw onderwerp");
        language = "nl";
    }

    @FXML
    public void setEnglish() {
        chatlistID.setText("Chat List");
        textAreaID.setPromptText("Here is your conversation");
        typetextID.setPromptText("Ask a question");
        instellingenID.setText("Settings");
        uitloggenID.setText("Logout");
        setLoggedInUserText("Logged in as: ");
        newSubjectID.setPromptText("New subject");
        language = "en";
    }

    public void addChat() {
        if (!chats.isEmpty()) {
            chats.get(chatIndex).setHistory(textAreaID.getText());
        }
        textAreaID.setText(null);
        String chatName = typetextID.getText(); // Use the text from the TextField directly
        chatList.add(chatName);
        Chat chat = new Chat(chatName, chatName + " " + (chatList.size() - 1));
        chat.setName(chatName); // Set the name of the chat
        chats.add(chat);
        chatIndex = chats.size() - 1;
        newSubjectID.setText(chatName);
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
        if (result.isPresent() && result.get() == ButtonType.OK) {
            for (Chat chat : chats) {
                if (!chat.isLoadedFromDB()) {
                    Database.insertChatMessage(chat);
                }
            }
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


    public void loadChatsForLoggedInUser() {
        String username = LoggedInUser.getInstance().getUser().getUsername();

        // Clear the existing chats and chat list
        chats.clear();
        chatList.clear();

        // Load the user's chats
        List<Chat> userChats = Database.selectAllChatMessages(username); // Pass the username
        chats.addAll(userChats);

        // Update the ListView with the user's chats
        for (Chat chat : userChats) {
            if (!chat.getName().isEmpty()) {
                chatList.add(chat.getName());
            }
        }
    }
}