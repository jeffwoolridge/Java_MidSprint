import java.util.UUID;

public class TrackTest {
    public static void main(String[] args) {
        MedicationTrackingSystem system = new MedicationTrackingSystem();

        // Mock Doctors 
        Doctor drSmith = new Doctor("D1", "Dr. Smith", 45, "Cardiology", "Male");
        Doctor drJones = new Doctor("D2", "Dr. Jones", 50, "Neurology", "Female");

        // Mock Patients 
        Patient johnDoe = new Patient("P1", "John Doe", 43, "Male");
        Patient janeRoe = new Patient("P2", "Jane Roe", 32, "Female");

        // Mock Medications (
        Medication aspirin = new Medication("M1", "Aspirin", "500mg", 100);
        

        // Add to system
        system.addDoctor(drSmith);
        system.addDoctor(drJones);

        system.addPatient(johnDoe);
        system.addPatient(janeRoe);

        system.addMedication(aspirin);
        

        //  Add sample prescription
        Prescription pres = new Prescription(UUID.randomUUID().toString(), drSmith, johnDoe, aspirin);
        system.addPrescription(pres);
        johnDoe.addPrescription(pres);
        johnDoe.addMedication(aspirin);

        // Start system
        system.start();
    }
}

