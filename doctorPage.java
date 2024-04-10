package com.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.text.html.ImageView;

import org.w3c.dom.Text;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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
import javafx.stage.Stage;


public class doctorPage extends Application {
    private Stage stage;
    private Scene scene1;
    private Scene scene2;
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

    @SuppressWarnings("static-access")
    private Scene createDoctorPage() throws IOException
    {
        BorderPane mainPane = new BorderPane();
        mainPane.setStyle("-fx-background-color: #DFEDD6");

        String strName = Files.readAllLines(Paths.get(patientID + "_PatientInfo.txt")).get(1-1);
        String strGender = Files.readAllLines(Paths.get(patientID + "_PatientInfo.txt")).get(2-1);
        String strDOB = Files.readAllLines(Paths.get(patientID + "_PatientInfo.txt")).get(3-1);
        String strAge = Files.readAllLines(Paths.get(patientID + "_PatientInfo.txt")).get(4-1);
        String strWeight = Files.readAllLines(Paths.get(patientID + "_PatientInfo.txt")).get(5-1);
        String strHeight = Files.readAllLines(Paths.get(patientID + "_PatientInfo.txt")).get(6-1);
        String strAddress = Files.readAllLines(Paths.get(patientID + "_PatientInfo.txt")).get(7-1);
        String strPhoneNumber = Files.readAllLines(Paths.get(patientID + "_PatientInfo.txt")).get(8-1);
        String strEmail = Files.readAllLines(Paths.get(patientID + "_PatientInfo.txt")).get(9-1);
        String strInsurance = Files.readAllLines(Paths.get(patientID + "_PatientInfo.txt")).get(10-1);

        //Import Image
        FileInputStream inputStream = new FileInputStream("C:\\Users\\Jeshad\\Downloads\\HT_Logo.jpg");          //Change File path
        Image image = new Image(inputStream);
        ImageView imageView = new ImageView(image);

        //Welcome Text
        Text welcome = new Text("Welcome Doctor Smith");
        welcome.setFont(new Font(19));
        welcome.setStyle("-fx-font-weight: 600;");

        //Patient List
        ComboBox<String> patients = new ComboBox<String>();
        patients.getItems().addAll("John Doe", "Jane Smith", "Tom Brown", "Harry Potter");
        patients.setValue("Select Patient");
        patients.setPrefSize(200, 50);

        //Upcoming appointment
        Text appoint = new Text("Upcoming Appointments:");
        appoint.setFont(new Font(20));
        appoint.setStyle("-fx-font-weight: 700;");
        Text noApp = new Text("You have no Upcoming Appointments.");

        VBox wrapper = new VBox();
        wrapper.setSpacing(10);
        wrapper.getChildren().addAll(appoint, noApp);

        //VBox
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.TOP_LEFT);
        vbox.getChildren().addAll(welcome, imageView, wrapper);
        vbox.setSpacing(40);
        vbox.setPadding(new Insets(40, 0, 10, 20));


        Rectangle patOver = new Rectangle(500, 400);
        patOver.setArcWidth(100/5);
        patOver.setArcHeight(100/5);
        patOver.setFill(javafx.scene.paint.Color.WHITE);
        patOver.setStroke(Color.BLACK);
        patOver.setStrokeWidth(2);

        Text overviewTitle = new Text("Patient Overview");
        overviewTitle.setFont(new Font(25));
        overviewTitle.setStyle("-fx-font-weight: 600;");
        overviewTitle.setStyle("-fx-alignment: center;");


        Text leftTest = new Text ("Name: " + strName + "\nGender: " + strGender + "\nDOB: " + strDOB + "\nAge: " + strAge + "\nWeight " + strWeight + "\nHeight: " + strHeight + "\n\nAddress: " + strAddress + "\nPhone #: " + strPhoneNumber + "\nEmail: " + strEmail + "\nCurrent Insurance: " + strInsurance);
        leftTest.setFont(new Font(20));

