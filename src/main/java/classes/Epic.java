package classes;

import java.util.ArrayList;
public class Epic extends BacklogItem implements Linker {

    private ArrayList<UserStory> userStories;
    private Sprint sprint;

    public Epic(String name, String description, Sprint sprint, Chat chat) {
        super(name, description, chat); // Oproep naar de constructor van BacklogItem
        this.sprint = sprint;
        this.userStories = new ArrayList<>();
    }

    // Gebruik de geërfde methoden voor name, description, and chat
    public ArrayList<UserStory> getUserStories() {
        return userStories;
    }


    // Adds a Userstory to the epic
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
        // Implementatie indien er specifieke logica nodig is om berichten te koppelen.
    }
}
