package optimusinventory.api.models;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class StockItemLog {
    @Id
    private String id;
    private User user;
    private StockItem stockItem;
    private Date date;
    private StockItemLogAction stockItemLogAction;

    public StockItemLog(User user, StockItem stockItem, Date date, StockItemLogAction stockItemLogAction) {
        this.user = user;
        this.stockItem = stockItem;
        this.date = date;
        this.stockItemLogAction = stockItemLogAction;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public StockItem getStockItem() {
        return stockItem;
    }

    public void setStockItem(StockItem stockItem) {
        this.stockItem = stockItem;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public StockItemLogAction getStockItemLogAction() {
        return stockItemLogAction;
    }

    public void setStockItemLogAction(StockItemLogAction stockItemLogAction) {
        this.stockItemLogAction = stockItemLogAction;
    }
}
