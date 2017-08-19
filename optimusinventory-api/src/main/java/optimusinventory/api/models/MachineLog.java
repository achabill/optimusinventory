package optimusinventory.api.models;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class MachineLog {
    @Id
    private String id;
    private Machine machine;
    private User user;
    private Date date;
    private LogAction logAction;

    public MachineLog() {
    }

    public MachineLog(Machine machine, User user, Date date, LogAction logAction) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LogAction getLogAction() {
        return logAction;
    }

    public void setLogAction(LogAction logAction) {
        this.logAction = logAction;
    }
}
