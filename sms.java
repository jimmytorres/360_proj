package AutomationSystem;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class sms {

    @Override
    public void start(Stage primaryStage) {
        // Create UI components
        TextField recipientField = new TextField();
        recipientField.setPromptText("Recipient");

        TextArea messageArea = new TextArea();
        messageArea.setPromptText("Compose your message here");

        Label statusLabel = new Label();
        statusLabel.setTextFill(Color.RED); // Set default color to red

        Button sendButton = new Button("Send");
        sendButton.setOnAction(e -> {
            String recipient = recipientField.getText();
            String message = messageArea.getText();
            if (!recipient.isEmpty() && !message.isEmpty()) {
                // Here you can implement the logic to send the message
                // For simplicity, we'll just print the message to the console
                System.out.println("Message sent to: " + recipient);
                System.out.println("Message content: " + message);
                // Clear fields after sending
                recipientField.clear();
                messageArea.clear();
                statusLabel.setText("Message sent successfully");
                statusLabel.setTextFill(Color.GREEN);
            } else if (recipient.isEmpty()) {
                statusLabel.setText("Error: No recipient found");
                statusLabel.setTextFill(Color.RED);
            }
        });

        // Arrange components in a layout
        VBox inputBox = new VBox(10, recipientField, messageArea, sendButton, statusLabel);
        inputBox.setPadding(new Insets(10));

        // Set up the scene and stage
        Scene scene = new Scene(inputBox, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Messaging System");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
