package org.example.project2sem2.Controller;

import javafx.event.ActionEvent;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.KeyCode;
import org.example.project2sem2.Utils.Chat;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ChatBoxController {

    @FXML
    private Label NameID;
    @FXML
    private MenuItem adminID;
    @FXML
    private Button chatlistID;
    @FXML

    private MenuButton instellingenID;
    @FXML
    private ListView<String> listviewID;
    @FXML
    private AnchorPane parentID;
    @FXML
    private TextArea textAreaID;
    @FXML
    private TextField textfield;
    @FXML
    private Button textfieldEnterID;
    @FXML
    private MenuItem accountBeheerID;
    @FXML
    private MenuItem faqsID;
    @FXML
    private MenuItem uitloggenID;

    ArrayList<Chat> chats = new ArrayList<>();
    private ObservableList<String> chatList;
    private int chatIndex = 0;

    public void initialize() {
        updateUI();



        if (false) { // Placeholder for Global.loggedInUser.getIsAdmin() == 1
            adminID.setVisible(true);
        } else {
            adminID.setVisible(false);
        }

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

    private void updateUI() {
        // Placeholder for switch (Global.SelectedLanguage) { ... }


        chatlistID.setText("Chat List"); // Placeholder for Global.GetSentence(21)
        textAreaID.setPromptText("Here is your conversation"); // Placeholder for Global.GetSentence(23)
        textfield.setPromptText("Ask a question"); // Placeholder for Global.GetSentence(24)
        instellingenID.setText("Settings"); // Placeholder for Global.GetSentence(25)
        uitloggenID.setText("Logout"); // Placeholder for Global.GetSentence(29)
    }

    public void addChat() {
        chats.get(chatIndex).setHistory(textAreaID.getText());
        textAreaID.setText(null);
        chatList.add("New Chat"); // Placeholder for Global.GetSentence(22)
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
        changeTextField("A:", "Response"); // Placeholder for ConversationDB.getText(textfield.getText())
        textfield.setText(null);
        chat.setHistory(textAreaID.getText());
    }

    @FXML
    public void goToAccountBeheerScherm(ActionEvent event) throws IOException {
        // Placeholder for FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("accountBeheerScherm.fxml"));
        // Placeholder for Parent root = fxmlLoader.load();
        // Placeholder for Stage currentStage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        // Placeholder for Scene scene = currentStage.getScene();
        // Placeholder for scene.setRoot(root);
    }

    @FXML
    public void goToAdminPage() throws IOException {
        if (false) { // Placeholder for Global.loggedInUser.getIsAdmin() == 1
            // Placeholder for FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("admin-view.fxml"));
            // Placeholder for Parent root = fxmlLoader.load();
            // Placeholder for Stage adminStage = new Stage();
            // Placeholder for adminStage.setScene(new Scene(root));
            // Placeholder for adminStage.show();
        }
    }

    public void changeTextField(String sender, String text) {
        textAreaID.appendText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss ")) + sender + " " + text + "\n");
        textAreaID.appendText("\n");
    }

    @FXML
    public void switchToLoginPage(ActionEvent event) throws IOException {
        // Placeholder for FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        // Placeholder for Parent root = fxmlLoader.load();
        // Placeholder for Stage currentStage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        // Placeholder for Scene scene = currentStage.getScene();
        // Placeholder for scene.setRoot(root);
    }
}