package classes;

import java.util.ArrayList;

public class Chat {
    private String chatName;
    private ArrayList<Message> chatMessages; ;
    private int sprintID;

    public Chat(String chatName, int sprintID) {
        this.chatName = chatName;
        this.sprintID = sprintID;
        chatMessages = new ArrayList<>();
    }

    public String getChatName() {
        return this.chatName;
    }

    public int getSprintID() {
        return this.sprintID;
    }
    public ArrayList<Message> getChatMessages() {
        return this.chatMessages;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public void setSprintID(int sprintID) {
        this.sprintID = sprintID;
    }

    public void setChatMessages(ArrayList<Message> chatMessages) {
        this.chatMessages = chatMessages;
    }
    public void viewChatMessages() {

    }


}