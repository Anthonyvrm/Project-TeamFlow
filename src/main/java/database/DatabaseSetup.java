package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSetup {
    public static void createTables() {
        String createUserTable = """
        CREATE TABLE IF NOT EXISTS User (
            userID INTEGER PRIMARY KEY AUTOINCREMENT,
            username TEXT UNIQUE NOT NULL,
            isScrumMaster BOOLEAN DEFAULT FALSE
        );
        """;

        String createSprintTable = """
    CREATE TABLE IF NOT EXISTS Sprint (
        sprintID INTEGER PRIMARY KEY AUTOINCREMENT,
        sprintInt INTEGER NOT NULL,
        startDate TIMESTAMP NOT NULL,
        endDate TIMESTAMP NOT NULL,
        chatID INTEGER,
        FOREIGN KEY (chatID) REFERENCES CHAT(chatID)
    );
    """;


        String createChatTable = """
        CREATE TABLE IF NOT EXISTS Chat (
            chatID INTEGER PRIMARY KEY AUTOINCREMENT,
            chatName TEXT NOT NULL
        );
        """;

        String createEpicTable = """
        CREATE TABLE IF NOT EXISTS Epic (
            epicID INTEGER PRIMARY KEY AUTOINCREMENT,
            epicName TEXT NOT NULL,
            epicDescription TEXT NOT NULL,
            sprintID INTEGER,
            chatID INTEGER,
            FOREIGN KEY (sprintID) REFERENCES Sprint(sprintID),
            FOREIGN KEY (chatID) REFERENCES Chat(chatID)
        );
        """;

        String createUserstoryTable = """
        CREATE TABLE IF NOT EXISTS Userstory (
            usID INTEGER PRIMARY KEY AUTOINCREMENT,
            usName TEXT NOT NULL,
            usDescription TEXT NOT NULL,
            epicID INTEGER,
            chatID INTEGER,
            FOREIGN KEY (epicID) REFERENCES Epic(epicID),
            FOREIGN KEY (chatID) REFERENCES Chat(chatID)
        );
        """;

        String createTaskTable = """
        CREATE TABLE IF NOT EXISTS Task (
            taskID INTEGER PRIMARY KEY AUTOINCREMENT,
            taskDescription TEXT NOT NULL,
            usID INTEGER,
            chatID INTEGER,
            FOREIGN KEY (usID) REFERENCES Userstory(usID),
            FOREIGN KEY (chatID) REFERENCES Chat(chatID)
        );
        """;

        String createMessageTable = """
        CREATE TABLE IF NOT EXISTS Message (
            messageID INTEGER PRIMARY KEY AUTOINCREMENT,
            userID INTEGER NOT NULL,
            message TEXT NOT NULL,
            isHighlighted BOOLEAN DEFAULT FALSE,
            chatID INTEGER NOT NULL,
            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
            FOREIGN KEY (userID) REFERENCES User(userID),
            FOREIGN KEY (chatID) REFERENCES Chat(chatID)
        );
        """;

        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(createUserTable);
            stmt.execute(createSprintTable);
            stmt.execute(createChatTable);
            stmt.execute(createEpicTable);
            stmt.execute(createUserstoryTable);
            stmt.execute(createTaskTable);
            stmt.execute(createMessageTable);
            System.out.println("Tables created successfully.");
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        createTables();
    }
}
