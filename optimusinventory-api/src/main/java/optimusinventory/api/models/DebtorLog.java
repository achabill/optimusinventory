package optimusinventory.api.models;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class DebtorLog {
    @Id
    private String id;
    private User user;
    private Debtor Debtor;
    private Date date;
    private DebtorLogAction debtorLogAction;

    public DebtorLog(User user, Debtor debtor, Date date, DebtorLogAction debtorLogAction) {
        this.user = user;
        Debtor = debtor;
        this.date = date;
        this.debtorLogAction = debtorLogAction;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public DebtorLogAction getDebtorLogAction() {
        return debtorLogAction;
    }

    public void setDebtorLogAction(DebtorLogAction debtorLogAction) {
        this.debtorLogAction = debtorLogAction;
    }
}
