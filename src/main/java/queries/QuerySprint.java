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

//    public static int getSprintByID(int sprintID) {
//        String sql = "SELECT * FROM Sprint WHERE sprintID = ?";
//
//        try (Connection conn = DatabaseConnection.connect();
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//
//            pstmt.setInt(1, sprintID); // Stel de chatName in als parameter
//
//            ResultSet rs = pstmt.executeQuery() ;
//            while (rs.next()) {
//
//                int sprintId = rs.getInt("sprintID");
//                int sprintInt = rs.getInt("sprintInt");
//
//                System.out.println("Sprint ID: " + sprintId +
//                        ", Sprint int: " + sprintInt);
//            }
//        } catch (SQLException e) {
//            System.out.println("Query failed: " + e.getMessage());
//        }
//        return sprintID;
//    }

    public static void main(String[] args) {
        getSprintQuery();
    }
}
