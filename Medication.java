import java.time.LocalDate;

public class Medication {

    // Instance variables
    private final String id;
    private String name;
    private String dose;
    private int quantityInStock;
    private LocalDate expiryDate;

    // Constructor
    public Medication(String id, String name, String dose, int quantityInStock, LocalDate expiryDate) {
        this.id = id;
        this.name = name;
        this.dose = dose;
        this.quantityInStock = quantityInStock;
        this.expiryDate = expiryDate;
    }

    // Getters
    public String getId() { 
        return id; 
    }
    public String getName() { 
        return name; 
    }
    public String getDose() { 
        return dose; 
    }
    public int getQuantityInStock() { 
        return quantityInStock; 
    }
    public LocalDate getExpiryDate() { 
        return expiryDate; 
    }

    // Setters
    public void setName(String name) { 
        this.name = name; 
    }
    public void setDose(String dose) { 
        this.dose = dose; 
    }
    public void setQuantityInStock(int quantityInStock) { 
        this.quantityInStock = quantityInStock;
    }
    public void setExpiryDate(LocalDate expiryDate) { 
        this.expiryDate = expiryDate; 
    }

    // Check if expired
    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }

    // Restock medication
    public void restock(int amount) {
        this.quantityInStock += amount;
    }
}
