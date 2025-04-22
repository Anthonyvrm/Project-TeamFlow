package queries;

import classes.Chat;
import java.util.ArrayList;
import database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryChats {
    // Method to retrieve all chats from the database
    public static ArrayList<Chat> getAllChats() {
        // SQL query to select all records from the chat
        String sql = "SELECT * FROM Chat";
        ArrayList<Chat> chats = new ArrayList<>();

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            // Iterate through all rows in the ResultSet
            while (rs.next()) {
                String chatName = rs.getString("chatName"); // Get chatName
                Chat chat = new Chat(chatName); // Create Chat object
                chats.add(chat);
            }

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        }
        return chats;
    }

    // Method to retrieve a single chat from the database with the chatID
    public static Chat getSingleChat(int chatID) {
        // SQL query to select a single chat with the chatID
        String sql = "SELECT * FROM Chat WHERE chatID = ?";
        Chat chat = null;
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, chatID);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) { // If chat exists
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

    // Method to retrieve chatID from a specific chat
    public static int getChatID(Chat chat) {
        // SQL query to select the chatID from a given chat name
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