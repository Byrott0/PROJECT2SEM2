package org.example.project2sem2.Utils;

import org.example.project2sem2.Model.User;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DatabaseTest {

    @BeforeAll
    static void setup() {
        // Ensure the database is in a clean state before running tests.
        // This might involve deleting test users, etc.
        Database.deleteUser("testuser");
    }

    @AfterEach
    void tearDown() {
        // Clean up after each test if necessary
    }

    @Test
    @Order(1)
    void signup() {
        User user = new User("testuser", "testpassword", "testuser@example.com");
        boolean result = Database.signup(user);
        assertTrue(result, "Signup should return true for successful user creation.");
    }

    @Test
    @Order(2)
    void login() {
        boolean result = Database.login("testuser", "testpassword");
        assertTrue(result, "Login should return true for valid credentials.");
    }

    @Test
    @Order(3)
    void getUser() {
        User user = Database.getUser("testuser");
        assertNotNull(user, "getUser should return a User object for an existing user.");
        assertEquals("testuser", user.getUsername(), "Usernames should match.");
        assertEquals("testpassword", user.getPassword(), "Passwords should match.");
        assertEquals("testuser@example.com", user.getEmail(), "Emails should match.");
    }

    @Test
    @Order(4)
    void updateUser() {
        User updatedUser = new User("testuser", "newpassword", "newemail@example.com");
        boolean result = Database.updateUser(updatedUser);
        assertTrue(result, "updateUser should return true for a successful update.");

        User user = Database.getUser("testuser");
        assertNotNull(user, "getUser should return a User object for an existing user.");
        assertEquals("newpassword", user.getPassword(), "Passwords should match.");
        assertEquals("newemail@example.com", user.getEmail(), "Emails should match.");
    }

    @Test
    @Order(5)
    void deleteUser() {
        boolean result = Database.deleteUser("testuser");
        assertTrue(result, "deleteUser should return true for a successful deletion.");

        User user = Database.getUser("testuser");
        assertNull(user, "getUser should return null for a deleted user.");
    }
}
