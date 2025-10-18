import java.time.LocalDate;

public class Prescription {

    private final String id;
    private final Doctor doctor;
    private final Patient patient;
    private final Medication medication;
    private final LocalDate dateIssued;
    private final LocalDate expiryDate;

    public Prescription(String id, Doctor doctor, Patient patient, Medication medication) {
        this.id = id;
        this.doctor = doctor;
        this.patient = patient;
        this.medication = medication;
        this.dateIssued = LocalDate.now();
        this.expiryDate = dateIssued.plusYears(1);
    }

    public String getId() { return id; }
    public Doctor getDoctor() { return doctor; }
    public Patient getPatient() { return patient; }
    public Medication getMedication() { return medication; }

    public LocalDate getIssuedDate() { return dateIssued; } // ✅ Fixed method
    public LocalDate getExpiryDate() { return expiryDate; }

    @Override
    public String toString() {
        return "Prescription{id='" + id + 
               "', doctor=" + doctor.getName() + 
               ", patient=" + patient.getName() + 
               ", medication=" + medication.getName() + 
               ", issued=" + dateIssued + 
               ", expires=" + expiryDate + "}";
    }
}
