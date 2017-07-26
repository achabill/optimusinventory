package optimusinventory.api.models;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class User {
    @Id
    private String id;
    @NotNull
    private String username;
    @NotNull
    private String password;
    private List<Privilege> privileges;
    private String phoneNumber;
    private String email;
    private String firstName;
    private String lastName;
    private Date createdOn;

    public User() {
    }

    public User(String username, String password, List<Privilege> privileges, String phoneNumber, String email, String firstName, String lastName, Date createdOn) {
        this.username = username;
        this.password = password;
        this.privileges = privileges;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.createdOn = createdOn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<Privilege> privileges) {
        this.privileges = privileges;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}
