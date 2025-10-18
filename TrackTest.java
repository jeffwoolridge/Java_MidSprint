import java.util.UUID;
import java.time.LocalDate;
import java.sql.Date;

public class TrackTest {
    public static void main(String[] args) {
        // Initialize the Medication Tracking System
        MedicationTrackingSystem system = new MedicationTrackingSystem();

        // Create mock doctors
        Doctor drDave = new Doctor("D1", "Dr. Dave");
        Doctor drSteve = new Doctor("D2", "Dr. Steve");
        Doctor drDre = new Doctor("D3", "Dr. Dre");
        Doctor drEvil = new Doctor("D4", "Dr. Evil");

        // Create mock patients
        Patient jeffWoolridge = new Patient("P1", "Jeff Woolridge", 22, "333-333-3333");
        Patient jamesBond = new Patient("P2", "James Bond", 12, "709-823-8283");

        // Create a medication with a future expiry date
        LocalDate expiryLocalDate = LocalDate.now().plusMonths(6); // expires in 6 months
        Date expiryDate = Date.valueOf(expiryLocalDate);
        Medication aspirin = new Medication("M1", "Aspirin", 10, expiryDate);

        // Add doctors and patients to the system
        system.addDoctor(drDave);
        system.addDoctor(drSteve);
        system.addDoctor(drDre);
        system.addDoctor(drEvil);

        system.addPatient(jeffWoolridge);
        system.addPatient(jamesBond);

        // Link patients to doctors
        drDave.addPatient(jeffWoolridge);
        drSteve.addPatient(jamesBond);

        // Add medication to system
        system.addMedication(aspirin);

        // Create and add a sample prescription
        Prescription pres = new Prescription(
            UUID.randomUUID().toString(),
            drDave,
            jeffWoolridge,
            aspirin
        );

        // Add prescription to system and patient
        system.addPrescription(pres);
        jeffWoolridge.addPrescription(pres);
        jeffWoolridge.addMedication(aspirin);

        // Start the interactive system
        system.start();
    }
}
