package optimusinventory.api.models;

import org.springframework.data.annotation.Id;

public class MachineLog {
    @Id
    private String id;
    private Machine machine;
    private User user;
    private String date;
    private LogAction logAction;

    public MachineLog() {
    }

    public MachineLog(Machine machine, User user, String date, LogAction logAction) {
        this.machine = machine;
        this.user = user;
        this.date = date;
        this.logAction = logAction;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public LogAction getLogAction() {
        return logAction;
    }

    public void setLogAction(LogAction logAction) {
        this.logAction = logAction;
    }
}
