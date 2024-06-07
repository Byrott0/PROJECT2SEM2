package org.example.project2sem2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseConnection {

    private String URL = "jdbc:mysql://sqldatabase.mysql.database.azure.com:3306";
    private String user = "AdminH";
    private String password = "Haagsegoon?";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, user, password);
    }
}