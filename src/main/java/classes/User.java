package classes;

public class User {

    private String username;
    private boolean isscrummaster;

    public User(String username, boolean isscrummaster) {
        this.username = username;
        this.isscrummaster = isscrummaster;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean getIsScrumMaster() {
        return this.isscrummaster;
    }

    public void setIsScrumMaster(boolean isscrummaster) {
        this.isscrummaster = isscrummaster;
    }
}

