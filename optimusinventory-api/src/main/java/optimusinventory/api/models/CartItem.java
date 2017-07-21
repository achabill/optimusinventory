package optimusinventory.api.models;

public class CartItem {
    private StockItem stockItem;
    private int quantity;
    private int total;

    public CartItem() {
    }

    public CartItem(StockItem stockItem, int quantity, int total) {
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
