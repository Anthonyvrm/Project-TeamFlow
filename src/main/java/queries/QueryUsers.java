package queries;

import classes.User;
import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryUsers {
    public static void getUsers() {
        String sql = "SELECT * FROM User";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                System.out.println("User ID: " + rs.getInt("userID") +
                        ", Username: " + rs.getString("username") +
                        ", Scrum Master: " + rs.getBoolean("isScrumMaster"));
            }

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        }
    }

    public static User getSingleUserByName(String username) {
        String sql = "SELECT * FROM User WHERE username = ?";  // Gebruik parameterized query

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username); // Stel de username in als parameter

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) { // Controleer of er een gebruiker is gevonden
                    int userID = rs.getInt("userID");
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

    public static User getSingleUserByID(int userID) {
        String sql = "SELECT * FROM User WHERE userID = ?";  // Gebruik parameterized query

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userID); // Stel de username in als parameter

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) { // Controleer of er een gebruiker is gevonden
                    int retrievedID = rs.getInt("userID");
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

    public static int getUserID(User user) {
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

    public static void main(String[] args) {
        getUsers();
    }
}
