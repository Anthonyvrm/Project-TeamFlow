package dao;

import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TaskDAO {
    public static void insertTask(String taskName, String taskDescription) {
        String sql = "INSERT INTO Task(taskName,taskDescription) VALUES(?,?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, taskName);
            pstmt.setString(2, taskDescription);

            pstmt.executeUpdate();
            System.out.println("Task inserted successfully.");
        } catch (SQLException e) {
            System.out.println("Insert failed: " + e.getMessage());
        }
    }

    public static void insertTestTask() {

        insertTask("Task1" , "Task1 Description");

    }
}
