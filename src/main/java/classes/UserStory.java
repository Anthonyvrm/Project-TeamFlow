package classes;

import java.util.ArrayList;

public class UserStory implements Linker {
    private String usName;
    private String usDescription;
    private ArrayList<Task> tasks;
    private Chat userStoryChat;
    private Epic epic;


    public UserStory(String usName, String usDescription, Chat userStoryChat, Epic epic) {
        this.usName = usName;
        this.usDescription = usDescription;
        this.userStoryChat = userStoryChat;
        this.epic = epic;
        this.tasks = new ArrayList<>();
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        task.setUserStory(this);
    }

    public void setUsName(String usName) {
        this.usName = usName;
    }

    public String getUsName() {
        return this.usName;
    }

    public void setUsDescription(String usDescription) {
        this.usDescription = usDescription;
    }

    public String getUsDescription() {
        return this.usDescription;
    }

    public Chat getUserStoryChat() {
        return this.userStoryChat;
    }

    public void setUserStoryChat(Chat userStoryChat) {
        this.userStoryChat = userStoryChat;
    }

    public Epic getEpic() {
        return epic;
    }

    public void setEpic(Epic epic) {
        this.epic = epic;
    }

    @Override
    public void linkMessage() {
    }
}
