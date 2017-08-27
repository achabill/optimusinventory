package optimusinventory.api.models;

import org.springframework.data.annotation.Id;

public class StockItemLog {
    @Id
    private String id;
    private User user;
    private StockItem stockItem;
    private String date;
    private LogAction logAction;

    public StockItemLog(User user, StockItem stockItem, String date, LogAction logAction) {
        this.user = user;
        this.stockItem = stockItem;
        this.date = date;
        this.logAction = logAction;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LogAction getStockItemLogAction() {
        return logAction;
    }

    public void setStockItemLogAction(LogAction logAction) {
        this.logAction = logAction;
    }
}
