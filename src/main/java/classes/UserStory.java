package classes;

import java.util.ArrayList;

public class UserStory extends BacklogItem implements Linker {
    private ArrayList<Task> tasks;
    private Epic epic;

    public UserStory(String name, String description, Chat chat, Epic epic) {
        super(name, description, chat); // Oproep naar de constructor van BacklogItem
        this.epic = epic;
        this.tasks = new ArrayList<>();
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    // Adds a task to the user story
    public void addTask(Task task) {
        this.tasks.add(task);
        task.setUserStory(this);
    }

    public Epic getEpic() {
        return epic;
    }

    public void setEpic(Epic epic) {
        this.epic = epic;
    }

    @Override
    public void linkMessage() {
        // Implementeer hier de koppellogica indien nodig.
    }
}
