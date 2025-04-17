package dao;

import classes.*;
import database.DatabaseConnection;
import queries.QueryChats;

import java.sql.*;
import java.time.LocalDateTime;

public class SprintDAO {

    public static int insertSprintAndReturnID(Sprint sprint) {
        // SQL statement to insert a new Sprint entry into the Sprint table
        String sql = "INSERT INTO Sprint(sprintInt, startDate, endDate, chatID) VALUES(?, ?, ?, ?)";
        int sprintID = -1;
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            // Set parameters for the INSERT statement using the Sprint object's properties
            pstmt.setInt(1, sprint.getSprintInt());
            pstmt.setTimestamp(2, java.sql.Timestamp.valueOf(sprint.getStartDate()));
            pstmt.setTimestamp(3, java.sql.Timestamp.valueOf(sprint.getEndDate()));
            pstmt.setInt(4, QueryChats.getChatID(sprint.getSprintChat()));
            pstmt.executeUpdate();

            // Retrieve the generated keys to get the newly created Sprint ID
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
