package classes;

public class Task implements Linker {
    private UserStory userStory;
    private String taskDescription;
    private Chat taskChat;

    public Task(UserStory userStory, String taskDescription, Chat taskChat) {
        this.userStory = userStory;
        this.taskDescription = taskDescription;
        this.taskChat = taskChat;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public void setTaskChat(Chat taskChat) {
        this.taskChat = taskChat;
    }

    public void setUserStory(UserStory userStory) {
        this.userStory = userStory;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public Chat getTaskChat() {
        return taskChat;
    }

    public UserStory getUserStory() {
        return userStory;
    }

    @Override
    public void linkMessage() {
        // Implementeer hier de koppellogica indien nodig.
    }
}
