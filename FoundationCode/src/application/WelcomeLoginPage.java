package application;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.application.Platform;
import databasePart1.*;

/**
 * The WelcomeLoginPage class displays a welcome screen for authenticated users.
 * It allows users to navigate to their respective pages based on their role or quit the application.
 */
public class WelcomeLoginPage {
	
	private final DatabaseHelper databaseHelper;

    public WelcomeLoginPage(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }
    public void show( Stage primaryStage, User user) {
    	
    	VBox layout = new VBox(5);
	    layout.setStyle("-fx-alignment: center; -fx-padding: 20;");
	    
	    Label welcomeLabel = new Label("Welcome!!");
	    welcomeLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
	    
	    
	    //add role selection box to chose if they have different role
	    ComboBox<String> roleComboBox = new ComboBox<>();
	    
	    //Replace strings with data return from user set. defelt is user.
	    roleComboBox.getItems().addAll(user.getRole(),"student","instructor","staff","Reviewer");
	  
	    // Set the default selection to the user's current role (if valid).
	    if (user != null && (user.getRole().equals("admin") || user.getRole().equals("user"))) {
	        roleComboBox.setValue(user.getRole());
	    } else {
	        roleComboBox.setValue("user");  // Or default if user role is not recognized
	    }
	    

	    
	    // Button to navigate to the user's respective page based on their role
	    Button continueButton = new Button("Continue to your Page");
	    continueButton.setOnAction(a -> {
	    	String role =roleComboBox.getValue();
	    	System.out.println("Selected role: "+ role);
	    	
	    	//add those if else statement to drive the user to their role page
	    	if(role.equals("admin")) {
	    		new AdminHomePage(databaseHelper).show(primaryStage,user);
	    	}
	    	else if(role.equals("user")) {
	    		new UserHomePage(databaseHelper).show(primaryStage,user);
	    	}
	    	if (role.equals("instructor")) {
	    	    new Instructor(databaseHelper).showPage(primaryStage,user);
	    	}
	    	else if (role.equals("student")) {
	    	    new Student(databaseHelper).showPage(primaryStage,user);
	    	}
	    	else if (role.equals("veviewer")) {
	    	    new Reviewer(databaseHelper).showPage(primaryStage,user);
	    	}
	    	else if (role.equals("staff")) {
	    	    new Staff(databaseHelper).showPage(primaryStage,user);
	    	}
	    	
	    });
	    
	    
	    // Button to quit the application
	    Button quitButton = new Button("Quit");
	    quitButton.setOnAction(a -> {
	    	databaseHelper.closeConnection();
	    	Platform.exit(); // Exit the JavaFX application
	    });
	    
	    
	    layout.getChildren().addAll(welcomeLabel,roleComboBox,continueButton,quitButton);
	    Scene welcomeScene = new Scene(layout, 800, 400);

	    // Set the scene to primary stage
	    primaryStage.setScene(welcomeScene);
	    primaryStage.setTitle("Welcome Page");
    }
}