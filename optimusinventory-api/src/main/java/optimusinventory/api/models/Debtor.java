package optimusinventory.api.models;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

/**
 * Created by Acha Bill on 7/17/2017.
 */
public class Debtor {
    @Id
    private String id;
    @NotNull
    private String firstName;
    private String lastName;
    @NotNull
    private int amount;
    private boolean isActive;

    public Debtor() {
    }

    public Debtor(String firstName, String lastName, int amount, boolean isActive) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.amount = amount;
        this.isActive = isActive;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
