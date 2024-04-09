package com.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class doctorPage extends Application {
    public static void main(String[] args) throws Exception
    {
        launch(args);
    }

    public static final int WIDTH = 1500, HEIGHT = 800;      //Size of GUI
    

    @SuppressWarnings("static-access")
    public void start(@SuppressWarnings("exports") Stage stage) throws FileNotFoundException
    {
        BorderPane mainPane = new BorderPane();
        mainPane.setStyle("-fx-background-color: #DFEDD6");

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
        vbox.getChildren().addAll(welcome, imageView, patients, wrapper);
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


        Text leftTest = new Text ("Name: John Doe\nGender: Male\nDOB: 01/01/2000\nAge: 24\nWeight 150 lb\nHeight: 5' 10'\n\nAddress: 123 Main St. Tempe, AZ 85218\nPhone #: (123) - 456 - 7890\nEmail: johndoe@gmail.com\nCurrent Insurance: United Health");
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

        Text meds = new Text("\t-Medication 1\n\t-Medication 2\n\t-Medication 3");
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

        Scene scene = new Scene(mainPane, WIDTH, HEIGHT);
        stage.setTitle("Healthtech Solutions - Doctor");
        stage.setScene(scene);
        stage.show();
    }

}
