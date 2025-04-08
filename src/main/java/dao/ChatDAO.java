package dao;

import database.DatabaseConnection;

import java.sql.*;

public class ChatDAO {
    public static void insertChat(String chatName, int sprintID) {
        String sql = "INSERT INTO Chat(chatName, sprintID) VALUES(?, ?)";

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
    public static int insertChatAndReturnID(String chatName) {
        String sql = "INSERT INTO Chat(chatName) VALUES(?)";
        int chatID = -1;

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, chatName);
            pstmt.executeUpdate();

            try (ResultSet keys = pstmt.getGeneratedKeys()) {
                if (keys.next()) {
                    chatID = keys.getInt(1);
                    System.out.println("Chat created: " + chatName + " (ID: " + chatID + ")");
                }
            }
        } catch (SQLException e) {
            System.out.println("Chat insert failed: " + e.getMessage());
        }

        return chatID;
    }
}


