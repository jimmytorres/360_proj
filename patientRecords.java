package AutomationSystem;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class PatientRecords extends Application {

    // Sample patient data
    private List<Patient> patients;

    @Override
    public void start(Stage primaryStage) {
        // Initialize sample data
        initializeData();

        // Create UI components
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label patientLabel = new Label("Select Patient:");
        ComboBox<String> patientComboBox = new ComboBox<>();
        patients.forEach(patient -> patientComboBox.getItems().add(patient.getId()));

        TextArea summaryTextArea = new TextArea();
        summaryTextArea.setEditable(false);
        summaryTextArea.setWrapText(true);
        summaryTextArea.setPrefWidth(400);
        summaryTextArea.setPrefHeight(200);

        Button viewButton = new Button("View Summary");

        // Handle view button click
        viewButton.setOnAction(event -> {
            String patientId = patientComboBox.getValue();
            Patient selectedPatient = findPatientById(patientId);
            if (selectedPatient != null) {
                summaryTextArea.setText(selectedPatient.generateSummary());
            }
        });

        // Add components to the grid
        gridPane.add(patientLabel, 0, 0);
        gridPane.add(patientComboBox, 1, 0);
        gridPane.add(viewButton, 1, 1);
        gridPane.add(summaryTextArea, 0, 2, 2, 1);

        // Set up the scene
        Scene scene = new Scene(gridPane, 600, 400);
        primaryStage.setTitle("Patient Records");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Initialize sample data
    private void initializeData() {
        patients = new ArrayList<>();
        Patient patient1 = new Patient("P001", "Insurance A", "Immunization A", "Prescription A", "Pharmacy A");
        Patient patient2 = new Patient("P002", "Insurance B", "Immunization B", "Prescription B", "Pharmacy B");
        patients.add(patient1);
        patients.add(patient2);
    }

    // Find patient by ID
    private Patient findPatientById(String id) {
        for (Patient patient : patients) {
            if (patient.getId().equals(id)) {
                return patient;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        launch(args);
    }

    // Patient class
    static class Patient {
        private String id;
        private String insuranceType;
        private String immunizationRecords;
        private String prescriptions;
        private String preferredPharmacy;

        public Patient(String id, String insuranceType, String immunizationRecords, String prescriptions, String preferredPharmacy) {
            this.id = id;
            this.insuranceType = insuranceType;
            this.immunizationRecords = immunizationRecords;
            this.prescriptions = prescriptions;
            this.preferredPharmacy = preferredPharmacy;
        }

        public String getId() {
            return id;
        }

        public String generateSummary() {
            return "Patient ID: " + id + "\n" +
                    "Insurance Type: " + insuranceType + "\n" +
                    "Immunization Records: " + immunizationRecords + "\n" +
                    "Prescriptions: " + prescriptions + "\n" +
                    "Preferred Pharmacy: " + preferredPharmacy;
        }
    }
}
