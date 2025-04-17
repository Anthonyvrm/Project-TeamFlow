package classes;

public class Task extends BacklogItem implements Linker {
    private UserStory userStory;

    public Task(String name, String description, Chat chat, UserStory userStory) {
        super(name, description, chat); // Oproep naar de constructor van BacklogItem
        this.userStory = userStory;
    }

    public void setUserStory(UserStory userStory) {
        this.userStory = userStory;
    }

    public UserStory getUserStory() {
        return userStory;
    }

    @Override
    public void linkMessage() {
        // Implementeer hier de koppellogica indien nodig.
    }
}
