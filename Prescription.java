import java.time.LocalDate;

public class Prescription {

    // Instance variables
    private final String id;
    private final Doctor doctor;
    private final Patient patient;
    private final Medication medication;
    private final LocalDate dateIssued;
    private final LocalDate expiryDate;

    // Constructor
    public Prescription(String id, Doctor doctor, Patient patient, Medication medication) {
        this.id = id;
        this.doctor = doctor;
        this.patient = patient;
        this.medication = medication;
        this.dateIssued = LocalDate.now();
        this.expiryDate = dateIssued.plusYears(1); 
    }

    // Getters
    public String getId() { return id; }
    public Doctor getDoctor() { return doctor; }
    public Patient getPatient() { return patient; }
    public Medication getMedication() { return medication; }
    public LocalDate getDateIssued() { return dateIssued; }
    public LocalDate getExpiryDate() { return expiryDate; }

}
