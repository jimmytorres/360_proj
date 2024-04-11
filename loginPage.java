
package AutomationSystem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView; // Corrected import for ImageView
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text; // Corrected import for Text
import javafx.stage.Stage;


public class loginPage extends Application {
    public static void main(String[] args) throws Exception
    {
        launch(args);
    }

    public static final int WIDTH = 1500, HEIGHT = 800;      //Size of GUI
    
    public void start(@SuppressWarnings("exports") Stage stage) throws FileNotFoundException
    {
        BorderPane mainPane = new BorderPane();  // Create the BorderPane directly
        mainPane.setStyle("-fx-border-color: black; -fx-border-width: 4; -fx-border-insets: 50;");
        mainPane.setStyle("-fx-background-color: #DFEDD6");

        //Import Image
        FileInputStream inputStream = new FileInputStream("/Users/jimmytorres/Downloads/HT_Logo.jpg");     //Edit File path of logo
        Image image = new Image(inputStream);
        ImageView imageView = new ImageView(image);

        //Username, Password, Login
        Text healthText = new Text("Healthtech Pediatrics");
        healthText.setFont(new Font(40));
        healthText.setStyle("-fx-font-weight: 700;");
        //Adding labels, checkboxes to the eat gridpane
        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Login");

        usernameField.setPromptText("Username");
        usernameField.setMaxWidth(400);
        usernameField.setMinHeight(50);
        usernameField.setFont(Font.font("Arial", 15));

        passwordField.setPromptText("Password");
        passwordField.setMaxWidth(400);
        passwordField.setMinHeight(50);
        passwordField.setFont(Font.font("Arial", 15));

        loginButton.setPrefSize(150, 50);

        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");

        //VBox
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.getChildren().addAll(imageView, healthText, usernameField, passwordField, loginButton, errorLabel);
        vbox.setSpacing(40);
        vbox.setPadding(new Insets(100, 100, 60, 600));

        //Adds the panes together
        BorderPane pane = new BorderPane();
        pane.setCenter(vbox);
        HBox centerPane = new HBox();
        centerPane.getChildren().add(pane);
        mainPane.setCenter(centerPane);

        Scene scene = new Scene(mainPane, WIDTH, HEIGHT);
        stage.setTitle("Healthtech Solutions - Login");
        stage.setScene(scene);
        stage.show();

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            // Perform validation (check if username anthisd password meet criteria)
            if (isValidLogin(username, password)) {
                System.out.println("Login successful!");

                // Check if the username contains "@doctor"
                if (username.toLowerCase().contains("@doctor")) {

                    doctorPage doctorDisplay = new doctorPage();
                    try {
                        stage.close();
                        doctorDisplay.start(new Stage());
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    }
                } else if (username.toLowerCase().contains("@nurse")) {
                    nursePage nurseDisplay = new nursePage();
                    try {
                        stage.close();
                        nurseDisplay.start(new Stage());
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    }
                } else if (username.toLowerCase().contains("@patient")) {
                	patientPage patientDisplay = new patientPage();
                    stage.close();
					patientDisplay.start(new Stage());
                }
                  else {
                    errorLabel.setText("Access denied.");
                }
            } else {
                errorLabel.setText("Invalid credentials. Please check your username and password.");
            }
        });
    }

    // Method to validate login credentials
    private boolean isValidLogin(String username, String password) {
        // Check if username contains "@doctor" and password contains exactly three 'z' characters
    	if (username.toLowerCase().contains("@doctor") && countZs(password) == 3) {
    		return true;
    	} else if (username.toLowerCase().contains("@nurse") && countZs(password) == 3) {
    		return true;
    	} else if (username.toLowerCase().contains("@patient") && countZs(password) == 3) {
    		return true;
    	} else {
    		return false;
    	}
        
    }
    // Helper method to count occurrences of 'z' in a string
    private int countZs(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'z' || str.charAt(i) == 'Z') {
                count++;
            }
        }
        return count;
    }
}
