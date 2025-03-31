package classes;

import dao.MessageDAO;

public class Message {

    private User user;
    private String message;
    private boolean isHighlighted;
    private Chat chat;

    public Message(User user, String message, boolean isHighlighted, int userID, Chat chat) {
        this.user = user;
        this.message = message;
        this.isHighlighted = isHighlighted;
        this.chat = chat;
    }

    public User getUser(User user) {
        return this.user;
    }

    public void setUser() {
        this.user = user;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage() {
        this.message = message;
    }

    public boolean getIsHiglighted(boolean ishighlighted) {
        return this.isHighlighted;
    }

    public void setIshighlighted() {
        this.isHighlighted = isHighlighted;
    }


    public Chat getChat(Chat chat) {
        return this.chat;
    }

    public void setChat() {
        this.chat = chat;
    }

    public void markMessageAsImportant(Message message) {
        //dev
    }

    public void sendMessageToChat() {
        MessageDAO.insertMessage(this.user, this.message, this.isHighlighted, this.chat);
    }

}