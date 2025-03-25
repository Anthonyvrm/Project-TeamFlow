package main;

import database.DatabaseSetup;
import dao.UserDAO;
import queries.QueryUsers;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize the database
        DatabaseSetup.createTables();

        while (true) {
            System.out.println("\n===== SQLite User Management =====");
            System.out.println("1. Add User");
            System.out.println("2. View Users");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Is Scrum Master? (true/false): ");
                    boolean isScrumMaster = scanner.nextBoolean();
                    scanner.nextLine(); // Consume newline
                    UserDAO.insertUser(username, isScrumMaster);
                }
                case 2 -> QueryUsers.getUsers();

                case 3 -> {
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
