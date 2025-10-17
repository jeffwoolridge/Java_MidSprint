import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

public class Medication implements Identifiable {

    private final String id;
    private final String name;
    private final String dose;
    private int quantityInStock;
    private final LocalDate expiryDate;

    // Constructor with random expiry date
    public Medication(String id, String name, String dose, int quantityInStock) {
        this.id = id;
        this.name = name;
        this.dose = dose;
        this.quantityInStock = quantityInStock;
        this.expiryDate = generateRandomExpiryDate();
    }

    // Getters
    @Override
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

    // Check if expired
    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }

    // Restock method
    public void restock(int amount) {
        this.quantityInStock += amount;
    }

    // Random expiry date between 10 years in the past or future
    private static LocalDate generateRandomExpiryDate() {
        LocalDate start = LocalDate.now().minusYears(100);
        LocalDate end = LocalDate.now().plusYears(100);
        long startEpochDay = start.toEpochDay();
        long endEpochDay = end.toEpochDay();
        long randomEpochDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay + 1);
        return LocalDate.ofEpochDay(randomEpochDay);
    }


}



