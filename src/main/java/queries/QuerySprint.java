package queries;

import database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuerySprint {
    public static void getSprintQuery() {
        String sql = "SELECT * FROM Sprint";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int sprintID = rs.getInt("sprintID");
                int sprintInt = rs.getInt("sprintInt");

                System.out.println("sprintID: " + sprintID + ", sprintInt: " + sprintInt);
            }

        } catch (SQLException e) {
            System.out.println("Query mislukt: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        getSprintQuery();
    }
}
