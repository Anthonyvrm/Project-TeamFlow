package dao;

import classes.UserStory;
import classes.Sprint;

import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class EpicDAO {
    public static void insertEpic(String epicName, String epicDescription, UserStory userStory, int epicID, Sprint sprint) {
        String sql = "INSERT INTO Epic(epicName, epicDescription) VALUES";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, epicName);
            pstmt.setString(2, epicDescription);
            pstmt.executeUpdate();
            System.out.println("Epic inserted successfully.");
        } catch (SQLException e) {
            System.out.println("Insert failed: " + e.getMessage());
        }


    }

    public static void main(String[] args) {



    }
}
