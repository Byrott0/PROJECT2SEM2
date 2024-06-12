package org.example.project2sem2.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.example.project2sem2.Utils.Chat;

import java.util.ArrayList;
import java.util.List;

public class ChatBoxController {

    @FXML
    private Label NameID;

    @FXML
    private Button chatlistID;

    @FXML
    private Button imageFileChooserID;

    @FXML
    private ListView<Chat> listviewID;

    @FXML
    private ScrollBar scrollbarID;

    @FXML
    private TextArea textAreaID;

    @FXML
    private TextField textfield;

    @FXML
    private Button textfieldEnterID;

    @FXML
    private Label welkomID;

    @FXML
    private TextField OnderwerpNaam;

    private List<Chat> chats = new ArrayList<>();

    @FXML
    void addChat(ActionEvent event) {
        System.out.println("addChat called"); // Debugging line
        Chat newChat = new Chat();
        chats.add(newChat);
        listviewID.getItems().add(newChat);
        System.out.println("New chat added: " + newChat); // Debugging line
    }

    @FXML
    void VerstuurText(ActionEvent event) {
        String text = textfield.getText();
        textAreaID.appendText(text + "\n");
        textfield.clear();
    }

    @FXML
    void WeergeefOnderwerp(ActionEvent event) {
        String onderwerp = OnderwerpNaam.getText();
        Chat newChat = new Chat(onderwerp);
        chats.add(newChat);
        listviewID.getItems().add(newChat);
        OnderwerpNaam.clear();
    }

    @FXML
    void TypText(ActionEvent event) {
        System.out.println("TypText called");
        String text = textfield.getText();
        textAreaID.appendText(text + "\n");
        textfield.clear();
    }
}
