package classes;

import java.util.ArrayList;

public class Task implements Linker {
    private UserStory userStory;
    private String taskDescription;
    private int taskID;
    private ArrayList <Message> taskMessages;

    public Task(UserStory userStory, String taskDescription, int taskID) {
        this.userStory = userStory;
        this.taskDescription = taskDescription;
        this.taskID = taskID;
        this.taskMessages = new ArrayList<>();
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public void setTaskMessages(ArrayList<Message> taskMessages) {
        this.taskMessages = taskMessages;
    }

    public void setTaskID(int TaskID) { this.taskID = taskID; }

    public void setUserStory(UserStory userStory) {
        this.userStory = userStory;
    }


    public String getTaskDescription() {
        return taskDescription;
    }

    public ArrayList<Message> getTaskMessages() {
        return taskMessages;
    }

    public int getTaskID() { return taskID; }

    public UserStory getUserStory() {
        return userStory;
    }

    @Override
    public void linkMessage () {}
}
