package org.example.project2sem2;

import java.sql.*;

public class Logincontroller {
    private boolean success;
    private int userId;

    public Logincontroller(boolean success, int userId) {
        this.success = success;
        this.userId = userId;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getUserId() {
        return userId;
    }
}
