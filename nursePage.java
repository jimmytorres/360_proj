package AutomationSystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView; // Corrected import for ImageView
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text; // Corrected import for Text
import javafx.stage.Stage;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.nio.file.StandardOpenOption;



public class nursePage extends Application {
    private Stage stage;
    private Scene scene1;
    private Scene scene2;
    private Scene scene3;
    String patientID;
    String strMed1 = "";
    String strMed2 = "";
    String strMed3 = "";

    int clickCount = 0;
    Label meds = new Label();

    public static void main(String[] args) throws Exception
    {
        launch(args);
    }
    
    public void start(@SuppressWarnings("exports") Stage primaryStage) throws FileNotFoundException
    {
        stage = primaryStage;
		stage.setTitle("Heart Health Imaging and Recording System");

		//scene1 = createDoctorPage();
        scene2 = createDocInfoPage();

        stage.setScene(scene2);
		stage.show();
    }
    
    private Scene createUpdatePatientInfoPage() throws IOException {
        // Assuming patientID is defined and valid
        List<String> patientInfo = Files.readAllLines(Paths.get(patientID + "_PatientInfo.txt"));
        
        if (patientInfo.size() < 10) {
            throw new IOException("Incomplete patient info for ID: " + patientID);
        }

        BorderPane mainPane = new BorderPane();
        mainPane.setStyle("-fx-background-color: #F0E68C"); // Light khaki background

        Text instructions = new Text("Update Patient Information");
        instructions.setFont(new Font("Arial", 24));

        // Adding labels for each TextField
        Label nameLabel = new Label("Name:");
        TextField newNameField = new TextField(patientInfo.get(0));
        
        Label genderLabel = new Label("Gender:");
        TextField newGenderField = new TextField(patientInfo.get(1));
        
        Label dobLabel = new Label("DOB (dd/mm/yyyy):");
        TextField newDOBField = new TextField(patientInfo.get(2));
        
        Label ageLabel = new Label("Age:");
        TextField newAgeField = new TextField(patientInfo.get(3));
        
        Label weightLabel = new Label("Weight (kg):");
        TextField newWeightField = new TextField(patientInfo.get(4));
        
        Label heightLabel = new Label("Height (cm):");
        TextField newHeightField = new TextField(patientInfo.get(5));
        
        Label addressLabel = new Label("Address:");
        TextField newAddressField = new TextField(patientInfo.get(6));
        
        Label phoneLabel = new Label("Phone Number:");
        TextField newPhoneField = new TextField(patientInfo.get(7));
        
        Label emailLabel = new Label("Email:");
        TextField newEmailField = new TextField(patientInfo.get(8));
        
        Label insuranceLabel = new Label("Insurance:");
        TextField newInsuranceField = new TextField(patientInfo.get(9));

        Button submitButton = new Button("Submit Updates");
        submitButton.setOnAction(e -> {
            try {
                updatePatientInfo(
                    patientID,
                    newNameField.getText(),
                    newGenderField.getText(),
                    newDOBField.getText(),
                    newAgeField.getText(),
                    newWeightField.getText(),
                    newHeightField.getText(),
                    newAddressField.getText(),
                    newPhoneField.getText(),
                    newEmailField.getText(),
                    newInsuranceField.getText()
                );
                // Assuming update is successful, switch back to the previous scene
                stage.setScene(scene1); // Or call switchScenes(scene1) if that's how your navigation is setup
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> {
            // Switch back to the previous scene directly without updating
            stage.setScene(scene1); // Or call switchScenes(scene1) if that's how your navigation is setup
        });

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.add(nameLabel, 0, 0);
        grid.add(newNameField, 1, 0);
        // Continue adding other labels and fields to the grid in similar fashion
        grid.addRow(1, genderLabel, newGenderField);
        grid.addRow(2, dobLabel, newDOBField);
        grid.addRow(3, ageLabel, newAgeField);
        grid.addRow(4, weightLabel, newWeightField);
        grid.addRow(5, heightLabel, newHeightField);
        grid.addRow(6, addressLabel, newAddressField);
        grid.addRow(7, phoneLabel, newPhoneField);
        grid.addRow(8, emailLabel, newEmailField);
        grid.addRow(9, insuranceLabel, newInsuranceField);

        VBox vBox = new VBox(20, instructions, grid, submitButton, cancelButton);
        vBox.setAlignment(Pos.CENTER);
        mainPane.setCenter(vBox);

        return new Scene(mainPane, 600, 800);
    }






    
    private void updatePatientInfo(String patientID, String name, String gender, String dob, String age, String weight, String height, String address, String phone, String email, String insurance) throws IOException {
        List<String> lines = Arrays.asList(name, gender, dob, age, weight, height, address, phone, email, insurance);
        Files.write(Paths.get(patientID + "_PatientInfo.txt"), lines, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
    }


    @SuppressWarnings("static-access")
    private Scene createDoctorPage() throws IOException {
        BorderPane mainPane = new BorderPane();
        mainPane.setStyle("-fx-background-color: #957DAD");

        List<String> patientInfo = Files.readAllLines(Paths.get(patientID + "_PatientInfo.txt"));
        String strName = patientInfo.get(0);
        String strGender = patientInfo.get(1);
        String strDOB = patientInfo.get(2);
        String strAge = patientInfo.get(3);
        String strWeight = patientInfo.get(4);
        String strHeight = patientInfo.get(5);
        String strAddress = patientInfo.get(6);
        String strPhoneNumber = patientInfo.get(7);
        String strEmail = patientInfo.get(8);
        String strInsurance = patientInfo.get(9);

        FileInputStream inputStream = new FileInputStream("/Users/jimmytorres/Downloads/HT_Logo.jpg");
        Image image = new Image(inputStream);
        ImageView imageView = new ImageView(image);

        Text welcome = new Text("Welcome Nurse");
        welcome.setFont(new Font(19));
        welcome.setStyle("-fx-font-weight: 600;");

        ComboBox<String> patients = new ComboBox<>();
        patients.getItems().addAll("John Doe", "Jane Smith", "Tom Brown", "Harry Potter");
        patients.setValue("Select Patient");
        patients.setPrefSize(200, 50);

        Text appoint = new Text("Upcoming Appointments:");
        appoint.setFont(new Font(20));
        appoint.setStyle("-fx-font-weight: 700;");
        Text noApp = new Text("You have no Upcoming Appointments.");

        VBox wrapper = new VBox(appoint, noApp);
        wrapper.setSpacing(10);

        VBox vbox = new VBox(welcome, imageView, wrapper);
        vbox.setAlignment(Pos.TOP_LEFT);
        vbox.setSpacing(40);
        vbox.setPadding(new Insets(40, 0, 10, 20));

        // Patient Overview StackPane
        Text overviewTitle = new Text("Patient Overview");
        overviewTitle.setFont(new Font(25));
        overviewTitle.setStyle("-fx-font-weight: 600; -fx-alignment: center;");

        Text patientDetails = new Text("Name: " + strName + "\nGender: " + strGender + "\nDOB: " + strDOB + "\nAge: " + strAge + "\nWeight: " + strWeight + "\nHeight: " + strHeight + "\nAddress: " + strAddress + "\nPhone #: " + strPhoneNumber + "\nEmail: " + strEmail + "\nInsurance: " + strInsurance);
        patientDetails.setFont(new Font(20));

        StackPane stackPaneOverview = new StackPane();
        Rectangle overviewBackground = new Rectangle(500, 400, Color.WHITE);
        overviewBackground.setStroke(Color.BLACK);
        overviewBackground.setStrokeWidth(2);
        stackPaneOverview.getChildren().addAll(overviewBackground, overviewTitle, patientDetails);
        StackPane.setAlignment(overviewTitle, Pos.TOP_CENTER);

        // History StackPane
        Text historyTitle = new Text("History");
        historyTitle.setFont(new Font(25));
        historyTitle.setStyle("-fx-font-weight: 600; -fx-alignment: center;");

        Text historyDetails = new Text("The patient was diagnosed with...\nThe patient has history with...");
        historyDetails.setFont(new Font(20));

        StackPane stackPaneHistory = new StackPane();
        Rectangle historyBackground = new Rectangle(500, 250, Color.WHITE);
        historyBackground.setStroke(Color.BLACK);
        historyBackground.setStrokeWidth(2);
        stackPaneHistory.getChildren().addAll(historyBackground, historyTitle, historyDetails);
        StackPane.setAlignment(historyTitle, Pos.TOP_CENTER);

        Button updateInfoButton = new Button("Update Patient Info");
        updateInfoButton.setPrefSize(200, 40);
        updateInfoButton.setOnAction(e -> {
            try {
                scene3 = createUpdatePatientInfoPage();
                switchScenes(scene3);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        VBox overview = new VBox(10, stackPaneOverview, stackPaneHistory, updateInfoButton);
        overview.setAlignment(Pos.TOP_CENTER);
        overview.setPadding(new Insets(10));

        mainPane.setLeft(vbox);
        mainPane.setCenter(overview);
        stage.setTitle("Healthtech Solutions - Doctor");
        scene1 = new Scene(mainPane, 1500, 800);

        return scene1;
    }


    private Scene createDocInfoPage()
    {
        BorderPane mainPane = new BorderPane();

        mainPane.setStyle("-fx-background-color: #957DAD");
        Text topText = new Text("Please enter the patient's ID");
        topText.setFont(new Font("Times New Roman", 30));
        topText.setStyle("-fx-font-weight: 600;");
        mainPane.setTop(topText);
        BorderPane.setAlignment(topText, Pos.CENTER);
        mainPane.setPadding(new Insets(20, 0, 0, 0));

        Button btnPatientView = new Button("Submit");
        btnPatientView.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
        btnPatientView.setPrefSize(250, 100);
        btnPatientView.setFont(Font.font("Times New Roman", 30));

        TextField tfPatientID = new TextField();
        tfPatientID.setMaxWidth(350);
        tfPatientID.setMinHeight(60);
        tfPatientID.setFont(new Font(25));

        btnPatientView.setOnAction(e -> {
			String strIDinput = tfPatientID.getText();
            patientID = strIDinput;
			File file = new File(strIDinput + "_PatientInfo.txt");
			if (file.exists())
            {
				try {
                    scene1 = createDoctorPage();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
				switchScenes(scene1);
			}
			else
			{
				topText.setText("Invalid ID. Please try again later.");
			}
		});
        
        VBox vboxMain = new VBox();
        vboxMain.setAlignment(Pos.CENTER);
        vboxMain.getChildren().addAll(tfPatientID, btnPatientView);
        vboxMain.setSpacing(70);
        
        //Adds the panes together
        BorderPane pane = new BorderPane();
        HBox centerPane = new HBox();
        centerPane.getChildren().add(pane);
        mainPane.setCenter(vboxMain);

		scene2 = new Scene(mainPane, 1500, 800);
		return scene2;
    }

    @SuppressWarnings("exports")
	public void switchScenes(Scene scene) {
		stage.setScene(scene);
	}
}
