package dao;

import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ChatDAO {
    public static void insertChat(String chatName) {
        String sql = "INSERT INTO Chat(chatName) VALUES(?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, chatName);

            pstmt.executeUpdate();
            System.out.println("Chat inserted successfully.");
        } catch (SQLException e) {
            System.out.println("Insert failed: " + e.getMessage());
        }
    }

    public static void insertTestChat() {

        insertChat("Chat1");
        insertChat("Chat5");
        insertChat("Chat7");
        insertChat("Chat40");
        insertChat("Chat5780");

    }
}
