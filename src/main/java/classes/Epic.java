package classes;

import java.util.ArrayList;
import java.util.List;

public class Epic implements Linker {

    private String epicName;
    private String epicDescription;
    private ArrayList<UserStory> userStories;
    private Sprint sprint;
    private Chat epicChat;

    public Epic(String epicName, String epicDescription, Sprint sprint, Chat epicChat) {
        this.epicName = epicName;
        this.epicDescription = epicDescription;
        this.sprint = sprint;
        this.epicChat = epicChat;
        this.userStories = new ArrayList<>();

    }

    public String getEpicName() {
        return epicName;
    }

    public void setEpicName(String epicName) {
        this.epicName = epicName;
    }

    public String getEpicDescription() {
        return epicDescription;
    }

    public void setEpicDescription(String epicDescription) {
        this.epicDescription = epicDescription;
    }

    public ArrayList<UserStory> getUserStories() {
        return userStories;
    }

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

    public Chat getEpicChat() {
        return epicChat;
    }

    public void setEpicChat(Chat epicChat) {
        this.epicChat = epicChat;
    }

    @Override
    public void linkMessage() {
        // Implementatie indien er specifieke logica nodig is om berichten te koppelen.
    }
}
