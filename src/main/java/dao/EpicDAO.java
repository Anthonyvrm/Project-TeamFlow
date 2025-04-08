package dao;

import classes.Epic;
import classes.UserStory;
import classes.Sprint;
import

import database.DatabaseConnection;
import queries.QuerySprint;
import queries.QueryUserStory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class EpicDAO {
    public static void insertEpic(Epic epic) {
        String sql = "INSERT INTO Epic(epicName, epicDescription) VALUES(?,?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, epic.getEpicName());
            pstmt.setString(2, epic.getEpicDescription());
            pstmt.setInt(3, QuerySprint.getSprintID(epic.getSprint()));
            pstmt.setInt(4, QueryUserStory.getUserStoryID(epic.getUserStory()));
            pstmt.executeUpdate();
            System.out.println("Epic inserted successfully.");
        } catch (SQLException e) {
            System.out.println("Insert failed: " + e.getMessage());
        }


    }

    public static void insertTestEpic() {
        insertEpic("Epic1" , "Epic1 Description");



    }
}
