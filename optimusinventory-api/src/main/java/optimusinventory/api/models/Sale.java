package optimusinventory.api.models;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

/**
 * Created by Acha Bill on 7/17/2017.
 */
public class Sale {
    @Id
    private String id;
    @NotNull
    private Cart cart;

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
}
