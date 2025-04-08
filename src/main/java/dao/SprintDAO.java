package dao;

import database.DatabaseConnection;

import java.sql.*;
import java.time.LocalDateTime;

public class SprintDAO {

    public static int insertSprintAndReturnID(int sprintInt, LocalDateTime startDate, LocalDateTime endDate) {
        String sql = "INSERT INTO Sprint(sprintInt, startDate, endDate) VALUES(?, ?, ?)";
        int sprintID = -1 ;
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, sprintInt);
            pstmt.setTimestamp(2, java.sql.Timestamp.valueOf(startDate));
            pstmt.setTimestamp(3, java.sql.Timestamp.valueOf(endDate));
            pstmt.executeUpdate();

            try (ResultSet keys = pstmt.getGeneratedKeys()) {
                if (keys.next()) {
                    sprintID = keys.getInt(1);
                    System.out.println("Sprint created: " + sprintInt + " (ID: " + sprintID + ")");
                }
            }
            System.out.println("Sprint inserted successfully.");
        } catch (SQLException e) {
            System.out.println("Insert failed: " + e.getMessage());
        }
        return sprintID ;
    }

    //public static void insertTestSprint() {
        //int sprintNumber = 1;
       // LocalDateTime startDate = LocalDateTime.now();
        //LocalDateTime endDate = startDate.plusWeeks(2);

        //insertSprint(sprintNumber, startDate, endDate);



    }
//}
