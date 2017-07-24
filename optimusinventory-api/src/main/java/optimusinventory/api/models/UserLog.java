package optimusinventory.api.models;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class UserLog {
    @Id
    private String id;
    private User user;
    private User who;
    private UserLogAction userLogAction;
    private Date date;


    public UserLog(User user, User who, UserLogAction userLogAction, Date date) {
        this.user = user;
        this.who = who;
        this.userLogAction = userLogAction;
        this.date = date;
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

    public UserLogAction getUserLogAction() {
        return userLogAction;
    }

    public void setUserLogAction(UserLogAction userLogAction) {
        this.userLogAction = userLogAction;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getWho() {
        return who;
    }

    public void setWho(User who) {
        this.who = who;
    }
}
