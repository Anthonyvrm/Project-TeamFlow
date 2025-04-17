package classes;
import dao.*;
import queries.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Scanner;
public class CLI {
    Scanner scanner = new Scanner(System.in);

    public void userManagementMenu() {
        System.out.println("\n===== User management =====");
        System.out.println("1. Add User");
        System.out.println("2. View Users");
        System.out.println("3. Go back");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> {
                //user chooses username
                System.out.print("Enter username: ");
                String username = scanner.nextLine();

                //Is the user a scrummaster
                System.out.print("Is Scrum Master? (true/false): ");
                boolean isScrumMaster = scanner.nextBoolean();
                scanner.nextLine(); //new line

                //Makes User object and sends it to the database
                UserDAO.insertUser(new User (username, isScrumMaster));
                System.out.printf("User: %s Scrum Master: %b%n", username, isScrumMaster);
            }
            case 2 -> {
                //print all users
                for (User user : QueryUsers.getAllUsers()) {
                    System.out.printf("Username: %s Scrummaster: %b%n", user.getUsername(), user.getIsScrumMaster());
                }
            }
            case 3 -> {
                mainMenu();
            }
            default -> System.out.println("Invalid choice. Try again.");
        }
    }

    public void scrumManagementMenu() {
        System.out.println("\n===== Scrum management =====");
        System.out.println("1. Add Sprint");
        System.out.println("2. Add Epic");
        System.out.println("3. Add User Story");
        System.out.println("4. Add Task");
        System.out.println("5. View sprint status");
        System.out.println("6. Exit");
        System.out.print ("Choose an option: ");

        //user chooses an option
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> {
                try {
                    //give the sprint an integer
                    System.out.print("Enter sprint number (int): ");
                    int sprintInt = scanner.nextInt();
                    scanner.nextLine();

                    //Sprint duration current or not
                    System.out.print("Use default 2-week sprint duration? (J/N): ");
                    String useDefault = scanner.nextLine();

                    LocalDateTime startDate;
                    LocalDateTime endDate;

                    //if current automatically add start and end dates
                    if (useDefault.equalsIgnoreCase("J")) {
                        startDate = LocalDateTime.now();
                        endDate = startDate.plusWeeks(2);
                        System.out.println("Sprint will run from " + startDate + " to " + endDate);
                    } else { //if not current manually add dates
                        System.out.print("Enter start date (YYYY-MM-DDTHH:MM): ");
                        String startInput = scanner.nextLine();
                        startDate = LocalDateTime.parse(startInput);

                        System.out.print("Enter end date (YYYY-MM-DDTHH:MM): ");
                        String endInput = scanner.nextLine();
                        endDate = LocalDateTime.parse(endInput);
                    }

                    String chatName = "Sprintchat:" + sprintInt;
                    Chat sprintChat = new Chat(chatName); //create chat object with sprint as the name
                    ChatDAO.insertChat(sprintChat); //add chat to database
                    int sprintID = SprintDAO.insertSprintAndReturnID(new Sprint (sprintInt, startDate, endDate, sprintChat)); //add sprint to database
                    System.out.printf("Sprint (ID: %d) and chat succesfully added.", sprintID);
                    //errors
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date format. Please use: YYYY-MM-DDTHH:MM");
                } catch (Exception e) {
                    System.out.println("Error adding sprint: " + e.getMessage());
                }
            }
            case 2 -> {
                try {
                    //enter epic name
                    System.out.println("Enter epic name: ");
                    String epicName = scanner.nextLine();

                    //enter epic description
                    System.out.println("Enter the epic description: ");
                    String epicDescription = scanner.nextLine();

                    //print all sprints with ID and int
                    for (Sprint sprint : QuerySprint.getAllSprints()) {
                        System.out.printf("Sprint ID: %d SprintInt: %d%n", QuerySprint.getSprintID(sprint), sprint.getSprintInt());
                    }
                    //enter the sprintID
                    System.out.println("Enter the Sprint ID: ");
                    int sprintID = scanner.nextInt();
                    scanner.nextLine();

                    //get the sprint by the ID
                    Sprint sprint = QuerySprint.getSingleSprint(sprintID);

                    Chat epicChat = new Chat("Epic: " + epicName); //Create chat object
                    ChatDAO.insertChat(epicChat); //add chat to database
                    EpicDAO.insertEpic(new Epic(epicName, epicDescription, sprint, epicChat)); //create epic object and add it to database

                    //error
                } catch (Exception e) {
                    System.out.println("Error adding sprint: " + e.getMessage());
                }
            }


            case 3 -> {
                try {
                    //enter userstory name
                    System.out.println("Enter userstory name: ");
                    String usName = scanner.nextLine();

                    //enter userstory description
                    System.out.println("Enter userstory description: ");
                    String usDescription = scanner.nextLine();

                    //print all epics
                    for(Epic epic : QueryEpics.getAllEpics()){
                        System.out.printf("Epic ID: %d Name: %s%n", QueryEpics.getEpicID(epic), epic.getEpicName());
                    }
                    //enter the epicID
                    System.out.print("Enter epic ID: ");
                    int epicID = scanner.nextInt();
                    scanner.nextLine();

                    Epic epic = QueryEpics.getSingleEpic(epicID); //get chosen epic
                    Chat userStoryChat = new Chat("Userstory: " + usName); //create chat object
                    ChatDAO.insertChat(userStoryChat); //add chat to database
                    UserstoryDAO.insertUserstory(new UserStory(usName, usDescription, userStoryChat, epic)); //create Userstory object and add to database
                    //error
                } catch (Exception e) {
                    System.out.println("Error adding userstory: " + e.getMessage());
                }
            }
            case 4 -> {
                try {
                    //enter task name
                    System.out.println("Enter task name: ");
                    String taskName = scanner.nextLine();

                    //enter task description
                    System.out.println("Enter the task description: ");
                    String taskDescription = scanner.nextLine();

                    //print all userstories
                    for (UserStory userStory : QueryUserStory.getAllUserStories()){
                        System.out.printf("Userstory ID: %d Name: %s", QueryUserStory.getUserStoryID(userStory), userStory.getUsName());
                    }
                    //enter usID
                    System.out.println("Enter User Story ID: ");
                    int userStoryID = scanner.nextInt();
                    scanner.nextLine();

                    //selected Userstory
                    UserStory userstory = QueryUserStory.getSingleUserStory(userStoryID);
                    Chat taskChat = new Chat("Task: " + taskName); //create chat object
                    ChatDAO.insertChat(taskChat); //add chat to database
                    TaskDAO.insertTask(new Task(userstory, taskDescription, taskChat)); //create task
                    //error
                } catch (Exception e) {
                    System.out.println("Error adding task: " + e.getMessage());
                }
            }
            case 5 -> {
                try {
                    //print all sprints
                    System.out.println("Overview of sprints:");
                    for (Sprint sprint : QuerySprint.getAllSprints()) {
                        String status = sprint.isCurrent() ? "Current" : "Not current"; //give status true or false based on Date
                        System.out.printf("Sprint ID: %d, Sprint Int: %d, Status: %s%n",
                                QuerySprint.getSprintID(sprint), sprint.getSprintInt(), status);
                    }
                    //error
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
    }

    public void mainMenu() {
        System.out.println("\n===== Welcome to TeamFlow =====");
        System.out.println("1. User management");
        System.out.println("2. Send message");
        System.out.println("3. View chats");
        System.out.println("4. Scrum management");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume line

            switch (choice) {
                case 1 -> {
                    //refers to userManagement menu
                    userManagementMenu();
                }
                case 2 -> {
                    //print all users
                    for (User user : QueryUsers.getAllUsers()) {
                        System.out.printf("Username: %s Scrummaster: %b%n", user.getUsername(), user.getIsScrumMaster());
                    }
                    //enter username
                    System.out.print("Choose username: ");
                    String username = scanner.nextLine();

                    User selectedUser = null;
                    //gets single user
                    for (User user : QueryUsers.getAllUsers()) {
                        if (Objects.equals(username, user.getUsername())) {
                            selectedUser = new User(user.getUsername(), user.getIsScrumMaster());
                            break;
                        }
                    } //error
                    if (selectedUser == null) {
                        System.out.println("User not found. Please try again.");
                        return;
                    }

                    String message;
                    //option to highlight if user is a scrummaster
                    if (selectedUser.getIsScrumMaster()) {
                        System.out.println("Do you want to highlight this message? (J/N)");
                        String keuze = scanner.nextLine();
                        if (keuze.equalsIgnoreCase("j")) {
                            System.out.print("Enter Message: ");
                            message = scanner.nextLine();
                            String highlightedMessage = "*** " + message;
                            System.out.println("Highlighted Message: " + highlightedMessage);

                            //print all chats
                            for (Chat chat : QueryChats.getAllChats()) {
                                System.out.printf("Chat ID: %d Chat name: %s%n", QueryChats.getChatID(chat), chat.getChatName());
                            }
                            System.out.print("Choose chatID: ");
                            int chatID = scanner.nextInt();
                            Chat selectedChat = QueryChats.getSingleChat(chatID);
                            //error
                            if (selectedChat == null) {
                                System.out.println("Chat not found. Please try again.");
                                return;
                            } else {
                                MessageDAO.insertMessage(new Message(selectedUser, message, true, selectedChat)); //create message object and add it to database
                                scanner.nextLine();
                            }
                            return;
                        }
                    }
                    // enter message
                    System.out.print("Enter Message: ");
                    message = scanner.nextLine();
                    //select a chat
                    for (Chat chat : QueryChats.getAllChats()) {
                        System.out.printf("Chat ID: %d Chat name: %s%n", QueryChats.getChatID(chat), chat.getChatName());
                    }
                    System.out.print("Choose chatID: ");
                    int chatID = scanner.nextInt();
                    Chat selectedChat = QueryChats.getSingleChat(chatID);

                    if (selectedChat == null) {
                        System.out.println("Chat not found. Please try again.");
                    } else {
                        MessageDAO.insertMessage(new Message(selectedUser, message, false, selectedChat)); //create message object and add it to database
                        scanner.nextLine();
                    }

                }
                case 3 -> {
                    //print all chats
                    for (Chat chat : QueryChats.getAllChats()) {
                        System.out.printf("Chat ID: %d Chat name: %s%n", QueryChats.getChatID(chat), chat.getChatName());
                    }
                    System.out.println("Enter '0' to view highlighted messages.");
                    System.out.print("Choose 'chatID' or enter '0': ");
                    int option = scanner.nextInt();
                    scanner.nextLine();
                    //print ALL highlighted messages if option = 0
                    if (option == 0) {
                        Message.viewHighlighted();
                    } else {
                        //selected chat
                        Chat chat = QueryChats.getSingleChat(option);
                        //option to show highlighted messages per chat
                        if (chat != null) {
                            System.out.println("Do you only want to see the important messages in this chat? (J/N)");
                            String keuze = scanner.nextLine();
                            if (keuze.equalsIgnoreCase("J")) {
                                chat.setChatMessages(QueryMessages.getMessagesForChat(option));
                                chat.viewHighlightedMessages();
                            } else {
                                chat.setChatMessages(QueryMessages.getMessagesForChat(option));
                                chat.viewChatMessages();
                            }
                        } else { //error
                            System.out.println("There is no chat with chatID: " + option);
                        }
                    }
                }
                case 4 -> {
                    //refers scrum management menu
                    scrumManagementMenu();
                }
                case 5 -> {
                    //closes application
                    System.out.println("Exiting...");
                    scanner.close();
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