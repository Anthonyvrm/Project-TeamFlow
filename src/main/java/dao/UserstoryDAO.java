package dao;

import classes.*;
import database.DatabaseConnection;
import queries.QueryEpics;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserstoryDAO {
    public static void insertUserstory(UserStory userstory) {
        String sql = "INSERT INTO Userstory (usName, usDescription, epicID) VALUES(?,?,?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userstory.getUsName());
            pstmt.setString(2, userstory.getUsDescription());
            pstmt.setInt(3, QueryEpics.getEpicID(userstory.getEpic()));

            pstmt.executeUpdate();
            System.out.println("Userstory inserted successfully.");
        } catch (SQLException e) {
            System.out.println("Insert failed: " + e.getMessage());
        }
    }
}
