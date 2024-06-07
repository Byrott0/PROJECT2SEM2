package org.example.project2sem2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseConnection {

    private String url = "jdbc:mysql://sqldatabase.mysql.database.azure.com:3306/project";
    private String user = "AdminH";
    private String password = "Haagsegoon?";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}