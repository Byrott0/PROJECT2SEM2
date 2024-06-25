package org.example.project2sem2.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbChatQueries {

    private static final Logger logger = LoggerFactory.getLogger(DbChatQueries.class);



    public static void insertChatMessage(Chat chat) {
        String insertSQL = "INSERT INTO chat (message, username, subject) VALUES (?, ?, ?)";

        try (Connection conn = DbConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setString(1, chat.getHistory());
            pstmt.setString(2, LoggedInUser.getInstance().getUser().getUsername());
            pstmt.setString(3, chat.getName()); // Get the name of the chat
            pstmt.executeUpdate();
            System.out.println("Record inserted successfully");
        } catch (SQLException e) {
            logger.error("Error inserting chat message", e);
        }
    }

    public static List<Chat> selectAllChatMessages(String username) {
        String selectSQL = "SELECT id, message, username, subject FROM chat WHERE username = ?";
        List<Chat> chats = new ArrayList<>();

        try (Connection conn = DbConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String message = rs.getString("message");
                    String subject = rs.getString("subject");
                    Chat chat = new Chat(subject, subject, true); // Set loadedFromDB to true
                    chat.setHistory(message);
                    chats.add(chat);
                }
            }
        } catch (SQLException e) {
            logger.error("Error selecting chat messages", e);
        }
        return chats;
    }
}
