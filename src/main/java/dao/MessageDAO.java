package dao;

import classes.*;
import database.DatabaseConnection;
import queries.QueryChats;
import queries.QueryUsers;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MessageDAO {
    public static void insertMessage(Message message) {
        // SQL statement to insert a new Message entry into the Message table
        String sql = "INSERT INTO Message(userID ,message, isHighlighted, chatID) VALUES(?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Set parameters for the INSERT statement using the Message object's properties
            pstmt.setInt(1, QueryUsers.getUserID(message.getUser()));
            pstmt.setString(2, message.getMessage());
            pstmt.setBoolean(3, message.getIsHighlighted());
            pstmt.setInt(4, QueryChats.getChatID(message.getChat()));

            // Validate the chat ID before executing the INSERT statement
            if(QueryChats.getChatID(message.getChat()) >= 0) {
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

