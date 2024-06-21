package org.example.project2sem2.Model;

public class User {
    private String username;
    private String password;
    private String email;
    private History history;

    // Parameterized constructor
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.history = new History();
    }

    // Getter and Setter for username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Getter and Setter for password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getter and Setter for email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}