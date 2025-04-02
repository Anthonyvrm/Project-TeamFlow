package queries;

import database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryUserStory {
    public static void getUserStoriesQuery() {

        String sql = "SELECT * FROM Userstory";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int usID = rs.getInt("usID");
                String usName = rs.getString("usName");
                String usDescription = rs.getString("usDescription");
                int epicID = rs.getInt("epicID");
                int chatID = rs.getInt("chatID");

                System.out.println("usID: " + usID +
                        ", usName: " + usName +
                        ", usDescription: " + usDescription +
                        ", epicID: " + epicID +
                        ", chatID: " + chatID);
            }

        } catch (SQLException e) {
            System.out.println("Query mislukt: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        getUserStoriesQuery();
    }
}
