package queries;

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

    public static void main(String[] args) {
        getMessageQuery();
    }
}

