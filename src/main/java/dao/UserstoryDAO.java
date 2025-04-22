package dao;

import classes.*;
import database.DatabaseConnection;
import queries.QueryChats;
import queries.QueryEpics;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserstoryDAO {
    public static void insertUserstory(UserStory userstory) {
        // SQL query to insert a new userstory into the Userstory table
        String sql = "INSERT INTO Userstory (usName, usDescription, epicID, chatID) VALUES(?,?,?,?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Set parameters for the INSERT statement using the Userstory object's properties
            pstmt.setString(1, userstory.getName());
            pstmt.setString(2, userstory.getDescription());
            pstmt.setInt(3, QueryEpics.getEpicID(userstory.getEpic()));
            pstmt.setInt(4, QueryChats.getChatID(userstory.getChat()));

            pstmt.executeUpdate();
            System.out.println("Userstory inserted successfully.");
        } catch (SQLException e) {
            System.out.println("Insert failed: " + e.getMessage());
        }
    }
}
