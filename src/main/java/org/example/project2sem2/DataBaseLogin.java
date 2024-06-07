package org.example.project2sem2;
import java.sql.*;

public class DataBaseLogin {

    private databaseConnection databaseConnection = new databaseConnection();

    public boolean login(String username, String password) {
        String selectQuery = "SELECT * FROM credentials WHERE username = ? AND password = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return true; // User found
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // User not found
    }
}