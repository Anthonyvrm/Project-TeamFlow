package dao;

import classes.Epic;
import database.DatabaseConnection;
import queries.QueryChats;
import queries.QuerySprint;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class EpicDAO {
    public static void insertEpic(Epic epic) {
        // SQL statement to insert a new Epic entry into the Epic table
        String sql = "INSERT INTO Epic(epicName, epicDescription, sprintID, chatID) VALUES(?,?,?,?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Set parameters for the INSERT statement using the Epic object's properties
            pstmt.setString(1, epic.getName());
            pstmt.setString(2, epic.getDescription());
            pstmt.setInt(3, QuerySprint.getSprintID(epic.getSprint()));
            pstmt.setInt(4, QueryChats.getChatID(epic.getChat()));
            pstmt.executeUpdate();
            System.out.println("Epic inserted successfully.");
        } catch (SQLException e) {
            System.out.println("Insert failed: " + e.getMessage());
        }
    }
}
