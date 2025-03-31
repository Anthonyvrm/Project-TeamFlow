package classes;

import java.util.ArrayList;

public class Chat {
    private String chatName;
    private ArrayList<Message> chatMessages;
    private int chatID;

    public Chat(String chatName, int chatID, ArrayList<Message> chatMessages) {
        this.chatName = chatName;
        this.chatMessages = new ArrayList<>();
        this.chatID = chatID;

    }

    public String getChatName() {
        return this.chatName;
    }

    public int getChatID() {
        return this.chatID;
    }

    public void setChatID(int chatID) {
        this.chatID = chatID;
    }


    public ArrayList<Message> getChatMessages() {
        return this.chatMessages;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }



    public void setChatMessages(ArrayList<Message> chatMessages) {
        this.chatMessages = chatMessages;
    }
    public void viewChatMessages() {
        for (Message message : chatMessages) {
            System.out.println(message.getMessage());
        }
    }

}