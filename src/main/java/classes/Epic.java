package classes;

import java.util.ArrayList;
public class Epic extends BacklogItem implements Linker {

    private ArrayList<UserStory> userStories;
    private Sprint sprint;

    public Epic(String name, String description, Sprint sprint, Chat chat) {
        super(name, description, chat); // Call to constructor from BacklogItem
        this.sprint = sprint;
        this.userStories = new ArrayList<>();
    }

    // Use inherited methods for name, description, and chat
    public ArrayList<UserStory> getUserStories() {
        return userStories;
    }


    // Adds an Userstory to the epic
    public void addUserStory(UserStory userStory) {
        this.userStories.add(userStory);
        userStory.setEpic(this);
    }

    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }

    @Override
    public void linkMessage() {
        // Implementation if specific logic is needed to connect messages.
    }
}
