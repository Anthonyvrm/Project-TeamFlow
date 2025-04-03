package dao;
import classes.Chat;
import classes.User;

import database.DatabaseConnection;
import queries.QueryChats;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MessageDAO {
    public static void insertMessage(User user, String message, boolean isHighlighted, int chatID) {
        String sql = "INSERT INTO Message(userID ,message, isHighlighted, chatID) VALUES(?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, user.getUserID());
            pstmt.setString(2, message);
            pstmt.setBoolean(3, isHighlighted);
            pstmt.setInt(4, chatID);

            if(chatID >= 0) {
                pstmt.executeUpdate();
                System.out.println("Message inserted successfully.");
            } else {
                System.out.println("insert Failed...");
            }

        } catch (SQLException e) {
            System.out.println("Insert failed: " + e.getMessage());
        }
    }

    public static void main(String[] args) {



    }
}

