package optimusinventory.api.models;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class DebtorDebit {
    @NotNull
    private Date date;
    @NotNull
    private double amount;
    @NotNull
    Debtor debtor;

    public DebtorDebit() {
    }

    public DebtorDebit(Date date, double amount, Debtor debtor) {
        this.date = date;
        this.debtor = debtor;
        this.amount = amount;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return this.date;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setDebtor(Debtor debtor) {
        this.debtor = debtor;
    }

    public Debtor getDebtor() {
        return this.debtor;
    }
}