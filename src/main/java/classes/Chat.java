package classes;

import queries.QueryChats;
import queries.QueryMessages;

import java.util.ArrayList;
import java.util.Objects;

public class Chat {
    private String chatName;
    private static ArrayList<Message> chatMessages;

    public Chat(String chatName, Chat chat) {
        this.chatName = chatName;
        chatMessages = new ArrayList<>();
    }

    public Chat(String retrievedChatName) {
        this.chatName = retrievedChatName;
    }

    public String getChatName() {
        return this.chatName;
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
    public static void viewChatMessages(int chatID) {// Print alle verstuurde berichten uit 1 chat onder elkaar, met * voor het bericht als deze is gehighlight.

        for (Message message : Objects.requireNonNull(QueryMessages.getChatMessageQuery(chatID))) {
            if (message.getIsHighlighted()) {
                System.out.print("*");
            }
            System.out.print(message.getUser() + ": ");
            System.out.println(message.getMessage());
        }
    }

}