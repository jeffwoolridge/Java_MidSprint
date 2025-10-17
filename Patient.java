import java.util.ArrayList;
import java.util.List;

public class Patient extends Person implements Identifiable {

    private final List<Medication> medications;
    private final List<Prescription> prescriptions;

    public Patient(String id, String name, int age, String phoneNumber) {
        super(id, name, age, phoneNumber);
        this.prescriptions = new ArrayList<>(); 
        this.medications = new ArrayList<>();   
    }

    public List<Medication> getMedications() { 
        return medications;
    }

    public List<Prescription> getPrescriptions() { 
        return prescriptions; 
    }

    public void addMedication(Medication medication) {
        if (!medications.contains(medication)) {
            medications.add(medication);
        }
    }

    public void addPrescription(Prescription prescription) {
        prescriptions.add(prescription);
    }

    @Override
    public String toString() {
        return "Patient{id='" + getId() + "', name='" + getName() + "', age=" + getAge() + "}";
    }
}
