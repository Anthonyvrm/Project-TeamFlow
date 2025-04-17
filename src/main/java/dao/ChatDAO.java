package dao;

import classes.Chat;
import database.DatabaseConnection;

import java.sql.*;

public class ChatDAO {
    // SQL statement to insert a new Chat entry into the Chat table
    public static void insertChat(Chat chat) {
        String sql = "INSERT INTO Chat(Chatname) VALUES(?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Set the chat name parameter in the INSERT statement
            pstmt.setString(1, chat.getChatName());

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


