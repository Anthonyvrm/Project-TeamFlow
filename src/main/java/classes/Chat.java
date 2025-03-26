package classes;

import java.util.ArrayList;

public class Chat {
    private String chatName;
    private ArrayList<String> chatMessages; ;
    private int sprintID;

    public Chat(String chatName, int sprintID, ArrayList<String> chatMessages) {
        this.chatName = chatName;
        this.sprintID = sprintID;
        this.chatMessages = chatMessages;
    }

    public String getChatName() {
        return this.chatName;
    }

    public int getSprintID() {
        return this.sprintID;
    }
    public ArrayList<String> getChatMessages() {
        return this.chatMessages;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public void setSprintID(int sprintID) {
        this.sprintID = sprintID;
    }

    public void setChatMessages(ArrayList<String> chatMessages) {
        this.chatMessages = chatMessages;
    }
}
