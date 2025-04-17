package dao;

import classes.Chat;
import database.DatabaseConnection;

import java.sql.*;

public class ChatDAO {
    public static void insertChat(Chat chat) {
        // SQL statement to insert a new Chat entry into the Chat table
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
}


