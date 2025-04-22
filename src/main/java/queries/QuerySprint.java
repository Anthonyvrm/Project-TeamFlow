package queries;

import classes.Chat;
import classes.Sprint;
import database.DatabaseConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class QuerySprint {
    // Method to retrieve all sprints from the Sprint table in the database
    public static ArrayList<Sprint> getAllSprints() {
        // SQL query to select all sprints from the Sprint table in the database
        String sql = "SELECT * FROM Sprint";
        ArrayList<Sprint> sprint = new ArrayList<>();

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            // Loop through result set and create Sprint objects
            while (rs.next()) {
                int sprintInt = rs.getInt("sprintInt");
                LocalDateTime startDate = rs.getTimestamp("startDate").toLocalDateTime();
                LocalDateTime endDate = rs.getTimestamp("endDate").toLocalDateTime();

                sprint.add(new Sprint(sprintInt, startDate, endDate, null));
            }

        } catch (SQLException e) {
            System.out.println("Query mislukt: " + e.getMessage());
        }
        return sprint;
    }

    // Method to retrieve the sprintID based on a given sprint object
    public static int getSprintID(Sprint sprint) {
        // SQL query to select the sprintID from the Sprint table where the sprintInt matches with
        String sql = "SELECT sprintID FROM Sprint WHERE sprintInt = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, sprint.getSprintInt());
            ResultSet rs = pstmt.executeQuery();

            // Return sprintID if found
            if (rs.next()) {
                return rs.getInt("sprintID");
            } else {
                System.out.println("⚠ Sprint not found.");
            }

        } catch (SQLException e) {
            System.out.println("⚠ Sprint query failed: " + e.getMessage());
        }

        return -1;
    }

    // Method to retrieve a single Sprint object by sprintID
    public static Sprint getSingleSprint(int sprintID) {
        // SQL query to select a single sprint by sprintID
        String sql = "SELECT * FROM Sprint WHERE sprintID = ?";
        Sprint sprint = null;
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, sprintID);
            ResultSet rs = pstmt.executeQuery();

            // Create Sprint object if found
            if (rs.next()) {
                int sprintInt = rs.getInt("sprintInt");
                Timestamp start = rs.getTimestamp("startDate");
                Timestamp end = rs.getTimestamp("endDate");
                int chatID = rs.getInt("chatID");

                LocalDateTime startDate = start.toLocalDateTime();
                LocalDateTime endDate = end.toLocalDateTime();

                Chat chat = QueryChats.getSingleChat(chatID);
                if (chat != null) {
                   sprint = new Sprint(sprintInt, startDate, endDate, chat);
                } else {
                    System.out.println("Chat not found for sprint.");
                }
            } else {
                System.out.println("Sprint not found.");
            }

        } catch (SQLException e) {
            System.out.println("Sprint query failed: " + e.getMessage());
        }
        return sprint;
    }

    public static ArrayList<Chat> getChatsBySprint(int sprintId) {
        String sql = "SELECT * FROM Sprint WHERE sprintID = ?";
        ArrayList<Chat> chats = new ArrayList<>();

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, sprintId);
            ResultSet rs = pstmt.executeQuery();
            // Add each chat found to the list
            while (rs.next()) {
                int chatID = rs.getInt("chatID");
                chats.add(QueryChats.getSingleChat(chatID));
            }
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        }
        return chats;
    }
}
