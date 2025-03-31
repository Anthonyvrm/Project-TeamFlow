package queries;

import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class
QueryUsers {
    public static void getUsers() {
        String sql = "SELECT * FROM User";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                System.out.println("User ID: " + rs.getInt("userID") +
                        ", Username: " + rs.getString("username") +
                        ", Scrum Master: " + rs.getBoolean("isScrumMaster"));
            }

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        getUsers();
    }
}
