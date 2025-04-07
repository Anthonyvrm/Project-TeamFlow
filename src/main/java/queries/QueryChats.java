package queries;

import classes.Chat;
import classes.User;
import database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryChats {
    public static void getChatQuery() {
        String sql = "SELECT * FROM Chat";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {

                int chatId = rs.getInt("chatID");
                String chatName = rs.getString("chatName");
                int sprintId = rs.getInt("sprintID");

                System.out.println("Chat ID: " + chatId +
                        ", Chat Name: " + chatName +
                        ", Sprint ID: " + sprintId);
            }

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        }
    }

    public static Chat getSingleChat(int chat) {
        String sql = "SELECT * FROM Chat WHERE chatID = ?";  // Gebruik parameterized query

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, chat); // Stel de chatName in als parameter

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) { // Controleer of er een gebruiker is gevonden
                    String retrievedChatName = rs.getString("chatName");

                    // Maak en retourneer een User-object
                    return new Chat(retrievedChatName);
                } else {
                    System.out.println("Chat not found.");
                    return null;  // Return null als geen gebruiker gevonden is
                }
            }

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public static int getChatID(Chat chat) {
        String sql = "SELECT * FROM User WHERE chatname = ?";  // Gebruik parameterized query

        int retrievedChatID = -1 ;

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, chat.getChatName()); // Stel de username in als parameter

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

    public static void main(String[] args) {
        getChatQuery();
    }
}
