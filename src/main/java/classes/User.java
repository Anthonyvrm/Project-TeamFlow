package classes;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String username;
    private boolean isScrumMaster;
    private ArrayList<Message> messages;

    public User(String username, boolean isScrumMaster) {
        this.username = username;
        this.isScrumMaster = isScrumMaster;
        this.messages = new ArrayList<>();
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean getIsScrumMaster() {
        return this.isScrumMaster;
    }

    public void setIsScrumMaster(boolean isScrumMaster) {
        this.isScrumMaster = isScrumMaster;
    }

    // Add message to the user's message list
    public void addMessage(Message message) {
        messages.add(message);
        message.setUser(this);
    }
}

