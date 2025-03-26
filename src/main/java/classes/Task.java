package classes;

import java.util.ArrayList;

public class Task {
    private UserStory userStory;
    private String taskDescription;
    private ArrayList <Message> taskMessages;

    public Task(UserStory userStory, String taskDescription) {
        this.userStory = userStory;
        this.taskDescription = taskDescription;
        this.taskMessages = new ArrayList<>();
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public void setTaskMessages(ArrayList<Message> taskMessages) {
        this.taskMessages = taskMessages;
    }

    public void setUserStory(UserStory userStory) {
        this.userStory = userStory;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public ArrayList<Message> getTaskMessages() {
        return taskMessages;
    }

    public UserStory getUserStory() {
        return userStory;
    }

    public void linkMessage () {}
}
