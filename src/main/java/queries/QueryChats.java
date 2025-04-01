package queries;

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
                int sprintId = rs.getInt("SprintID");

                System.out.println("Chat ID: " + chatId +
                        ", Chat Name: " + chatName +
                        ", Sprint ID: " + sprintId);
            }

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        getChatQuery();
    }
}
