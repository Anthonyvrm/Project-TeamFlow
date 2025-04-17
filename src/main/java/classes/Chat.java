package classes;

import queries.QueryChats;

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

    // view the chat messages
    public void viewChatMessages() {
        if (chatMessages != null) {
            for (Message message : chatMessages) {
                if (message.getIsHighlighted()) {
                    System.out.print("*");
                }
                System.out.printf("%s: %s%n", message.getUser().getUsername(), message.getMessage());
            }
        } else {
            System.out.println("There are no messages in this chat...");
        }
    }

    // shows the highlighted messages from a specific chat
    public void viewHighlightedMessages() {
        boolean foundHighlighted = false;
        if(chatMessages != null) {

            System.out.println("These are the important messages in this chat: " + this.getChatName());
            for (Message message : this.getChatMessages()) {
                if (message.getIsHighlighted()) {
                    System.out.printf("* %s: %s\n", message.getUser().getUsername(), message.getMessage());
                    foundHighlighted = true;
                }
            }
        }
        if (!foundHighlighted) {
            System.out.println("There are no highlighted messages in this chat...");
        }
    }
}
