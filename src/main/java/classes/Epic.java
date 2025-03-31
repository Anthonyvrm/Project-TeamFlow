package classes;

public class Epic implements Linker {
    private String epicName;
    private String epicDescription;
    private UserStory userStory;
    private int epicID;
    private Sprint sprint;

    public Epic(String epicName, String epicDescription, UserStory userStory, int epicID, Sprint sprint) {
        this.epicName = epicName;
        this.epicDescription = epicDescription;
        this.userStory = userStory;
        this.epicID = epicID;
        this.sprint = sprint;
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

    public void setEpicID(int epicID) {
        this.epicID = epicID;
    }
    public int getEpicID() {
        return this.epicID;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }
    public Sprint getSprint(){
        return this.sprint;
    }

    @Override
    public void linkMessage() {
    }
}
