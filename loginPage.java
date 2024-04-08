package AutomationSystem;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class loginPage extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        // Create text fields for username and password
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        // Error label for displaying login error messages
        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");

        // Create login button
        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            // Perform validation (check if username and password meet criteria)
            if (isValidLogin(username, password)) {
                System.out.println("Login successful!");

                // Check if the username contains "@doctor"
                if (username.toLowerCase().contains("@doctor")) {
                    // Open doctor's UI display
                    new recep().display(); // Assuming DoctorUI has a display method
                } else {
                    errorLabel.setText("Access denied. Only doctors allowed.");
                }
            } else {
                errorLabel.setText("Invalid credentials. Please check your username and password.");
            }
        });

        // Add components to the layout
        layout.getChildren().addAll(usernameField, passwordField, loginButton, errorLabel);

        // Create and set the scene
        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setTitle("Healthtech Solutions - Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to validate login credentials
    private boolean isValidLogin(String username, String password) {
        // Check if username contains "@doctor" and password contains exactly three 'z' characters
        return username.toLowerCase().contains("@doctor") && countZs(password) == 3;
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

    public static void main(String[] args) {
        launch(args);
    }
}
