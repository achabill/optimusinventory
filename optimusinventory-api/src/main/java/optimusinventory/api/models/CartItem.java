package optimusinventory.api.models;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

/**
 * Created by Acha Bill on 7/17/2017.
 */
public class CartItem {
    @Id
    private String id;
    @NotNull
    private StockItem stockItem;
    @NotNull
    private int quantity;
    private int total;

    public CartItem() {
    }

    public CartItem(String id, StockItem stockItem, int quantity, int total) {
        this.id = id;
        this.stockItem = stockItem;
        this.quantity = quantity;
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public StockItem getStockItem() {
        return stockItem;
    }

    public void setStockItem(StockItem stockItem) {
        this.stockItem = stockItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
