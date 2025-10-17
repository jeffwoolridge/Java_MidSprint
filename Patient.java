import java.util.List;

public class Patient extends Person implements Identifiable {

    // Instance variables
    private final List<Medication> medications;
    private final List<Prescription> prescriptions;

    // Constructor
    public Patient(String id, String name, int age, String phoneNumber) {
        super(id, name, age, phoneNumber);
        this.prescriptions = null;
        this.medications = null;
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

    @Override
    public String toString() {
        return "Patient{id='" + getId() + "', name='" + getName() + "', age=" + getAge() + "}";
    }


}
