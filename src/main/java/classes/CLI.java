package classes;
import dao.*;
import queries.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
public class CLI {
    Scanner scanner = new Scanner(System.in);

    public void scrumMasterMenu() {
        System.out.println("\n===== Scrum Master Menu =====");
        System.out.println("1. Add User");
        System.out.println("2. View Users");
        System.out.println("3. Exit");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the leftover newline character after nextInt()

        switch (choice) {
            case 1 -> {
                System.out.print("Enter username: ");
                String username = scanner.nextLine(); // Wacht op de gebruikersinvoer

                // Vervolgens de Scrum Master vraag
                System.out.print("Is Scrum Master? (true/false): ");
                boolean isScrumMaster = scanner.nextBoolean();
                scanner.nextLine(); // Consume the leftover newline character

                UserDAO.insertUser(username, isScrumMaster);
            }
            case 2 -> {
                QueryUsers.getUsers();
            }
            case 3 -> {
                mainMenu();
            }
            default -> System.out.println("Invalid choice. Try again.");
        }
    }

    public void scrumManagementMenu() {
        System.out.println("\n===== Scrum Master Menu =====");
        System.out.println("1. Add Sprint");
        System.out.println("2. Add Epic.");
        System.out.println("3. Add User Story");
        System.out.println("4. Add Task");
        System.out.println("5. Exit");
        System.out.print ("Choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> {
                try {

                    System.out.print("Enter sprint number (int): ");
                    int sprintNummer = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Use default 2-week sprint duration? (yes/no): ");
                    String useDefault = scanner.nextLine();

                    LocalDateTime startDate;
                    LocalDateTime endDate;

                    if (useDefault.equalsIgnoreCase("yes")) {
                        startDate = LocalDateTime.now();
                        endDate = startDate.plusWeeks(2);
                        System.out.println("Sprint will run from " + startDate + " to " + endDate);
                    } else {
                        System.out.print("Enter start date (YYYY-MM-DDTHH:MM): ");
                        String startInput = scanner.nextLine();
                        startDate = LocalDateTime.parse(startInput);

                        System.out.print("Enter end date (YYYY-MM-DDTHH:MM): ");
                        String endInput = scanner.nextLine();
                        endDate = LocalDateTime.parse(endInput);
                    }

                    String chatName = "Sprintchat_" + sprintNummer;
                    int chatID = ChatDAO.insertChatAndReturnID(chatName);

                    if (chatID == -1) {
                        System.out.println("Chat creation failed/ Sprint not added");
                        return;
                    }

                    SprintDAO.insertSprint(sprintNummer, startDate, endDate, chatID);
                    System.out.println("Sprint and chat succesfully added.");


                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date format. Please use: YYYY-MM-DDTHH:MM");
                } catch (Exception e) {
                    System.out.println("Error adding sprint: " + e.getMessage());
                }
            }
            case 2 -> {
            }
            case 3 -> {
            }
            case 4 -> {
            }
            case 5 -> {
            }
        }
    }

    public void chatMenu() {}

    public void mainMenu() {
        System.out.println("\n===== Welcome to TeamFlow =====");
        System.out.println("1. User Management");
        System.out.println("2. Send message");
        System.out.println("3. View chats");
        System.out.println("4. Scrum Management");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume line

            switch (choice) {
                case 1 -> {
                    scrumMasterMenu();
                }
                case 2 -> {
                    QueryUsers.getUsers();
                    System.out.print("Choose username: ");
                    String username = scanner.nextLine();

                    User selectedUser = QueryUsers.getSingleUserByName(username);
                    if (selectedUser == null) {
                        System.out.println("User not found. Please try again.");
                        return;
                    }

                    System.out.print("Enter Message: ");
                    String message = scanner.nextLine();

                    QueryChats.getChatQuery();
                    System.out.print("Choose chatID: ");
                    int chatID = scanner.nextInt();
                    Chat selectedChat = QueryChats.getSingleChat(chatID);

                    if (QueryChats.getSingleChat(chatID) == null) {
                        System.out.println("Chat not found. Please try again.");
                        return;
                    } else {
                        MessageDAO.insertMessage(selectedUser, message, false, selectedChat);
                        scanner.nextLine();
                    }


                }
                case 3 -> {
                    // Toon alle chats
                    QuerySprint.getSprintQuery();

                    System.out.println("Choose sprintID: ");
                    int sprintID = scanner.nextInt();
                    scanner.nextLine();

                    QueryChats.getChatsBySprint(sprintID);
                    System.out.print("Choose chatID: ");
                    int chatID = scanner.nextInt();
                    scanner.nextLine();

                    Chat chat = QueryChats.getSingleChat(chatID);
                    chat.setChatMessages(QueryMessages.getMessagesForChat(chatID));
                    if (chat != null) {
                       chat.viewChatMessages();
                    } else {
                        System.out.println("There is no chat with chatID: " + chatID);
                    }
                }
                case 4 -> {
                    scrumManagementMenu();
                }
                case 5 -> {
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
    }

    public static void main() {
        CLI cli = new CLI();
        while (true) {
            cli.mainMenu();
        }
    }
}