        GridPane textBox = new GridPane();
        textBox.setHgap(5);
        textBox.setVgap(10);
        textBox.add(leftTest, 3, 5);
        //textBox.getChildren().addAll(leftTest);
        StackPane stack = new StackPane();
        stack.setAlignment(overviewTitle, Pos.TOP_CENTER);
        stack.getChildren().addAll(patOver, overviewTitle, textBox);

        Rectangle history = new Rectangle(500, 250);
        history.setArcWidth(100/5);
        history.setArcHeight(100/5);
        history.setFill(javafx.scene.paint.Color.WHITE);
        history.setStroke(Color.BLACK);
        history.setStrokeWidth(2);

        GridPane historyGridPane = new GridPane();

        Text historyTitle = new Text("History");
        historyTitle.setFont(new Font(25));
        historyTitle.setStyle("-fx-font-weight: 600;");
        historyTitle.setStyle("-fx-alignment: center;");

        Text historyInfo = new Text ("The Patient was diagnosed with...\n\nThe patient has history with...");
        historyInfo.setFont(new Font(20));

        Text immunization = new Text("Immunization: \tNo Immunity Records Found.");
        immunization.setFont(new Font(17));
        immunization.setStyle("-fx-font-weight: 700;");

        historyGridPane.setHgap(5);
        historyGridPane.setVgap(10);
        historyGridPane.add(historyInfo, 3 , 5);
        historyGridPane.add(immunization, 3, 9);

        StackPane historyStack = new StackPane();
        historyStack.setAlignment(historyTitle, Pos.TOP_CENTER);
        historyStack.getChildren().addAll(history, historyTitle, historyGridPane);

        VBox overview = new VBox();
        overview.setAlignment(Pos.TOP_CENTER);
        overview.getChildren().addAll(stack, historyStack);
        overview.setSpacing(40);
        overview.setPadding(new Insets(60, 70, 60, 100));

        Rectangle medication = new Rectangle(500, 200);
        medication.setArcWidth(100/5);
        medication.setArcHeight(100/5);
        medication.setFill(javafx.scene.paint.Color.WHITE);
        medication.setStroke(Color.BLACK);
        medication.setStrokeWidth(2);

        Text mediTitle = new Text("Medication");
        mediTitle.setFont(new Font(25));
        mediTitle.setStyle("-fx-font-weight: 600;");
        mediTitle.setStyle("-fx-alignment: center;");

        Text currentMed = new Text("Current Medications:");
        currentMed.setStyle("-fx-font-weight: 700;");
        currentMed.setFont(new Font(17));

        meds.setFont(new Font(17));

        GridPane medicationGridPane = new GridPane();
        medicationGridPane.setHgap(5);
        medicationGridPane.setVgap(10);
        medicationGridPane.add(currentMed, 3 , 5);
        medicationGridPane.add(meds, 3, 6);

        StackPane medsStack = new StackPane();
        medsStack.setAlignment(mediTitle, Pos.TOP_CENTER);
        medsStack.setAlignment(medication, Pos.TOP_CENTER);
        medsStack.getChildren().addAll(medication, mediTitle, medicationGridPane);

        Rectangle previousRectangle = new Rectangle(500, 200);
        previousRectangle.setArcWidth(100/5);
        previousRectangle.setArcHeight(100/5);
        previousRectangle.setFill(javafx.scene.paint.Color.WHITE);
        previousRectangle.setStroke(Color.BLACK);
        previousRectangle.setStrokeWidth(2);

        Text previousMedsTitle = new Text("Previous Medications");
        previousMedsTitle.setFont(new Font(25));
        previousMedsTitle.setStyle("-fx-font-weight: 600;");
        previousMedsTitle.setStyle("-fx-alignment: center;");

