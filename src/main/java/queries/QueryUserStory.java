package queries;

import classes.*;
import database.DatabaseConnection;

import javax.management.Query;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryUserStory {
    // Method to retrieve all userstories from the Userstory table in the database
    public static ArrayList<UserStory> getAllUserStories() {
        // SQL query to select all userstories records from the Userstory table
        String sql = "SELECT * FROM Userstory";
        ArrayList<UserStory> userStories = new ArrayList<>();
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String usName = rs.getString("usName");
                String usDescription = rs.getString("usDescription");
                Epic epic = QueryEpics.getSingleEpic(rs.getInt("epicID"));
                Chat chat = QueryChats.getSingleChat(rs.getInt("chatID"));
                UserStory userStory = new UserStory(usName, usDescription, chat, epic);
                userStories.add(userStory);
            }

        } catch (SQLException e) {
            System.out.println("Query mislukt: " + e.getMessage());
        }
        return userStories;
    }

    // Method to retrieve a single Userstory object by userStoryID
    public static UserStory getSingleUserStory(int userStoryID){
        // SQL query to select a single userStory by usID
        String sql = "SELECT * FROM Userstory WHERE usID = ?";
        UserStory userStory = null;
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userStoryID);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                String usName = rs.getString("usName");
                String usDescription = rs.getString("usDescription");
                Epic epic = QueryEpics.getSingleEpic(rs.getInt("epicID"));
                Chat chat = QueryChats.getSingleChat(rs.getInt("chatID"));
                userStory = new UserStory(usName, usDescription, chat, epic);
            }
        } catch (SQLException e) {
            System.out.println("Query mislukt: " + e.getMessage());
        }
        return userStory;
    }

    // Method to retrieve the userStoryID based on a given userstory object
    public static int getUserStoryID(UserStory userstory) {
        // SQL query to select the usID from the Userstory table where the usName matches with
        String sql = "SELECT usID FROM Userstory WHERE usName = ?";
        int usID = -1;
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userstory.getName());

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                usID = rs.getInt("usID");
            }

        } catch (SQLException e) {
            System.out.println("Query mislukt: " + e.getMessage());
        }
        return usID;
    }
}
