package dao;

import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ChatDAO {
    public static void insertChat(String chatName, int sprintID) {
        String sql = "INSERT INTO Chat(chatName, sprintID) VALUES(? ?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, chatName);
            pstmt.setInt(2, sprintID);

            pstmt.executeUpdate();
            System.out.println("Chat inserted successfully.");
        } catch (SQLException e) {
            System.out.println("Insert failed: " + e.getMessage());
        }
    }

}
