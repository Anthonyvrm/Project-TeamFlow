package queries;

import classes.*;
import database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class QueryMessages {
    public static ArrayList<Message> getAllMessages() {
        String sql = "SELECT * FROM Message";
        ArrayList<Message> messages = new ArrayList<>();

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                User user = QueryUsers.getSingleUser(rs.getInt("userID"));
                String message = rs.getString("message");
                boolean isHighlighted = rs.getBoolean("isHighlighted");
                Chat chat = QueryChats.getSingleChat(rs.getInt("chatID"));

                messages.add(new Message(user, message, isHighlighted, chat));
            }
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        }
        return messages;
    }

    public static ArrayList<Message> getMessagesForChat(int chatID) {
        String sql = "SELECT * FROM Message WHERE chatID = ?"; // Use parameterized query
        ArrayList<Message> messages = new ArrayList<Message>();
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, chatID);

            try (ResultSet rs = pstmt.executeQuery()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                boolean hasMessages = false; // To check if any messages exist

                while (rs.next()) {
                    hasMessages = true;

                    User user = QueryUsers.getSingleUser(rs.getInt("userID"));
                    String message = rs.getString("message");
                    boolean isHighlighted = rs.getBoolean("isHighlighted");

                    messages.add(new Message(user, message, isHighlighted, QueryChats.getSingleChat(chatID)));
                }

                if (!hasMessages) {
                    System.out.println("No messages found.");
                    return null;
                }
            } catch (SQLException e) {
                System.out.println("Error retrieving messages: " + e.getMessage());
                return messages;
            }
        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
            return messages;
        }
        return messages;
    }
}