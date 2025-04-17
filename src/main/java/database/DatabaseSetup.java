package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSetup {
    // Create all necessary tables, if they do not already exist
    public static void createTables() {
        // Create the User table to store application users
        String createUserTable = """
        CREATE TABLE IF NOT EXISTS User (
            userID INTEGER PRIMARY KEY AUTOINCREMENT,
            username TEXT UNIQUE NOT NULL,
            isScrumMaster BOOLEAN DEFAULT FALSE
        );
        """;

        // Create the Sprint table to store project sprints
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

        // Create the Chat table to store chats
        String createChatTable = """
        CREATE TABLE IF NOT EXISTS Chat (
            chatID INTEGER PRIMARY KEY AUTOINCREMENT,
            chatName TEXT NOT NULL
        );
        """;

        // Create the Epic table to store epics linked to sprints and chats
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

        // Create the Userstory table to store userstories linked to epics and tasks
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

        // Create the Task table to store tasks linked to user stories and chats
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

        // Create the Message table to store chat messages
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

        // Execute SQL statements to create the tables
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

    // Trigger creation of the tables
    public static void main(String[] args) {
        createTables();
    }
}
