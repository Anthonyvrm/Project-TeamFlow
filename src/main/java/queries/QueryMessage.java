package queries;

import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryTemplate {

    /**
     * Executes a SQL query and processes the results.
     */
    public static void executeQuery() {
        // Replace the SQL statement with your desired query.
        String sql = "SELECT * FROM your_table_name";

        // Establish connection, prepare statement, and execute query.
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            // Process the result set.
            while (rs.next()) {
                // Replace column names and types as necessary.
                int id = rs.getInt("id_column");
                String name = rs.getString("name_column");
                boolean flag = rs.getBoolean("flag_column");

                // Use the retrieved data as needed.
                System.out.println("ID: " + id + ", Name: " + name + ", Flag: " + flag);
            }
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        }
    }

    /**
     * Main method to run the query.
     */
    public static void main(String[] args) {
        executeQuery();
    }
}
