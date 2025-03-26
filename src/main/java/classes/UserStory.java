package classes;

import java.util.ArrayList ;
public class UserStory implements Linker {
    private String usName ;
    private String usDescription ;
    private ArrayList<Task>  tasks ;

    public UserStory(String usName, String usDescription) {
        this.usName = usName;
        this.usDescription = usDescription;
        this.tasks = new ArrayList<>();
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void setUsName(String usName) {
        this.usName = usName;
    }

    public String getUsName() {
        return this.usName;
    }

    public void setUsDescription(String usDescription) {
        this.usDescription = usDescription;
    }

    public String getUsDescription() {
        return this.usDescription;
    }

    @Override
    public void linkMessage() {

    }
}
