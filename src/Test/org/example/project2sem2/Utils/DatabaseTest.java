package org.example.project2sem2.Utils;

import org.example.project2sem2.Model.User;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DatabaseTest {

    @BeforeAll
    public static void setup() {
        DbUserHandler.deleteUser("testuser");
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    @Order(1)
    public void signup() {
        // Arrange
        User user = new User("testuser", "testpassword", "testuser@example.com");

        // Act
        boolean result = DbUserHandler.signup(user);

        // Assert
        assertTrue(result, "Signup should return true for successful user creation.");
    }

    @Test
    @Order(2)
    public void login() {
        // Arrange
        String username = "testuser";
        String password = "testpassword";

        // Act
        boolean result = DbUserHandler.login(username, password);

        // Assert
        assertTrue(result, "Login should return true for valid credentials.");
    }

    @Test
    @Order(3)
    public void getUser() {
        // Arrange
        String username = "testuser";

        // Act
        User user = DbUserHandler.getUser(username);

        // Assert
        assertNotNull(user, "getUser should return a User object for an existing user.");
        assertEquals("testuser", user.getUsername(), "Usernames should match.");
        assertEquals("testpassword", user.getPassword(), "Passwords should match.");
        assertEquals("testuser@example.com", user.getEmail(), "Emails should match.");
    }

    @Test
    @Order(4)
    public void updateUser() {
        // Arrange
        User updatedUser = new User("testuser", "newpassword", "newemail@example.com");

        // Act
        boolean result = DbUserHandler.updateUser(updatedUser, "testuser");

        // Assert
        assertTrue(result, "updateUser should return true for a successful update.");

        // Act
        User user = DbUserHandler.getUser("testuser");

        // Assert
        assertNotNull(user, "getUser should return a User object for an existing user.");
        assertEquals("newpassword", user.getPassword(), "Passwords should match.");
        assertEquals("newemail@example.com", user.getEmail(), "Emails should match.");
    }

    @Test
    @Order(5)
    public void deleteUser() {
        // Arrange
        String username = "testuser";

        // Act
        boolean result = DbUserHandler.deleteUser(username);

        // Assert
        assertTrue(result, "deleteUser should return true for a successful deletion.");

        // Act
        User user = DbUserHandler.getUser(username);

        // Assert
        assertNull(user, "getUser should return null for a deleted user.");
    }
}
