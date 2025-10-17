import java.time.LocalDate;
import java.util.*;

public class MedicationTrackingSystem {

    private final List<Patient> patients = new ArrayList<>();
    private final List<Doctor> doctors = new ArrayList<>();
    private final List<Medication> medications = new ArrayList<>();
    private final List<Prescription> prescriptions = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);
    private int prescriptionCounter = 1; // Add this at the top of your class


    public void start() {
        while (true) {
            System.out.println("\n\n Medication Tracking System \n");
            System.out.println("1. Search");
            System.out.println("2. Add Doctor");
            System.out.println("3. Add Patient");
            System.out.println("4. Add Patient to Doctor");
            System.out.println("5. Add Medication");
            System.out.println("6. Accept Prescription");
            System.out.println("7. Edit or Delete");
            System.out.println("8. Generate Report");
            System.out.println("9. Check Expired Medications");
            System.out.println("10. Print Prescriptions by Doctor");
            System.out.println("11. Restock Medications");
            System.out.println("12. Exit \n");
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> search();
                case "2" -> addDoctorInteractive();
                case "3" -> addPatientInteractive();
                case "4" -> addPatientToDoctor();
                case "5" -> addMedicationInteractive(); 
                case "6" -> acceptPrescription();
                case "7" -> editOrDelete();
                case "8" -> generateReport();           
                case "9" -> checkExpiredMedications();  
                case "10" -> printPrescriptionsByDoctor();
                case "11" -> restockMedications();
                case "12" -> {
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
    String name = scanner.nextLine().toLowerCase(); // ✅ Only declare once

    switch (type) {
        case "patient" ->
            patients.stream()
                .filter(p -> p.getName().toLowerCase().contains(name))
                .forEach(this::printPatientWithDoctors); // ✅ optionally print with doctors
        
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
            String presId = "RX" + String.format("%03d", prescriptionCounter++);
            Prescription pres = new Prescription(presId, doctor, patient, medication);
            prescriptions.add(pres);
            patient.addPrescription(pres);
            patient.addMedication(medication);
            System.out.println("Prescription added with ID: " + presId);
        } else {
            System.out.println("Doctor, patient, or medication not found. Prescription not added.");
        }
    }



    private void editOrDelete() {
        System.out.print("Edit or delete (patient/doctor/medication): ");
        String type = scanner.nextLine().toLowerCase();

        switch (type) {
            case "patient" -> {
                System.out.print("Enter patient ID: ");
                String id = scanner.nextLine();
                Patient patient = patients.stream()
                    .filter(p -> p.getId().equals(id))
                    .findFirst()
                    .orElse(null);

                if (patient != null) {
                    System.out.print("Edit or delete? ");
                    String action = scanner.nextLine().toLowerCase();
                    if (action.equals("delete")) {
                        patients.remove(patient);
                        System.out.println("Patient deleted.");
                    } else if (action.equals("edit")) {
                        editPatient(patient);
                    }
                } else {
                    System.out.println("Patient not found.");
                }
            }

            case "doctor" -> {
                System.out.print("Enter doctor ID: ");
                String id = scanner.nextLine();
                Doctor doctor = doctors.stream()
                    .filter(d -> d.getId().equals(id))
                    .findFirst()
                    .orElse(null);

                if (doctor != null) {
                    System.out.print("Edit or delete? ");
                    String action = scanner.nextLine().toLowerCase();
                    if (action.equals("delete")) {
                        doctors.remove(doctor);
                        System.out.println("Doctor deleted.");
                    } else if (action.equals("edit")) {
                        editDoctor(doctor);
                    }
                } else {
                    System.out.println("Doctor not found.");
                }
            }

            case "medication" -> {
                System.out.print("Enter medication ID: ");
                String id = scanner.nextLine();
                Medication medication = medications.stream()
                    .filter(m -> m.getId().equals(id))
                    .findFirst()
                    .orElse(null);

                if (medication != null) {
                    System.out.print("Edit or delete? ");
                    String action = scanner.nextLine().toLowerCase();
                    if (action.equals("delete")) {
                        medications.remove(medication);
                        System.out.println("Medication deleted.");
                    } else if (action.equals("edit")) {
                        editMedication(medication);
                    }
                } else {
                    System.out.println("Medication not found.");
                }
            }

            default -> System.out.println("Invalid type.");
        }
    }



    private void editMedication(Medication medication) {
        System.out.print("Enter new name (current: " + medication.getName() + "): ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) {
            medication.setName(newName);
        }

        System.out.print("Enter new quantity (current: " + medication.getQuantity() + "): ");
        String qtyInput = scanner.nextLine();
        if (!qtyInput.isEmpty()) {
            try {
                int newQty = Integer.parseInt(qtyInput);
                medication.setQuantity(newQty);
            } catch (NumberFormatException e) {
                System.out.println("Invalid quantity entered. Skipping update.");
            }
        }

        System.out.println("Medication updated.");
    }


    private void editDoctor(Doctor doctor) {
        System.out.print("Enter new name (current: " + doctor.getName() + "): ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) {
            doctor.setName(newName);
        }

        System.out.println("Doctor updated.");
    }


    private void editPatient(Patient patient) {
        System.out.print("Enter new name (current: " + patient.getName() + "): ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) {
            patient.setName(newName);
        }

        System.out.print("Enter new age (current: " + patient.getAge() + "): ");
        String ageInput = scanner.nextLine();
        if (!ageInput.isEmpty()) {
            try {
                int newAge = Integer.parseInt(ageInput);
                patient.setAge(newAge);
            } catch (NumberFormatException e) {
                System.out.println("Invalid age entered. Skipping update.");
            }
        }

        System.out.print("Enter new phone number (current: " + patient.getPhoneNumber() + "): ");
        String phoneInput = scanner.nextLine();
        if (!phoneInput.isEmpty()) {
            patient.setPhoneNumber(phoneInput);
        }

        System.out.println("Patient updated.");
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

        // create Patient
        Patient patient = new Patient(id, name, age, phoneNumber);

    // aDD to system
    addPatient(patient);

    System.out.println("Patient added successfully.");
}
    private void addMedicationInteractive() {
        System.out.print("Enter Medication ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter Medication Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Medication Quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine());

        
        int randomDays = new Random().nextInt(701) + 30; 
        LocalDate expiryDate = LocalDate.now().plusDays(randomDays);
        Date expiry = java.sql.Date.valueOf(expiryDate);
        System.out.println("Generated expiry date: " + expiryDate);

        Medication medication = new Medication(id, name, quantity, expiry);
        addMedication(medication);
        System.out.println("Medication added successfully.");
    }


    private void printPatientWithDoctors(Patient patient) {
        System.out.println(patient);

        System.out.print("Doctors: ");
        List<Doctor> doctorsForPatient = doctors.stream()
            .filter(d -> d.getPatients().contains(patient))
            .toList();

        if (doctorsForPatient.isEmpty()) {
            System.out.println("None");
        } else {
            doctorsForPatient.forEach(d -> System.out.println("  " + d));
        }

    }

    public static void main(String[] args) {
        MedicationTrackingSystem system = new MedicationTrackingSystem();
        system.start();
    }
}
    



