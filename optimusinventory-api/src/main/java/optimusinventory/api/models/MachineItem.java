package optimusinventory.api.models;

import org.springframework.data.annotation.Id;

/**
 * A machine used in the office.
 * This can be a photocopier, printer, scanner, etc..
 */
public class MachineItem {
    @Id
    private String id;
    private String date;
    private int quantity;
    private double unitPrice;
    private MachineQuality quality;
    private double total;
    private Machine machine;

    public MachineItem() {
    }

    public MachineItem(String date, int quantity, double unitPrice, MachineQuality quality, double total,
            Machine machine) {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public MachineQuality getQuality() {
        return quality;
    }

    public void setQuality(MachineQuality quality) {
        this.quality = quality;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }
}
