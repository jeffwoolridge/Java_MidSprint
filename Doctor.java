import java.util.ArrayList;
import java.util.List;

public class Doctor extends Person implements Identifiable {

    private String specialization;
    private final List<Patient> patients;

    public Doctor(String id, String name) {
        super(id, name, 0, ""); // Provide dummy values for age and phone number
        this.specialization = "";
        this.patients = new ArrayList<>();
    }


    

    public String getSpecialization() {
        return specialization;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return "Doctor{id='" + id + "', name='" + name + "'}";
    }

}

