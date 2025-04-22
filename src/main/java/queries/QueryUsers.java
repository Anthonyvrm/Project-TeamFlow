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

            // Loop through the results and create User objects
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
        String sql = "SELECT * FROM User WHERE userID = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set the userID parameter in the query
            pstmt.setInt(1, userID);

            // Retrieve data from results and create and return new user object
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String retrievedUsername = rs.getString("username");
                    boolean isScrumMaster = rs.getBoolean("isScrumMaster");

                    return new User(retrievedUsername, isScrumMaster);
                } else {
                    System.out.println("User not found.");
                    return null;  // Return null if no user has been found
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

            // Set the username parameter in the query
            pstmt.setString(1, user.getUsername());

            // Retrieve the userID from the results
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) { // Control if a user has been found
                    retrievedUserID = rs.getInt("userID");
                    return retrievedUserID;
                } else {
                    System.out.println("User not found.");
                    return retrievedUserID;  // Return null if no user has been found
                }
            }

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return retrievedUserID;
        }
    }
}
