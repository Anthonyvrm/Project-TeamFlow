package classes;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Sprint {
    private int sprintInt;
    private Chat chat;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    public Sprint(int sprintInt, Chat chat) {
        this.sprintInt = sprintInt;
        this.chat = chat;
        this.startDate = LocalDateTime.now();
        this.endDate = startDate.plusDays(14);


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

    public Chat getChat() {
        return this.chat;
    }

    public void setSprintInt(int sprintInt) {
        this.sprintInt = sprintInt;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }
}
