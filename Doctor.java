import java.util.ArrayList;
import java.util.List;

public class Doctor extends Person implements Identifiable {

    private final List<Patient> patients = new ArrayList<>();

    public Doctor(String id, String name) {
        super(id, name, 0, null);  // default age and phone if needed
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    @Override
    public String toString() {
        return "Doctor{id='" + getId() + "', name='" + getName() + "'}";
    }
}
