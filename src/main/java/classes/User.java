package classes;

public class User {

    private String username;
    private boolean isScrumMaster;
    private int userID;

    public User(String username, boolean isScrumMaster, int userID) {
        this.username = username;
        this.isScrumMaster = isScrumMaster;
        this.userID = userID;
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

    public int getUserID() { return this.userID;}
    public void setUserID(int userID) { this.userID = userID;}
}

