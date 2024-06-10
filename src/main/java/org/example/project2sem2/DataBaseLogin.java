package org.example.project2sem2;
import java.sql.*;

public class DataBaseLogin {

    private databaseConnection databaseConnection = new databaseConnection();

    public Logincontroller login(String username, String password) {
        String selectQuery = "SELECT * FROM credentials WHERE username = ? AND password = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Logincontroller(true, resultSet.getInt("id")); // User found
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Logincontroller(false, -1); // User not found
    }
}