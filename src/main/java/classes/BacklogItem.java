package classes;

public abstract class BacklogItem {
    protected String name;
    protected String description;
    protected Chat chat;

    public BacklogItem(String name, String description, Chat chat) {
        this.name = name;
        this.description = description;
        this.chat = chat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }
}

