package org.example.project2sem2.Utils;

import org.example.project2sem2.Model.User;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DatabaseTest {

    @BeforeAll
    static void setup() {
        DbUserQueries.deleteUser("testuser");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Order(1)
    void signup() {
        // Arrange
        User user = new User("testuser", "testpassword", "testuser@example.com");

        // Act
        boolean result = DbUserQueries.signup(user);

        // Assert
        assertTrue(result, "Signup should return true for successful user creation.");
    }

    @Test
    @Order(2)
    void login() {
        // Arrange
        String username = "testuser";
        String password = "testpassword";

        // Act
        boolean result = DbUserQueries.login(username, password);

        // Assert
        assertTrue(result, "Login should return true for valid credentials.");
    }

    @Test
    @Order(3)
    void getUser() {
        // Arrange
        String username = "testuser";

        // Act
        User user = DbUserQueries.getUser(username);

        // Assert
        assertNotNull(user, "getUser should return a User object for an existing user.");
        assertEquals("testuser", user.getUsername(), "Usernames should match.");
        assertEquals("testpassword", user.getPassword(), "Passwords should match.");
        assertEquals("testuser@example.com", user.getEmail(), "Emails should match.");
    }

    @Test
    @Order(4)
    void updateUser() {
        // Arrange
        User updatedUser = new User("testuser", "newpassword", "newemail@example.com");

        // Act
        boolean result = DbUserQueries.updateUser(updatedUser, "testuser");

        // Assert
        assertTrue(result, "updateUser should return true for a successful update.");

        // Act
        User user = DbUserQueries.getUser("testuser");

        // Assert
        assertNotNull(user, "getUser should return a User object for an existing user.");
        assertEquals("newpassword", user.getPassword(), "Passwords should match.");
        assertEquals("newemail@example.com", user.getEmail(), "Emails should match.");
    }

    @Test
    @Order(5)
    void deleteUser() {
        // Arrange
        String username = "testuser";

        // Act
        boolean result = DbUserQueries.deleteUser(username);

        // Assert
        assertTrue(result, "deleteUser should return true for a successful deletion.");

        // Act
        User user = DbUserQueries.getUser(username);

        // Assert
        assertNull(user, "getUser should return null for a deleted user.");
    }
}
