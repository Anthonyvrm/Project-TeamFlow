package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSetup {
    public static void createTables() {
        String createUserTable = """
            CREATE TABLE IF NOT EXISTS User (
                userID INTEGER PRIMARY KEY AUTOINCREMENT,
                username TEXT UNIQUE NOT NULL,
                isScrumMaster BOOLEAN DEFAULT FALSE
            );
        """;

        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(createUserTable);
            System.out.println("User table created successfully.");
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        createTables();
    }
}
