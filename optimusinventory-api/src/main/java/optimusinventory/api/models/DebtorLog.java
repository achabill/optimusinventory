package optimusinventory.api.models;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

public class DebtorLog {
    @Id
    private String id;
    @NotNull
    private User user;
    @NotNull
    private Debtor Debtor;
    @NotNull
    private String date;
    @NotNull
    private LogAction logAction;

    public DebtorLog(User user, Debtor debtor, String date, LogAction logAction) {
        this.user = user;
        Debtor = debtor;
        this.date = date;
        this.logAction = logAction;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public optimusinventory.api.models.Debtor getDebtor() {
        return Debtor;
    }

    public void setDebtor(optimusinventory.api.models.Debtor debtor) {
        Debtor = debtor;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public LogAction getDebtorLogAction() {
        return logAction;
    }

    public void setDebtorLogAction(LogAction logAction) {
        this.logAction = logAction;
    }
}
