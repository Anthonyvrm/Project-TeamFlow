package dao;

import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserstoryDAO {
    public static void insertUserstory(String usName, String usDescription) {
        String sql = "INSERT INTO Userstory (usName, usDescription) VALUES(?,?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, usName);
            pstmt.setString(2, usDescription);

            pstmt.executeUpdate();
            System.out.println("Userstory inserted successfully.");
        } catch (SQLException e) {
            System.out.println("Insert failed: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        insertUserstory("userstory1", "userstory1 description");

    }
}
