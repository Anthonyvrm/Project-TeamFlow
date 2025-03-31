package classes;

public class Message {

    private User user;
    private String message;
    private boolean isHighlighted;
    private int userID;
    private int messageID;
    private int chatID;

    public Message(User user, String message, boolean isHighlighted, int userID, int messageID, int chatID) {
        this.user = user;
        this.message = message;
        this.isHighlighted = isHighlighted;
        this.userID = userID;
        this.messageID = messageID;
        this.chatID = chatID;
    }

    public User getUser(User user) {
        return this.user;
    }

    public void setUser() {
        this.user = user;
    }

    public String getMessage(String message) {
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

    public int getUserID(int userID) {
        return this.userID;
    }

    public void setUserID() {
        this.userID = userID;
    }

    public int getChatID(int chatID) {
        return this.chatID;
    }

    public void setChatID() {
        this.chatID = chatID;
    }

    public int getMessageID(int messageID) {
        return this.messageID;
    }

    public void setMessageID() {
        this.messageID = messageID;
    }

    public void markMessageAsImportant(Message message) {
        //dev
    }

    public void sendMessageToChat() {
        //dev
    }

}




