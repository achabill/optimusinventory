package optimusinventory.api.models;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class Sale {
    @Id
    private String id;
    @NotNull
    private Cart cart;
    @NotNull
    private String date; //use date as string for now.

    public Sale() {
    }

    public Sale(Cart cart) {
        this.cart = cart;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
