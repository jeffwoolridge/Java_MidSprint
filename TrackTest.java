import java.util.UUID;
import java.util.Date;

public class TrackTest {
    public static void main(String[] args) {
        MedicationTrackingSystem system = new MedicationTrackingSystem();

        // Mock Doctors 
        Doctor drSmith = new Doctor("D1", "Dr. Smith");
        Doctor drJones = new Doctor("D2", "Dr. Jones");

        // Mock Patients 
        Patient johnDoe = new Patient("P1", "John Doe", 0, "333-333-3333");

        // Mock Medications (
        //Date expiry = new Date(); // or some valid Date
        Medication aspirin = new Medication("id1", "Aspirin", 10, new Date());
        
        // Add to system
        system.addDoctor(drSmith);
        system.addDoctor(drJones);


        system.addMedication(aspirin);
        
        // Add sample prescription
        Prescription pres = new Prescription(UUID.randomUUID().toString(), drSmith, johnDoe, aspirin);
        system.addPrescription(pres);
        johnDoe.addPrescription(pres);
        johnDoe.addMedication(aspirin);

        // Start system
        system.start();
    }
}

