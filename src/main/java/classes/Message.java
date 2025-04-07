package classes;

import dao.MessageDAO;

public class Message {
    private User user;
    private String message;
    private boolean isHighlighted;
    private Chat chat;

    public Message(User user, String message, boolean isHighlighted, Chat chat) {
        this.user = user;
        this.message = message;
        this.isHighlighted = isHighlighted;
        this.chat = chat;
    }

    public User getUser() {
        return this.user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return this.message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getIsHighlighted() {
        return this.isHighlighted;
    }
    public void setIsHighlighted(boolean isHighlighted) {
        this.isHighlighted = isHighlighted;
    }

    public Chat getChat() {
        return this.chat;
    }
    public void setChat(Chat chat) {
        this.chat = chat;
    }


    public void markMessageAsImportant(Message message) {
        if(getUser().getIsScrumMaster()){
            message.setIsHighlighted(!message.getIsHighlighted());
            System.out.println("The Message is (de)highlighted!");
        } else {
            System.out.println("You are not allowed to highlight a message.");
        }
    }


    public void sendMessageToChat() {
        MessageDAO.insertMessage(this.user, this.message, this.isHighlighted, this.chat);
    }
}
