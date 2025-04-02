package classes;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Sprint {
    private int sprintInt;
    private int sprintID;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Chat chat;

    public Sprint(int sprintInt, int sprintID, LocalDateTime startDate, LocalDateTime endDate, Chat chat) {
        this.sprintInt = sprintInt;
        this.sprintID = sprintID;
        this.startDate = LocalDateTime.now();
        this.endDate = startDate.plusDays(14);
        this.chat = chat;
    }

    public LocalDateTime getEndDate() {
        return this.endDate;
    }

    public LocalDateTime getStartDate() {
        return this.startDate;
    }

    public int getSprintInt() {
        return this.sprintInt;
    }

    public int getSprintID() {
        return this.sprintID;
    }

    public void setSprintInt(int sprintInt) {
        this.sprintInt = sprintInt;
    }

    public void setSprintID(int sprintID) {
        this.sprintID = sprintID;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }
}
