package org.example.project2sem2.Utils;

import org.example.project2sem2.Model.User;

public class LoggedInUser {
    private static LoggedInUser instance = null;
    private User user;

    private LoggedInUser() {
        // Private constructor to prevent instantiation from outside
    }

    public static LoggedInUser getInstance() {
        if (instance == null) {
            instance = new LoggedInUser();
        }
        return instance;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}