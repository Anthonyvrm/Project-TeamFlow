package dao;

import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class SprintDAO {
    public static void insertSprint(int sprintInt, LocalDateTime startDate, LocalDateTime endDate) {
        String sql = "INSERT INTO Sprint(sprintInt, startDate, endDate) VALUES(?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, sprintInt);
            pstmt.setTimestamp(3, java.sql.Timestamp.valueOf(startDate));
            pstmt.setTimestamp(4, java.sql.Timestamp.valueOf(endDate));
            pstmt.executeUpdate();
            System.out.println("Sprint inserted successfully.");
        } catch (SQLException e) {
            System.out.println("Insert failed: " + e.getMessage());
        }


    }

    public static void main(String[] args) {



    }
}
