package queries;

import classes.User;
import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryUsers {
    // Method to retrieve all users from the User table in the database
    public static ArrayList<User> getAllUsers() {
        // SQL query to select all user records from the User table
        String sql = "SELECT * FROM User";
        ArrayList<User> users = new ArrayList<>();

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String username = rs.getString("username");
                boolean isScrumMaster = rs.getBoolean("isScrumMaster");

                users.add(new User(username, isScrumMaster));
            }
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        }
        return users;
    }

    // Method to retrieve a single User object by userID
    public static User getSingleUser(int userID) {
        // SQL query to select a single user by sprintID
        String sql = "SELECT * FROM User WHERE userID = ?";  // Gebruik parameterized query

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userID); // Stel de username in als parameter

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) { // Controleer of er een gebruiker is gevonden
                    String retrievedUsername = rs.getString("username");
                    boolean isScrumMaster = rs.getBoolean("isScrumMaster");

                    // Maak en retourneer een User-object
                    return new User(retrievedUsername, isScrumMaster);
                } else {
                    System.out.println("User not found.");
                    return null;  // Return null als geen gebruiker gevonden is
                }
            }

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    // Method to retrieve the userID based on a given user object
    public static int getUserID(User user) {
        // SQL query to retrieve userID for the given username
        String sql = "SELECT * FROM User WHERE username = ?";  // Gebruik parameterized query

        int retrievedUserID = -1 ;

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getUsername()); // Stel de username in als parameter

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) { // Controleer of er een gebruiker is gevonden
                    retrievedUserID = rs.getInt("userID");
                    // Maak en retourneer een User-object
                    return retrievedUserID;
                } else {
                    System.out.println("User not found.");
                    return retrievedUserID;  // Return null als geen gebruiker gevonden is
                }
            }

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return retrievedUserID;
        }
    }
}
