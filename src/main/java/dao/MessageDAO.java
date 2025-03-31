package dao;
import classes.Chat;
import classes.User;

import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MessageDAO {
    public static void insertMessage(int messageID, User user, String message, boolean isHighlighted, Chat chat) {
        String sql = "INSERT INTO Message(messageID, userID ,message, isHighlighted, chatID) VALUES(?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, messageID);
            pstmt.setInt(2, user.getUserID());
            pstmt.setString(3, message);
            pstmt.setBoolean(4, isHighlighted);
            pstmt.setInt(5, chat.getChatID());
            pstmt.executeUpdate();
            System.out.println("Message inserted successfully.");
        } catch (SQLException e) {
            System.out.println("Insert failed: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

    }
}

