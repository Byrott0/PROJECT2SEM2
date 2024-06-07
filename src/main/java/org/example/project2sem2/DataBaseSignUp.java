package org.example.project2sem2;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBaseSignUp {

    private databaseConnection databaseConnection = new databaseConnection();

    public void insertIntoCredentials(int id, String username , String Password, String email){
        try {
            String insertQuery = "INSERT INTO user (id, username, password, email) VALUES (?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(insertQuery)) {
                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, username);
                preparedStatement.setString(3, Password);
                preparedStatement.setString(4, email);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
