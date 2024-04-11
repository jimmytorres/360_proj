package AutomationSystem;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class patientPage extends Application{

    private Label usernameLabel;
    private Label patientNameLabel;
    private Label dobLabel;
    private Label appointmentSummaryArea;
    private ComboBox<String> dateDropdown;

    @Override
    public void start(Stage primaryStage) {
        usernameLabel = new Label("Username: jrabbit89\n");
        patientNameLabel = new Label("Patient Name: Jessica Rabbit\n");
        dobLabel = new Label("DOB: 05/01/1989");
        
     // Load the image
        Image logoImage = new Image("file:///C:/Users/zacar/eclipse-workspace/CSE360/src/project_prototype/HT_Logo.JPG");
        ImageView logoImageView = new ImageView(logoImage);
        logoImageView.setFitWidth(100); // Set width of the logo
        logoImageView.setPreserveRatio(true); // Maintain aspect ratio of the logo

        // Add logo above the username
        VBox logoBox = new VBox(5, logoImageView); // Adjust spacing as needed
        logoBox.setAlignment(Pos.CENTER_LEFT);
        logoBox.setPadding(new Insets(10, 10, 0, 10)); // Add padding to adjust the position

        // Left pane for update and prescriptions
        VBox leftPane = new VBox(10);
        leftPane.setPadding(new Insets(10));
        Button updateContactInfoButton = new Button("Update Contact Info");
        updateContactInfoButton.setMaxWidth(Double.MAX_VALUE);

        updateContactInfoButton.setOnAction(e -> new updateInfo().display());

        ListView<String> prescriptionsListView = new ListView<>();
        prescriptionsListView.getItems().add("No prescription");
        leftPane.getChildren().addAll(logoBox, usernameLabel, patientNameLabel, dobLabel, updateContactInfoButton, new Label("Prescriptions"), prescriptionsListView);

        // Center pane for appointment summary
        GridPane centerGrid = new GridPane();
        centerGrid.setVgap(10);
        centerGrid.setHgap(10);
        centerGrid.setPadding(new Insets(10));
        
     // Add label for "Patient Portal" with increased font size
        Label patientPortalLabel = new Label("Patient Portal");
        patientPortalLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 18pt;"); // Adjust font size as needed


        Label appointmentSummaryLabel = new Label("Appointment Summary");
        appointmentSummaryLabel.setStyle("-fx-font-weight: bold;");

        dateDropdown = new ComboBox<>();
        dateDropdown.getItems().addAll("February 3, 2024", "February 4, 2024", "February 5, 2024");
        dateDropdown.setValue("February 3, 2024"); // Set default value
        dateDropdown.setPrefWidth(200);

        appointmentSummaryArea = new Label();
        appointmentSummaryArea.setText("**Doctor's Appointment Summary**\r\n"
        		+ "\r\n"
        		+ "Patient: Jessica Rabbit  \r\n"
        		+ "Date: April 10, 2024  \r\n"
        		+ "Doctor: Dr. M. Hare  \r\n"
        		+ "\r\n"
        		+ "**Complaint:**  \r\n"
        		+ "Mild fatigue and occasional headaches.\r\n"
        		+ "\r\n"
        		+ "**History:**  \r\n"
        		+ "Fatigue for two weeks, attributed to work stress.\r\n"
        		+ "\r\n"
        		+ "**Medical History:**  \r\n"
        		+ "None reported. No known allergies.\r\n"
        		+ "\r\n"
        		+ "**Family History:**  \r\n"
        		+ "Non-contributory.\r\n"
        		+ "\r\n"
        		+ "**Social History:**  \r\n"
        		+ "Occupation: Entertainer. No tobacco, alcohol, or drug use.\r\n"
        		+ "\r\n"
        		+ "**Review:**  \r\n"
        		+ "No fever, weight changes, or gastrointestinal symptoms.\r\n"
        		+ "\r\n"
        		+ "**Exam:**  \r\n"
        		+ "Stable vitals. Well-appearing.\r\n"
        		+ "Normal findings on examination.\r\n"
        		+ "\r\n"
        		+ "**Assessment:**  \r\n"
        		+ "Stress-related fatigue and tension headaches.\r\n"
        		+ "Discussed stress management.\r\n"
        		+ "\r\n"
        		+ "**Plan:**  \r\n"
        		+ "Follow up as needed. No medications prescribed.\r\n"
        		+ "\r\n"
        		+ "**Provider:**  \r\n"
        		+ "Dr. M. Hare");
        appointmentSummaryArea.setWrapText(true);

        // Add listener to the dateDropdown
        dateDropdown.setOnAction(event -> updateAppointmentSummary());

        centerGrid.add(patientPortalLabel, 0, 1); // Add Patient Portal label
        centerGrid.add(appointmentSummaryLabel, 0, 2);
        centerGrid.add(dateDropdown, 0, 2); // Move dropdown to column 0
        centerGrid.add(appointmentSummaryArea, 0, 3);

     // Right pane for appointment option
        VBox rightPane = new VBox(10);
        rightPane.setPadding(new Insets(10));
        Button makeAppointmentButton = new Button("Make Appointment");
        makeAppointmentButton.setMaxWidth(Double.MAX_VALUE);
        makeAppointmentButton.setOnAction(e -> makeAppointment());

        rightPane.getChildren().add(makeAppointmentButton);
        
     // Main layout
        HBox mainLayout = new HBox(10);
        mainLayout.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY))); // Set light green background
        mainLayout.getChildren().addAll(leftPane, centerGrid, rightPane); // Add rightPane to mainLayout
        HBox.setHgrow(centerGrid, Priority.ALWAYS); // Makes the center grid grow in horizontal size

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(mainLayout);

        // Add button for sms
        Button smsButton = new Button("Send SMS");
        smsButton.setOnAction(e -> new sms().start(new Stage()));
        
        // Adjust button size and position
        smsButton.setPrefWidth(120); // Increase width by 20%
        smsButton.setTranslateX(-20); // Move button 20 units to the left
        smsButton.setTranslateY(-20); // Move button 20 units up

        borderPane.setBottom(smsButton);
        BorderPane.setAlignment(smsButton, Pos.BOTTOM_RIGHT);

        // Scene and stage setup
        Scene scene = new Scene(borderPane, 950, 650);
        primaryStage.setTitle("Enhanced Appointment Summary");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void onUpdate(String username, String patientName, String dob) {
        if (username != null && !username.isEmpty()) {
            usernameLabel.setText("Username: " + username + "\n");
        }
        if (patientName != null && !patientName.isEmpty()) {
            patientNameLabel.setText("Patient Name: " + patientName + "\n");
        }
        if (dob != null && !dob.isEmpty()) {
            dobLabel.setText("DOB: " + dob);
        }
    }

    private void updateAppointmentSummary() {
        String selectedDate = dateDropdown.getValue();
        String patientName = patientNameLabel.getText().replace("Patient Name: ", "").trim();
        // Update appointmentSummaryArea based on selected date and patient name
        appointmentSummaryArea.setText("Patient: " + patientName + "\nDate: " + selectedDate + "\nChief Complaint: ... [Content truncated for brevity]");
    }

    private void makeAppointment() {
        Stage appointmentStage = new Stage();
        appointmentStage.setTitle("Make Appointment");

        VBox appointmentLayout = new VBox(10);
        appointmentLayout.setPadding(new Insets(10));

        Label selectDateLabel = new Label("Select Appointment Date:");

        ComboBox<String> dateDropdown = new ComboBox<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);
        for (int i = 0; i < 7; i++) {
            LocalDate date = LocalDate.now().plusDays(i);
            dateDropdown.getItems().add(date.format(formatter));
        }
        dateDropdown.setValue(dateDropdown.getItems().get(0)); // Set default value

        Label selectedDateLabel = new Label(); // Label to display selected date

        Button confirmButton = new Button("Confirm");
        confirmButton.setOnAction(e -> {
            String selectedDate = dateDropdown.getValue();
            selectedDateLabel.setText("Selected Date: " + selectedDate); // Update label with selected date
            System.out.println("Appointment scheduled for: " + selectedDate);
            appointmentStage.close();
        });

        appointmentLayout.getChildren().addAll(selectDateLabel, dateDropdown, confirmButton, selectedDateLabel); // Add label to layout

        Scene appointmentScene = new Scene(appointmentLayout, 300, 200);
        appointmentStage.setScene(appointmentScene);
        appointmentStage.show();
    }


}

class updateInfo {
    public void display() {
        Stage updateInfoWindow = new Stage();
        updateInfoWindow.setTitle("Update Info");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(12);
        grid.setHgap(12);
        grid.setPadding(new Insets(10));

        Label changeInfo = new Label("Update Info");

        //Add code here
        TextField nameField = new TextField();
        TextField dobField = new TextField();
        TextField passwordField = new TextField();

        grid.add(new Label("Name:"), 0, 1);
        grid.add(nameField, 1, 1);

        grid.add(new Label("Password:"), 0, 3);
        grid.add(passwordField, 1, 3);

        grid.add(changeInfo, 0, 0);
        //Add code here if necessary

        Scene scene = new Scene(grid, 250, 250);
        updateInfoWindow.setScene(scene);
        updateInfoWindow.show();
    }
}
