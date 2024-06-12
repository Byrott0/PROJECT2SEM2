package org.example.project2sem2.Controller;

import javafx.event.ActionEvent;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import org.example.project2sem2.Utils.Chat;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.project2sem2.Utils.LoggedInUser;


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

    ArrayList<Chat> chats = new ArrayList<>();
    private ObservableList<String> chatList;
    private int chatIndex = 0;

    public void initialize() {
        updateUI();

        textfield.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                checkText();
                event.consume();
            }
        });

        chatList = listviewID.getItems();
        chatList.add("New Conversation");
        Chat chat = new Chat("New Chat " + (chatList.size() - 1));
        chats.add(chat);
        listviewID.getSelectionModel().
                selectedIndexProperty().
                addListener((observableValue, number, t1) -> {
                    chatIndex = observableValue.getValue().intValue();

                    textAreaID.setText(chats.get(chatIndex).getHistory());
                });
    }

    @FXML
    public void setDutch() {


        chatlistID.setText("Chatlijst");
        textAreaID.setPromptText("Hier komt uw gesprek te staan.");
        textfield.setPromptText("Stel uw vraag.");
        instellingenID.setText("Instellingen");
        uitloggenID.setText("Uitloggen");

    }

    @FXML
    public void setEnglish() {

        chatlistID.setText("Chat List");
        textAreaID.setPromptText("Here is your conversation");
        textfield.setPromptText("Ask a question");
        instellingenID.setText("Settings");
        uitloggenID.setText("Logout");

    }

    private void updateUI() {

        chatlistID.setText("Chat List");
        textAreaID.setPromptText("Here is your conversation");
        textfield.setPromptText("Ask a question");
        instellingenID.setText("Settings");
        uitloggenID.setText("Logout");
    }

    public void addChat() {
        chats.get(chatIndex).setHistory(textAreaID.getText());
        textAreaID.setText(null);
        chatList.add("New Chat");
        Chat chat = new Chat("New Chat " + (chatList.size() - 1));
        chats.add(chat);
        chatIndex = chats.size() - 1;
        System.out.println(chatIndex);
    }

    public void checkText() {
        Chat chat = chats.get(chatIndex);
        if (textfield.getPromptText().equals("Stel uw vraag.") || textfield.getPromptText().equals("Ask a question")) {
            chat.setTitle(textfield.getText());
            chatList.set(chatIndex, chat.toString());
        }
        changeTextField("Q:", textfield.getText());
        changeTextField("A:", "Response");
        textfield.setText(null);
        chat.setHistory(textAreaID.getText());
    }


    public void changeTextField(String sender, String text) {
        textAreaID.appendText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss ")) + sender + " " + text + "\n");
        textAreaID.appendText("\n");
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


            Stage currentStage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
            Scene scene = currentStage.getScene();
            scene.setRoot(root);


            LoggedInUser.getInstance().setUser(null);
        } else {

            alert.close();
        }
    }
}