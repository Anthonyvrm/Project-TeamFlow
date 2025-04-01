package dao;

import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO {
    public static void insertUser(String username, boolean isScrumMaster) {
        String sql = "INSERT INTO User(username, isScrumMaster) VALUES(?, ?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setBoolean(2, isScrumMaster);
            pstmt.executeUpdate();
            System.out.println("User inserted successfully.");
        } catch (SQLException e) {
            System.out.println("Insert failed: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        insertUser("Anthony", false);
        insertUser("Robbert", true);
        insertUser("Mirtan", false);
        insertUser("Mees", false);
    }
}
