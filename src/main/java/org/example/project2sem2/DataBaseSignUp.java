package org.example.project2sem2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseSignUp {

    private databaseConnection databaseConnection = new databaseConnection();

    public void insertIntoCredentials(String username, String Password, String email) {
        try {
            String selectQuery = "SELECT * FROM credentials WHERE id = ?";
            String insertQuery = "INSERT INTO credentials (id, username, password, email) VALUES (?, ?, ?, ?)";

            int id = 1;

            try (Connection connection = databaseConnection.getConnection();
                 PreparedStatement selectPreparedStatement = connection.prepareStatement(selectQuery)) {

                selectPreparedStatement.setInt(1, id);
                boolean idExists = true;
                while (idExists) {
                    try (ResultSet resultSet = selectPreparedStatement.executeQuery()) {
                        if (resultSet.next()) {
                            id++; // Increment id if it already exists
                            selectPreparedStatement.setInt(1, id);
                        } else {
                            idExists = false;
                        }
                    }
                }

                try (PreparedStatement insertPreparedStatement = connection.prepareStatement(insertQuery)) {
                    insertPreparedStatement.setInt(1, id);
                    insertPreparedStatement.setString(2, username);
                    insertPreparedStatement.setString(3, Password);
                    insertPreparedStatement.setString(4, email);

                    insertPreparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
