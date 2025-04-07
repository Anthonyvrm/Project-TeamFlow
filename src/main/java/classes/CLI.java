package classes;
import dao.MessageDAO;
import dao.UserDAO;
import queries.QueryChats;
import queries.QueryUsers;

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

    public void chatMenu() {}

    public void mainMenu() {
        System.out.println("\n===== Welcome to TeamFlow =====");
        System.out.println("1. User Management");
        System.out.println("2. Send message");
        System.out.println("3. View chats");
        System.out.println("4. Exit");
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
                    QueryChats.getChatQuery();
                    System.out.print("Choose chatID: ");
                    int chatID = scanner.nextInt();

                    Chat chat = QueryChats.getSingleChat(chatID);
                    if (chat != null) {
                       chat.viewChatMessages();
                    } else {
                        System.out.println("There is no chat with chatID: " + chatID);
                    }
                }
                case 4 -> {
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