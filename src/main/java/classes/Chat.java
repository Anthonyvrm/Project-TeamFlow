package classes;

import java.util.ArrayList;

public class Chat {
    private String chatName;
    private ArrayList<Message> chatMessages;

    public Chat(String chatName) {
        this.chatName = chatName;
        this.chatMessages = new ArrayList<>();
    }

    public String getChatName() {
        return this.chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public ArrayList<Message> getChatMessages() {
        return this.chatMessages;
    }

    public void setChatMessages(ArrayList<Message> chatMessages) {
        this.chatMessages = chatMessages;
    }


    public void addMessage(Message message) {
        chatMessages.add(message);
        message.setChat(this);
    }


    public void viewChatMessages() {
        for (Message message : chatMessages) {
            if (message.getIsHighlighted()) {
                System.out.print("*");
            }
            System.out.print(message.getUser().getUsername() + ": ");
            System.out.println(message.getMessage());
        }
    }
}
