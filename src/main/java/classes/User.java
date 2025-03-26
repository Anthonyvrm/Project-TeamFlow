package classes;

public class User {

    private String username;
    private boolean isScrumMaster;

    public User(String username, boolean isScrumMaster) {
        this.username = username;
        this.isScrumMaster = isScrumMaster;
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
}

