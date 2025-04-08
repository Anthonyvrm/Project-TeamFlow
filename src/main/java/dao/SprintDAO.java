package dao;

import classes.*;
import database.DatabaseConnection;

import java.sql.*;
import java.time.LocalDateTime;

public class SprintDAO {

    public static int insertSprintAndReturnID(Sprint sprint) {
        String sql = "INSERT INTO Sprint(sprintInt, startDate, endDate) VALUES(?, ?, ?)";
        int sprintID = -1;
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, sprint.getSprintInt());
            pstmt.setTimestamp(2, java.sql.Timestamp.valueOf(sprint.getStartDate()));
            pstmt.setTimestamp(3, java.sql.Timestamp.valueOf(sprint.getEndDate()));
            pstmt.executeUpdate();

            try (ResultSet keys = pstmt.getGeneratedKeys()) {
                if (keys.next()) {
                    sprintID = keys.getInt(1);
                    System.out.println("Sprint created: " + sprint.getSprintInt() + " (ID: " + sprintID + ")");
                }
            }
            System.out.println("Sprint inserted successfully.");
        } catch (SQLException e) {
            System.out.println("Insert failed: " + e.getMessage());
        }
        return sprintID;
    }
}
