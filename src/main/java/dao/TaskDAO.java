package dao;

import classes.*;
import database.DatabaseConnection;
import queries.QueryUserStory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskDAO {

    public static void insertTask(Task task) {
        String sql = "INSERT INTO Task(taskDescription,usID) VALUES(?,?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, task.getDescription());
            pstmt.setInt(2, QueryUserStory.getUserStoryID(task.getUserStory()));
            pstmt.executeUpdate();
            System.out.println("Task inserted successfully.");
        } catch (SQLException e) {
            System.out.println("Insert failed: " + e.getMessage());
        }
    }
    public static int insertTaskAndReturnID(Task task) {
        String sql = "INSERT INTO Task(taskDescription,usID) VALUES(?,?)";
        int taskID = -1;
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, task.getDescription());
            pstmt.setInt(2, QueryUserStory.getUserStoryID(task.getUserStory()));
            pstmt.executeUpdate();

            try (ResultSet keys = pstmt.getGeneratedKeys()) {
                if (keys.next()) {
                    taskID = keys.getInt(1);
                    System.out.println("Task created: " + task.getDescription() + " (ID: " + taskID + ")");
                }
            }

            System.out.println("Task inserted successfully.");
        } catch (SQLException e) {
            System.out.println("Insert failed: " + e.getMessage());
        }
        return taskID;
    }
}
