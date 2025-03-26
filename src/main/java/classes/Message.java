package classes;

public class Message {

    private User user;
    private String message;
    private boolean isHighlighted;

    public Message(User user, String message, boolean isHighlighted) {
        this.user = user;
        this.message = message;
        this.isHighlighted = isHighlighted;
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

    public boolean getIsHighlighted(boolean isHighlighted) {
        return this.isHighlighted;
    }

    public void setIsHighlighted() {
        this.isHighlighted = isHighlighted;
    }

    public void markMessageAsImportant(Message message) {
        //dev
    }

    public void sendMessageToChat() {
        //dev
    }
}




