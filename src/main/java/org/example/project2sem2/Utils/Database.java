package org.example.project2sem2.Utils;

import org.example.project2sem2.Controller.ChatBoxController;
import org.example.project2sem2.Model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private static final String URL = "jdbc:mysql://sqldatabase.mysql.database.azure.com:3306/project";
    private static final String USER = "AdminH";
    private static final String PASSWORD = "Haagsegoon?";

    // Private constructor to prevent instantiation
    private Database() {
        throw new UnsupportedOperationException("Utility class");
    }

    // Method to get database connection
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Method to close resources
    private static void closeResources(AutoCloseable... resources) {
        for (AutoCloseable resource : resources) {
            if (resource != null) {
                try {
                    resource.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Method to execute login query and return User
    public static boolean login(String username, String password) {
        String query = "SELECT * FROM credentials WHERE username = ? AND password = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String email = resultSet.getString("email");
                    User user = new User(username, password, email);
                    LoggedInUser.getInstance().setUser(user);
                    return true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception properly in real scenarios
        }
        return false;
    }


    // Method to execute signup query
    public static boolean signup(User user) {
        String query = "INSERT INTO credentials (username, password, email) VALUES (?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception properly in real scenarios
            return false;
        }
    }

    // Method to update user details
    public static boolean updateUser(User user, String oldUsername) {
        StringBuilder query = new StringBuilder("UPDATE credentials SET ");
        boolean isPasswordChanged = user.getPassword() != null && !user.getPassword().isEmpty();
        boolean isEmailChanged = user.getEmail() != null && !user.getEmail().isEmpty();
        boolean isUsernameChanged = user.getUsername() != null && !user.getUsername().isEmpty();

        if (isPasswordChanged) {
            query.append("password = ?");
        }
        if (isEmailChanged) {
            if (isPasswordChanged) {
                query.append(", ");
            }
            query.append("email = ?");
        }
        if (isUsernameChanged) {
            if (isPasswordChanged || isEmailChanged) {
                query.append(", ");
            }
            query.append("username = ?");
        }
        query.append(" WHERE username = ?");

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {

            int index = 1;
            if (isPasswordChanged) {
                preparedStatement.setString(index++, user.getPassword());
            }
            if (isEmailChanged) {
                preparedStatement.setString(index++, user.getEmail());
            }
            if (isUsernameChanged) {
                preparedStatement.setString(index++, user.getUsername());
            }
            preparedStatement.setString(index, oldUsername);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception properly in real scenarios
            return false;
        }
    }

    // Method to delete a user
    public static boolean deleteUser(String username) {
        String query = "DELETE FROM credentials WHERE username = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception properly in real scenarios
            return false;
        }
    }

    // Method to fetch a user by username
    public static User getUser(String username) {
        String query = "SELECT * FROM credentials WHERE username = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String password = resultSet.getString("password");
                    String email = resultSet.getString("email");
                    return new User(username, password, email);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception properly in real scenarios
        }
        return null;
    }

    public static void insertChatMessage(Chat chat) {
        String insertSQL = "INSERT INTO chat (message, username, subject) VALUES (?, ?, ?)";

        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setString(1, chat.getHistory());
            pstmt.setString(2, LoggedInUser.getInstance().getUser().getUsername());
            pstmt.setString(3, chat.getName()); // Get the name of the chat
            pstmt.executeUpdate();
            System.out.println("Record inserted successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<Chat> selectAllChatMessages(String username) {
        String selectSQL = "SELECT id, message, username, subject FROM chat WHERE username = ?";
        List<Chat> chats = new ArrayList<>();

        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {
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
            System.out.println(e.getMessage());
        }
        return chats;
    }
}
