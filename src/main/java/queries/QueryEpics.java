package queries;

import classes.*;
import database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryEpics {
    // Method to retrieve all epics from the database
    public static ArrayList<Epic> getAllEpics() {
        // SQL query to select all records from the epic
        String sql = "SELECT * FROM Epic";
        ArrayList<Epic> epics = new ArrayList<>();
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String epicName = rs.getString("epicName");
                String epicDescription = rs.getString("epicDescription");
                Sprint sprint = QuerySprint.getSingleSprint(rs.getInt("sprintID"));
                Chat chat = QueryChats.getSingleChat(rs.getInt("chatID"));
                Epic epic = new Epic(epicName, epicDescription, sprint, chat);
                epics.add(epic);
            }
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        }
        return epics;
    }

    public static ArrayList<Epic> getEpicsBySprint(int sprintId) {
        String sql = "SELECT epicID FROM Sprint WHERE sprintID = ?";
        ArrayList<Epic> epics = new ArrayList<>();

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, sprintId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int epicID = rs.getInt("epicID");
                epics.add(QueryEpics.getSingleEpic(epicID));
            }
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        }
        return epics;
    }

    // Method to retrieve a single epic from the database with an epicID
    public static Epic getSingleEpic(int epicID){
        // SQL query to select a single epic with the epicID
        String sql = "SELECT * FROM Epic WHERE epicID = ?";
        Epic epic = null;
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, epicID);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                String epicName = rs.getString("epicName");
                String epicDescription = rs.getString("epicDescription");
                Sprint sprint = QuerySprint.getSingleSprint(rs.getInt("sprintID"));
                Chat chat = QueryChats.getSingleChat(rs.getInt("chatID"));
                epic = new Epic(epicName, epicDescription, sprint, chat);
            }
        } catch (SQLException e) {
            System.out.println("Query mislukt: " + e.getMessage());
        }
        return epic;
    }

    // Method to retrieve the epicID from a specific epic
    public static int getEpicID(Epic epic) {
        // SQL query to select the epicID from a given epic name
        String sql = "SELECT epicID FROM Epic WHERE epicName = ?";
        int epicID = -1;
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1, epic.getName());

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                epicID = rs.getInt("epicID");
            }
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        }
        return epicID;
    }
}