        Text listOfMeds = new Text ("    -Medication 1 \t\t\t\t\t01/01/2020\n    -Medication 2 \t\t\t\t\t01/01/2020\n    -Medication 3 \t\t\t\t\t01/01/2020");
        listOfMeds.setFont(new Font(20));

        GridPane prevMedGrid = new GridPane();
        prevMedGrid.setHgap(5);
        prevMedGrid.setVgap(10);
        prevMedGrid.add(listOfMeds, 3, 6);
        
        StackPane prevMedStack = new StackPane();
        prevMedStack.setAlignment(previousMedsTitle, Pos.TOP_CENTER);
        prevMedStack.getChildren().addAll(previousRectangle, previousMedsTitle, prevMedGrid);

        Rectangle addMedsRectangle = new Rectangle(500, 200);
        addMedsRectangle.setArcWidth(100/5);
        addMedsRectangle.setArcHeight(100/5);
        addMedsRectangle.setFill(javafx.scene.paint.Color.WHITE);
        addMedsRectangle.setStroke(Color.BLACK);
        addMedsRectangle.setStrokeWidth(2);

        Text addMedsTitle = new Text("Add Medications");
        addMedsTitle.setFont(new Font(25));
        addMedsTitle.setStyle("-fx-font-weight: 600;");
        addMedsTitle.setStyle("-fx-alignment: center;");

        TextField medicationNameField = new TextField();
        medicationNameField.setPromptText("Enter Medication Name");
        medicationNameField.setPrefWidth(400);
        medicationNameField.setMinHeight(30);
        medicationNameField.setFont(Font.font("Arial", 15));
        
        Button confirmButton = new Button("Confirm");
        confirmButton.setPrefSize(100, 10);

        GridPane addMedGrid = new GridPane();
        addMedGrid.setHgap(5);
        addMedGrid.setVgap(10);
        addMedGrid.add(medicationNameField, 10, 6);

        VBox  addMedVbox = new VBox();
        addMedVbox.setAlignment(Pos.TOP_CENTER);
        addMedVbox.getChildren().addAll(addMedGrid, confirmButton);
        addMedVbox.setSpacing(30);

        confirmButton.setOnAction(e -> {
            if (clickCount == 0)
            {
                clickCount++;
                strMed1 = medicationNameField.getText();
                meds.setText("\t- " + strMed1 + "\n\t- " + strMed3 + "\n\t- " + strMed3);
            }
            else if (clickCount == 1)
            {
                strMed2 = medicationNameField.getText();
                meds.setText("\t- " + strMed1 + "\n\t- " + strMed2 + "\n\t- " + strMed3);
                clickCount++;
            }
            else
            {
                strMed3 = medicationNameField.getText();
                meds.setText("\t- " + strMed1 + "\n\t- " + strMed2 + "\n\t- " + strMed3);
                clickCount++;
            }
        });

        StackPane addMedsStack = new StackPane();
        addMedsStack.setAlignment(addMedsTitle, Pos.TOP_CENTER);
        addMedsStack.getChildren().addAll(addMedsRectangle, addMedsTitle, addMedVbox);

        VBox rightSide = new VBox();
        rightSide.setAlignment(Pos.TOP_CENTER);
        rightSide.getChildren().addAll(medsStack, prevMedStack, addMedsStack);
        rightSide.setSpacing(30);
        rightSide.setPadding(new Insets(60, 0, 0, 0));


        //Adds the panes together
        BorderPane pane = new BorderPane();
        pane.setLeft(vbox);
        pane.setCenter(overview);
        pane.setRight(rightSide);
        HBox centerPane = new HBox();
        centerPane.getChildren().add(pane);
        mainPane.setCenter(centerPane);
        stage.setTitle("Healthtech Solutions - Doctor");
        scene1 = new Scene(mainPane, 1500, 800);
        
        return scene1;
    }
    private Scene createDocInfoPage()
    {
        BorderPane mainPane = new BorderPane();

        mainPane.setStyle("-fx-background-color: #DFEDD6");
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
