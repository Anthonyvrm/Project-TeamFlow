package queries;

import classes.*;
import database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryTasks {
    public static ArrayList<Task> getAllTasks() {
        String sql = "SELECT * FROM Task";
        ArrayList<Task> tasks = new ArrayList<>();
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String taskDescription = rs.getString("taskDescription");
                UserStory userStory = QueryUserStory.getSingleUserStory(rs.getInt("usID"));
                Chat chat = QueryChats.getSingleChat(rs.getInt("chatID"));

                tasks.add(new Task(userStory, taskDescription, chat));
            }
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        }
        return tasks;
    }
}

