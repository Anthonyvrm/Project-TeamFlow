package classes;

public class Epic implements Linker {
    private String epicName;
    private String epicDescription;
    private UserStory userStory;

    public Epic(String epicName, String epicDescription, UserStory userStory) {
        this.epicName = epicName;
        this.epicDescription = epicDescription;
        this.userStory = userStory;
    }
    public void setEpicName(String epicName) {
        this.epicName = epicName;
    }

    public String getEpicName() {
        return this.epicName;
    }

    public void setEpicDescription(String epicDescription) {
        this.epicDescription = epicDescription;
    }

    public String getEpicDescription() {
        return this.epicDescription;
    }

    public void setUserStory(UserStory userStory) {
        this.userStory = userStory;
    }

    public UserStory getUserStory() {
        return this.userStory;
    }

    @Override
    public void linkMessage() {

    }
}
