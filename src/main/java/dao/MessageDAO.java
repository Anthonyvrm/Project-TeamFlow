package dao;
import classes.Chat;
import classes.User;

import database.DatabaseConnection;
import queries.QueryChats;
import queries.QueryUsers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MessageDAO {
    public static void insertMessage(User user, String message, boolean isHighlighted, Chat chat) {
        String sql = "INSERT INTO Message(user ,message, isHighlighted, chat) VALUES(?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, QueryUsers.getUserID(user));
            pstmt.setString(2, message);
            pstmt.setBoolean(3, isHighlighted);
            pstmt.setInt(4, QueryChats.getChatID(chat));

            if(QueryChats.getChatID(chat) >= 0) {
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

