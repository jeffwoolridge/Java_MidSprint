import java.util.ArrayList;
import java.util.List;

public class Doctor extends Person {

    private String specialization;
    private final List<Patient> patients;

    public Doctor(String id, String name, int age, String phoneNumber, String specialization) {
        super(id, name, age, phoneNumber);
        this.specialization = specialization;
        this.patients = new ArrayList<>();
    }

    public String getSpecialization() {
        return specialization;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void addPatient(Patient patient) {
        if (!patients.contains(patient)) {
            patients.add(patient);

    }}

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
}
 
 }

