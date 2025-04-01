package classes;

import java.util.ArrayList;

public class Chat {
    private String chatName;
    private ArrayList<Message> chatMessages;
    private Chat chat;

    public Chat(String chatName, Chat chat) {
        this.chatName = chatName;
        this.chatMessages = new ArrayList<>();
        this.chat = chat;

    }

    public String getChatName() {
        return this.chatName;
    }

    public Chat getChat() {
        return this.chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
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
            if (message.getIsHighlighted()) {
                System.out.print("*");
            }
            System.out.print(message.getUser() + ": ");
            System.out.println(message.getMessage());
        }
    }

}