package queries;

import classes.Chat;
import classes.Sprint;
import database.DatabaseConnection;

import java.sql.*;
import java.time.LocalDateTime;

public class QuerySprint {
    public static void getSprintQuery() {
        String sql = "SELECT * FROM Sprint";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int sprintID = rs.getInt("sprintID");
                int sprintInt = rs.getInt("sprintInt");

                System.out.println("sprintID: " + sprintID + ", Sprint: " + sprintInt);
            }

        } catch (SQLException e) {
            System.out.println("Query mislukt: " + e.getMessage());
        }
    }

    public static int getSprintID(Sprint sprint) {
        String sql = "SELECT sprintID FROM Sprint WHERE sprintInt = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, sprint.getSprintInt());
            ResultSet rs = pstmt.executeQuery();

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

    public static Sprint getSingleSprint(int sprintID) {
        String sql = "SELECT * FROM Sprint WHERE sprintID = ?";
        Sprint sprint = null;
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, sprintID);
            ResultSet rs = pstmt.executeQuery();

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
                    System.out.println("⚠ Chat not found for sprint.");
                }
            } else {
                System.out.println("⚠ Sprint not found.");
            }

        } catch (SQLException e) {
            System.out.println("⚠ Sprint query failed: " + e.getMessage());
        }
        return sprint;
    }

    public static void main(String[] args) {
        getSprintQuery();
    }
}
