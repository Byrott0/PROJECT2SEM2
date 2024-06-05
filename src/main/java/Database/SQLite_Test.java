package Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLite_Test {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:identifier.sqlite";

        // Create a connection to the database
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                System.out.println("Connected to the database");

                // Create a new table
                createNewTable(conn);

                // Insert data into the table
                insertData(conn);

                // Query the data
                queryData(conn);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void createNewTable(Connection conn) {
        String sql = "CREATE TABLE IF NOT EXISTS users (\n"
                + " id integer PRIMARY KEY,\n"
                + " name text NOT NULL,\n"
                + " capacity real\n"
                + ");";

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void insertData(Connection conn) {
        String sql = "INSERT INTO users (name, capacity) VALUES ('Alice', 3.5), ('Bob', 4.0);";

        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Data inserted");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void queryData(Connection conn) {
        String sql = "SELECT id, name, capacity FROM users";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" +
                        rs.getString("name") + "\t" +
                        rs.getDouble("capacity"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
