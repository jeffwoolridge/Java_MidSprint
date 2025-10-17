import java.util.ArrayList;
import java.util.List;

public class Patient extends Person implements Identifiable {

    // Instance variables
    private final List<Medication> medications;
    private final List<Prescription> prescriptions;

    // Constructor
    public Patient(String id, String name, int age, String phoneNumber) {
        super(id, name, age, phoneNumber);
        this.medications = new ArrayList<>();
        this.prescriptions = new ArrayList<>();
    }

    // Getters
    public List<Medication> getMedications() { 
        return medications;
     
    }
    public List<Prescription> getPrescriptions() { 
        return prescriptions; 
    }

    // Add medication
    public void addMedication(Medication medication) {
        if (!medications.contains(medication)) {
            medications.add(medication);
        }
    }

    // Add prescription
    public void addPrescription(Prescription prescription) {
        prescriptions.add(prescription);
    }

}
