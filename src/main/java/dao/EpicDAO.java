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
        String sql = "INSERT INTO Epic(epicName, epicDescription, sprintID, chatID) VALUES(?,?,?,?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, epic.getEpicName());
            pstmt.setString(2, epic.getEpicDescription());
            pstmt.setInt(3, QuerySprint.getSprintID(epic.getSprint()));
            pstmt.setInt(4, QueryChats.getChatID(epic.getEpicChat()));
            pstmt.executeUpdate();
            System.out.println("Epic inserted successfully.");
        } catch (SQLException e) {
            System.out.println("Insert failed: " + e.getMessage());
        }
    }
}
