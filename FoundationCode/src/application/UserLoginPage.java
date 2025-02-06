package application;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;

import databasePart1.*;

/**
 * The UserLoginPage class provides a login interface for users to access their accounts.
 * It validates the user's credentials and navigates to the appropriate page upon successful login.
 */
public class UserLoginPage {
	
    private final DatabaseHelper databaseHelper;

    public UserLoginPage(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public void show(Stage primaryStage) {
    	// Input field for the user's userName, password
        TextField userNameField = new TextField();
        userNameField.setPromptText("Enter userName");
        userNameField.setMaxWidth(250);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter Password");
        passwordField.setMaxWidth(250);
        
        // Label to display error messages
        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red; -fx-font-size: 12px;");


        Button loginButton = new Button("Login");
        
        loginButton.setOnAction(a -> {
        	// Retrieve user inputs
            String userName = userNameField.getText();
            String password = passwordField.getText();
            try {
            	User user=new User(userName, password, "");
            	

            	WelcomeLoginPage welcomeLoginPage = new WelcomeLoginPage(databaseHelper);
            	
            	// Retrieve the user's role from the database using userName
            	String role = databaseHelper.getUserRole(userName);
            	
            	if(role!=null) {
            		user.setRole(role);
            		
            		//If there is more than one role the user have, led them to the Welcome page
            		if(databaseHelper.login(user)  && role.length() > 10) {
            			welcomeLoginPage.show(primaryStage,user);
            		}
            		
                	//if they has one role, led them to the role page directly
            		else if(role.equals("admin")) {
        	    		new AdminHomePage(databaseHelper).show(primaryStage,user);
        	    	}
        	    	else if(role.equals("user")) {
        	    		new UserHomePage(databaseHelper).show(primaryStage,user);
        	    	}
        	    	else if (role.equals("instructor")) {
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
                	
            		
            		else {
            			// Display an error if the login fails
                        errorLabel.setText("Error logging in");
            		}
            	}
            	else {
            		// Display an error if the account does not exist
                    errorLabel.setText("user account doesn't exists");
            	}
            	
            } catch (SQLException e) {
                System.err.println("Database error: " + e.getMessage());
                e.printStackTrace();
            } 
        });

        VBox layout = new VBox(10);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");
        layout.getChildren().addAll(userNameField, passwordField, loginButton, errorLabel);

        primaryStage.setScene(new Scene(layout, 800, 400));
        primaryStage.setTitle("User Login");
        primaryStage.show();
    }
    
   
}
