package classes;
import dao.*;
import queries.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Objects;
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

                UserDAO.insertUser(new User (username, isScrumMaster));
                System.out.println("User: " + username + "Scrummaster: " + isScrumMaster );
            }
            case 2 -> {
                QueryUsers.getAllUsers();
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
                    int sprintInt = scanner.nextInt();
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

                    String chatName = "Sprintchat_" + sprintInt;
                    Chat sprintChat = new Chat(chatName);
                    ChatDAO.insertChat(sprintChat);
                    int sprintID = SprintDAO.insertSprintAndReturnID(new Sprint (sprintInt, startDate, endDate, sprintChat));

                    System.out.printf("Sprint (ID: %d) and chat succesfully added.", sprintID);

                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date format. Please use: YYYY-MM-DDTHH:MM");
                } catch (Exception e) {
                    System.out.println("Error adding sprint: " + e.getMessage());
                }
            }
            case 2 -> {
                try {
                    System.out.println("Enter epic name: ");
                    String epicName = scanner.nextLine();

                    System.out.println("Enter the epic description: ");
                    String epicDescription = scanner.nextLine();

                    QuerySprint.getAllSprints();
                    System.out.println("Enter the Sprint ID: ");
                    int sprintID = scanner.nextInt();
                    scanner.nextLine();

                    Sprint sprint = QuerySprint.getSingleSprint(sprintID);



                    Chat epicChat = new Chat("epicchat_" + epicName);
                    ChatDAO.insertChat(epicChat);
                    Epic epic = new Epic(epicName, epicDescription, sprint, epicChat);
                    EpicDAO.insertEpic(epic);



                } catch (Exception e) {
                    System.out.println("Error adding sprint: " + e.getMessage());
                }
            }


            case 3 -> {
                try {
                    System.out.println("Enter userstory name: ");
                    String usName = scanner.nextLine();

                    System.out.println("Enter userstory description: ");
                    String usDescription = scanner.nextLine();

                    QueryEpics.getAllEpics();
                    System.out.print("Enter Epic ID");
                    int epicID = scanner.nextInt();
                    scanner.nextLine();

                    Epic epic = QueryEpics.getSingleEpic(epicID);

                    Chat userStoryChat = new Chat("userstorychat_" + usName);
                    ChatDAO.insertChat(userStoryChat);
                    UserStory userstory = new UserStory(usName, usDescription, userStoryChat, epic);
                    UserstoryDAO.insertUserstory(userstory);


                } catch (Exception e) {
                    System.out.println("Error adding Userstory: " + e.getMessage());
                }
            }
            case 4 -> {
                try {
                    System.out.println("Enter task name: ");
                    String taskName = scanner.nextLine();

                    System.out.println("Enter the task description: ");
                    String taskDescription = scanner.nextLine();

                    QueryUserStory.getAllUserStories();
                    System.out.println("Enter User Story ID: ");
                    int userStoryID = scanner.nextInt();
                    scanner.nextLine();


                    UserStory userstory = QueryUserStory.getSingleUserStory(userStoryID);
                    Chat taskChat = new Chat("taskchat_" + taskName);
                    ChatDAO.insertChat(taskChat);
                    Task task = new Task(userstory, taskDescription, taskChat);
                    TaskDAO.insertTask(task);

                } catch (Exception e) {
                    System.out.println("Error adding task: " + e.getMessage());
                }
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
        System.out.println("3. View Sprints");
        System.out.println("4. Scrum Management");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume line

            switch (choice) {
                case 1 -> {
                    scrumMasterMenu();
                }
                case 2 -> {
                    for (User user : QueryUsers.getAllUsers()) {
                        System.out.printf("Username: %s Scrummaster: %b%n", user.getUsername(), user.getIsScrumMaster());
                    }
                    System.out.print("Choose username: ");
                    String username = scanner.nextLine();

                    User selectedUser = null;
                    for (User user : QueryUsers.getAllUsers()) {
                        if (Objects.equals(username, user.getUsername())) {
                            selectedUser = new User(user.getUsername(), user.getIsScrumMaster());
                            break;
                        }
                    }
                    if (selectedUser == null) {
                        System.out.println("User not found. Please try again.");
                        return;
                    }

                    System.out.print("Enter Message: ");
                    String message = scanner.nextLine();

                    for (Chat chat : QueryChats.getAllChats()) {
                        System.out.printf("Chat ID: %d Chat name: %s%n", QueryChats.getChatID(chat), chat.getChatName());
                    }
                    System.out.print("Choose chatID: ");
                    int chatID = scanner.nextInt();
                    Chat selectedChat = QueryChats.getSingleChat(chatID);

                    if (selectedChat == null) {
                        System.out.println("Chat not found. Please try again.");
                        return;
                    } else {
                        MessageDAO.insertMessage(new Message(selectedUser, message, false, selectedChat));
                        scanner.nextLine();
                    }

                }
                case 3 -> {
                    for (Sprint sprint : QuerySprint.getAllSprints()) {
                        System.out.printf("Sprint ID: %d SprintInt: %d%n", QuerySprint.getSprintID(sprint), sprint.getSprintInt());
                    }
                    System.out.println("Choose sprintID: ");
                    int sprintID = scanner.nextInt();
                    scanner.nextLine();

                    for (Chat chat : QuerySprint.getChatsBySprint(sprintID)){
                        System.out.printf("Chat ID: %d Chat name: %s%n", QueryChats.getChatID(chat), chat.getChatName());
                    }

                    System.out.print("Choose chatID: ");
                    int chatID = scanner.nextInt();
                    scanner.nextLine();

                    Chat chat = QueryChats.getSingleChat(chatID);

                    if (chat != null) {
                        chat.setChatMessages(QueryMessages.getMessagesForChat(chatID));
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