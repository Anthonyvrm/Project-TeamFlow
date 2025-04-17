package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Path to the Sql lite database file
    private static final String URL = "jdbc:sqlite:src/main/resources/database/teamflowdatabase.db";

    // Attempt to connect and get a return to the SQL lite database
    public static Connection connect() {
        Connection conn = null;
        try {
            // Connect to the database using the URL
            conn = DriverManager.getConnection(URL);
            // Error message when connection fails
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
        return conn;
    }

    // Test connection to database
    public static void main(String[] args) {
        connect();
    }
}
