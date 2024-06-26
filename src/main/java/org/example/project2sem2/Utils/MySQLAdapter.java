package org.example.project2sem2.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLAdapter implements DatabaseAdapter {
    private static final String URL = "jdbc:mysql://sqldatabase.mysql.database.azure.com:3306/project";
    private static final String USER = "AdminH";
    private static final String PASSWORD = "Haagsegoon?";

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

