import java.util.Date;

public class Medication implements Identifiable {
    private final String id;
    private String name;
    private int quantity;
    private Date expiryDate;

    // Constructor
    public Medication(String id, String name, int quantity, Date expiryDate) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
    }


    // Getters
    @Override
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    // Setters - Needed for editing functionality
    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    // Expiration check
    public boolean isExpired() {
        return expiryDate.before(new Date());
    }

    // Restock
    public void restock(int amount) {
        this.quantity += amount;
    }

    @Override
    public String toString() {
        return "Medication{id='" + id + "', name='" + name + "', quantity=" + quantity +
               ", expiryDate=" + expiryDate + "}";
    }
}
