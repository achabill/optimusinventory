package optimusinventory.api.models;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

public class StockItem {
    @Id
    private String id;
    @NotNull
    private String name;
    private String category;
    private String description;
    private int quantity;
    private int costPrice;
    private int sellingPrice;

    public StockItem() {
    }

    public StockItem(String name, String category, String description, int quantity, int costPrice, int sellingPrice) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.quantity = quantity;
        this.costPrice = costPrice;
        this.sellingPrice = sellingPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(int costPrice) {
        this.costPrice = costPrice;
    }

    public int getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(int sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    @Override
    public boolean equals(Object obj) {
        StockItem item = (StockItem)obj;
        return item.getName() == this.getName() && item.getCategory() == this.getCategory();
    }
}
