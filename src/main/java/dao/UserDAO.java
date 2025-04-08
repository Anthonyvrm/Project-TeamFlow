package dao;

import classes.*;
import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO {
    public static void insertUser(User user) {
        String sql = "INSERT INTO User(username, isScrumMaster) VALUES(?, ?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setBoolean(2, user.getIsScrumMaster());
            pstmt.executeUpdate();
            System.out.println("User inserted successfully.");
        } catch (SQLException e) {
            System.out.println("Insert failed: " + e.getMessage());
        }
    }
}
