package queries;

import database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryTasks {
    public static void getTaskQuery() {
        String sql = "SELECT * FROM Task";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {

                int taskId = rs.getInt("taskID");
                String taskName = rs.getString("taskName");
                String taskDescription = rs.getString("taskDescription");
                int usId = rs.getInt("usID");
                int chatId = rs.getInt("chatID");

                System.out.println("Task ID: " + taskId +
                        ", Task Name: " + taskName +
                        ", Task Description: " + taskDescription +
                        ", User ID: " + usId +
                        ", Chat ID: " + chatId);
            }

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        getTaskQuery();
    }
}

