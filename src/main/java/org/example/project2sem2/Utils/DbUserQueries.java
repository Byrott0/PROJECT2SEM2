package org.example.project2sem2.Utils;


import org.example.project2sem2.Model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbUserQueries {

    private static final Logger logger = LoggerFactory.getLogger(DbUserQueries.class);


    public static boolean login(String username, String password) {
        String query = "SELECT * FROM credentials WHERE username = ? AND password = ?";
        try (Connection connection = DbConnection.getConnection();
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
            logger.error("Error logging in user", e);
            return false;
        }
        return false;
    }


    public static boolean signup(User user) {
        String query = "INSERT INTO credentials (username, password, email) VALUES (?, ?, ?)";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            logger.error("Error signing up user", e);
            return false;
        }
    }


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

        try (Connection connection = DbConnection.getConnection();
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
            logger.error("Error updating user", e);
            return false;
        }
    }

    // Method to fetch a user by username
    public static User getUser(String username) {
        String query = "SELECT * FROM credentials WHERE username = ?";
        try (Connection connection = DbConnection.getConnection();
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
            logger.error("Error fetching user", e);
        }
        return null;
    }
    public static boolean deleteUser(String username) {
        String query = "DELETE FROM credentials WHERE username = ?";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
               logger.error("Error deleting user", e);
            return false;
        }
    }
}
