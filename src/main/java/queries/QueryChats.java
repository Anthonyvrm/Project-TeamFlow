package queries;

import classes.Chat;
import java.util.ArrayList;
import database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryChats {
    public static ArrayList<Chat> getAllChats() {
        String sql = "SELECT * FROM Chat";
        ArrayList<Chat> chats = new ArrayList<>();

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String chatName = rs.getString("chatName");
                Chat chat = new Chat(chatName);
                chats.add(chat);
            }

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        }
        return chats;
    }

    public static Chat getSingleChat(int chatID) {
        String sql = "SELECT * FROM Chat WHERE chatID = ?";
        Chat chat = null;
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, chatID);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String retrievedChatName = rs.getString("chatName");
                    chat = new Chat(retrievedChatName);
                } else {
                    System.out.println("Chat not found.");
                }
            }
            return chat;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public static int getChatID(Chat chat) {
        String sql = "SELECT chatID FROM Chat WHERE chatName = ?";  // Gebruik parameterized query
        int retrievedChatID = -1;

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, chat.getChatName());

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) { // Controleer of er een gebruiker is gevonden
                    retrievedChatID = rs.getInt("chatID");
                    // Maak en retourneer een User-object
                    return retrievedChatID;
                } else {
                    System.out.println("Chat not found.");
                    return retrievedChatID;  // Return null als geen gebruiker gevonden is
                }
            }

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return retrievedChatID;
        }
    }
}

