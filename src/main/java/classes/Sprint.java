package classes;

import java.time.LocalDateTime;

public class Sprint {
    private int sprintInt;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Chat sprintChat;


    public Sprint(int sprintInt, LocalDateTime startDate, LocalDateTime endDate, Chat sprintChat) {
        this.sprintInt = sprintInt;
        this.startDate = startDate;
        this.endDate = startDate.plusDays(14);
        this.sprintChat = sprintChat;
    }

    public int getSprintInt() {
        return sprintInt;
    }

    public void setSprintInt(int sprintInt) {
        this.sprintInt = sprintInt;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
        this.endDate = startDate.plusDays(14);
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Chat getSprintChat() {
        return sprintChat;
    }

    public void setSprintChat(Chat sprintChat) {
        this.sprintChat = sprintChat;
    }
}
