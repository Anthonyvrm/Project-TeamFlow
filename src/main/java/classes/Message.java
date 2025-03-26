package classes;

public class Message {

    private User user;
    private String message;
    private boolean ishighlighted;

    public Message(User user, String message, boolean ishighlighted) {
        this.user = user;
        this.message = message;
        this.ishighlighted = ishighlighted;
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
        return this.ishighlighted;
    }

    public void setIshighlighted() {
        this.ishighlighted = ishighlighted;
    }

    public void markMessageAsImportant(Message message) {
        //dev
    }

    public void sendMessageToChat() {
        //dev
    }
}




