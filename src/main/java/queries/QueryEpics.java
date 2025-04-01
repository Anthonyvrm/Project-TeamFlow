package queries;

import database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryEpics {
    public static void getEpicQuery() {
        String sql = "SELECT * FROM Epic";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {

                int epicId = rs.getInt("epicID");
                String epicName = rs.getString("epicName");
                String epicDescription = rs.getString("epicDesription");
                int sprintId = rs.getInt("sprintID");
                int chatId = rs.getInt("chatID");

                System.out.println("Epic ID: " + epicId +
                        ", Epic Name: " + epicName +
                        ", Epic Description: " + epicDescription +
                        ", Sprint ID: " + sprintId +
                        ", Chat ID: " + chatId);
            }

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        getEpicQuery();
    }
}
