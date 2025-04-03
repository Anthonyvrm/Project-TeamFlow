package queries;

import classes.User;
import database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class QueryMessages {
    public static void getMessageQuery() {
        String sql = "SELECT * FROM Message";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            while (rs.next()) {

                int messageId = rs.getInt("messageID");
                int userId = rs.getInt("userID");
                String message = rs.getString("message");
                boolean isHighlighted = rs.getBoolean("isHiglighted");
                int chatId = rs.getInt("chatID");

                String createdAtStr = rs.getString("created_at");

                LocalDateTime createdAt = LocalDateTime.parse(createdAtStr, formatter);

                System.out.println("Message ID: " + messageId +
                        ", User ID: " + userId +
                        ", message: " + message +
                        ", isHighlighted: " + isHighlighted +
                        ", chatID: " + chatId +
                        ", created_at: " + createdAt);
            }

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        }
    }

    public static void getChatMessageQuery(int chatID) {
        String sql = "SELECT * FROM Message WHERE chatID = ?"; // Use parameterized query

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, chatID);

            try (ResultSet rs = pstmt.executeQuery()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                boolean hasMessages = false; // To check if any messages exist

                while (rs.next()) {
                    hasMessages = true;
                    int userId = rs.getInt("userID");
                    String message = rs.getString("message");
                    String createdAtStr = rs.getString("created_at");

                    LocalDateTime createdAt = LocalDateTime.parse(createdAtStr, formatter);

                    System.out.println("User ID: " + userId +
                            ", Message: " + message +
                            ", Sent at: " + createdAt);
                }

                if (!hasMessages) {
                    System.out.println("No messages found.");
                }
            } catch (SQLException e) {
                System.out.println("Error retrieving messages: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        getMessageQuery();
    }
}