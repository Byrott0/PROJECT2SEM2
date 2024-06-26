package org.example.project2sem2.Utils;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseAdapter {
    Connection getConnection() throws SQLException;
}
