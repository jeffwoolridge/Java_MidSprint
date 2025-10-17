import java.util.*;

public class MedicationTrackingSystem {

    private final List<Patient> patients = new ArrayList<>();
    private final List<Doctor> doctors = new ArrayList<>();
    private final List<Medication> medications = new ArrayList<>();
    private final List<Prescription> prescriptions = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            System.out.println("\n\n Medication Tracking System \n");
            System.out.println("1. Search");
            System.out.println("2. Add Doctor");
            System.out.println("3. Add Patient");
            System.out.println("4. Add Patient to Doctor");
            System.out.println("5. Accept Prescription");
            System.out.println("6. Edit or Delete");
            System.out.println("7. Generate Report");
            System.out.println("8. Check Expired Medications");
            System.out.println("9. Print Prescriptions by Doctor");
            System.out.println("10. Restock Medications");
            System.out.println("11. Exit \n");
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> search();
                case "2" -> addDoctorInteractive();
                case "3" -> addPatientInteractive();
                case "4" -> addPatientToDoctor();
                case "5" -> acceptPrescription();
                case "6" -> editOrDelete();
                case "7" -> generateReport();
                case "8" -> checkExpiredMedications();
                case "9" -> printPrescriptionsByDoctor();
                case "10" -> restockMedications();
                case "11" -> {
                    System.out.println("Exiting...");
                    return; 
    }
            default -> System.out.println("Invalid choice.");
}

        }
    }

    private void search() {
        System.out.print("Search for (patient/doctor/medication): ");
        String type = scanner.nextLine().toLowerCase();
        System.out.print("Enter name to search: ");
        String name = scanner.nextLine().toLowerCase();

        switch (type) {
            case "patient" ->
                patients.stream()
                    .filter(p -> p.getName().toLowerCase().contains(name))
                    .forEach(System.out::println);
                
            case "doctor" ->
                doctors.stream()
                    .filter(d -> d.getName().toLowerCase().contains(name))
                    .forEach(System.out::println);
                
            case "medication" ->
                medications.stream()
                    .filter(m -> m.getName().toLowerCase().contains(name))
                    .forEach(System.out::println);
            
            default ->
                System.out.println("Invalid search type.");
        }
    }

        private void addPatientToDoctor() {
            Doctor doctor = findDoctorById();
            Patient patient = findPatientById();

            if (doctor != null && patient != null) {
                doctor.addPatient(patient);
                System.out.println("Patient added to doctor.");
        }
    }

    private void acceptPrescription() {
        Doctor doctor = findDoctorById();
        Patient patient = findPatientById();
        Medication medication = findMedicationById();

        if (doctor != null && patient != null && medication != null) {
            String presId = UUID.randomUUID().toString();
            Prescription pres = new Prescription(presId, doctor, patient, medication);
            prescriptions.add(pres);
            patient.addPrescription(pres);
            patient.addMedication(medication);
            System.out.println("Prescription added.");
        }
    }

    private void editOrDelete() {
        System.out.print("Edit or delete (patient/doctor/medication): ");
        String type = scanner.nextLine().toLowerCase();

        switch (type) {
            case "patient" ->
                editList(patients);
            
            case "doctor" -> 
                editList(doctors);
                
            case "medication" ->
                editList(medications);
            
            default ->
                System.out.println("Invalid type.");
        }
    }

    private <T extends Identifiable> void editList(List<T> list) {
        System.out.print("Enter ID to edit/delete: ");
        String id = scanner.nextLine();
        T item = list.stream().filter(i -> i.getId().equals(id)).findFirst().orElse(null);

        if (item != null) {
            System.out.print("Edit or delete? ");
            String action = scanner.nextLine().toLowerCase();
            if (action.equals("delete")) {
                list.remove(item);
                System.out.println("Deleted.");
            } else if (action.equals("edit")) {
                System.out.println("Editing not implemented yet.");
            }
        } else {
            System.out.println("Item not found.");
        }
    }

    private void generateReport() {
        System.out.println("\n--- System Report ---");
        System.out.println("Patients:");
        patients.forEach(System.out::println);
        System.out.println("\nDoctors:");
        doctors.forEach(System.out::println);
        System.out.println("\nMedications:");
        medications.forEach(System.out::println);
        System.out.println("\nPrescriptions:");
        prescriptions.forEach(System.out::println);
    }

    private void checkExpiredMedications() {
        System.out.println("Expired Medications:");
        medications.stream()
            .filter(Medication::isExpired)
            .forEach(System.out::println);
    }

    private void printPrescriptionsByDoctor() {
        Doctor doctor = findDoctorById();
        if (doctor != null) {
            prescriptions.stream()
                .filter(p -> p.getDoctor().equals(doctor))
                .forEach(System.out::println);
        }
    }

    private void restockMedications() {
        medications.forEach(med -> {
            int amount = new Random().nextInt(50) + 1;
            med.restock(amount);
            System.out.println(med.getName() + " restocked by " + amount);
        });
    }

    // Utility methods
    private Doctor findDoctorById() {
        System.out.print("Enter doctor ID: ");
        String id = scanner.nextLine();
        return doctors.stream().filter(d -> d.getId().equals(id)).findFirst().orElse(null);
    }

    private Patient findPatientById() {
        System.out.print("Enter patient ID: ");
        String id = scanner.nextLine();
        return patients.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }

    private Medication findMedicationById() {
        System.out.print("Enter medication ID: ");
        String id = scanner.nextLine();
        return medications.stream().filter(m -> m.getId().equals(id)).findFirst().orElse(null);
    }

    public void addDoctor(Doctor doctor) {
    doctors.add(doctor);
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public void addMedication(Medication medication) {
        medications.add(medication);
    }

    public void addPrescription(Prescription prescription) {
        prescriptions.add(prescription);
    }

    public static void main(String[] args) {
        MedicationTrackingSystem system = new MedicationTrackingSystem();
        system.start();
    }

    private void addDoctorInteractive() {
        System.out.print("Enter Doctor ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter Doctor Name: ");
        String name = scanner.nextLine();

        Doctor doctor = new Doctor(id, name);
        addDoctor(doctor);
        System.out.println("Doctor added successfully.");
    }

    private void addPatientInteractive() {
    System.out.print("Enter Patient ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter Patient Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Patient Age: ");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Patient Phone Number: ");
        String phoneNumber = scanner.nextLine();

        // Create a patient object
        Patient patient = new Patient(id, name, age, phoneNumber);

    // Add to the system
    addPatient(patient);

    System.out.println("Patient added successfully.");
    }


}

    



