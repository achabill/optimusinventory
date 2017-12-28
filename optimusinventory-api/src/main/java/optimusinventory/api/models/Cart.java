package optimusinventory.api.models;

import java.util.List;

public class Cart {
    List<CartItem> cartItems;
    double total;

    public Cart() {
    }

    public Cart(List<CartItem> cartItems) {
        this.cartItems = cartItems;
        this.total = 0;
        for (int i = 0; i < cartItems.size(); i++) {
            this.total += cartItems.get(i).getTotal();
        }
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public double getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
