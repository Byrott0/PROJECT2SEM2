package org.example.project2sem2;

import java.sql.*;

public class Logincontroller {

    boolean isValidCredentials(String username, String password) {
        // Maak verbinding met de database
        String url = "jdbc:sqlite:identifier.sqlite";
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                // Maak een SQL-query om de gebruiker op te zoeken
                String sql = "SELECT * FROM users WHERE name = ? AND password = ?";

                // Voer de query uit
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, username);
                    pstmt.setString(2, password);
                    try (ResultSet rs = pstmt.executeQuery()) {
                        // Als er een resultaat is, dan is de gebruikersnaam en het wachtwoord geldig
                        if (rs.next()) {
                            return true;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // Als we hier komen, dan is de gebruikersnaam en het wachtwoord niet geldig
        return false;
    }



}
