package optimusinventory.api.models;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * A machine used in the office.
 * This can be a photocopier, printer, scanner, etc..
 */
public class MachineItem {
    @Id
    private String id;
    private Date date;
    private int quantity;
    private int unitPrice;
    private int quality;
    private int total;
    private Machine machine;

    public MachineItem() {
    }

    public MachineItem(Date date, int quantity, int unitPrice, int quality, int total, Machine machine) {
        this.date = date;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.quality = quality;
        this.total = total;
        this.machine = machine;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }
}
