package optimusinventory.api.models;

import javax.validation.constraints.NotNull;

public class CartItem {
    @NotNull
    private StockItem stockItem;
    @NotNull
    private int quantity;
    @NotNull
    private double total;

    public CartItem() {
    }

    public CartItem(StockItem stockItem, int quantity, double total) {
        this.stockItem = stockItem;
        this.quantity = quantity;
        this.total = total;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
