package application;

import java.sql.SQLException;
import java.util.List;

import databasePart1.DatabaseHelper;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * AdminPage class represents the user interface for the admin user.
 * This page displays a simple welcome message for the admin.
 */

public class AdminHomePage {
	/**
     * Displays the admin page in the provided primary stage.
     * @param primaryStage The primary stage where the scene will be displayed.
     */
	
private final DatabaseHelper databaseHelper;
	
	public AdminHomePage(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }
	
    public void show(Stage primaryStage,User user) {
    	VBox layout = new VBox();
    	
	    layout.setStyle("-fx-alignment: center; -fx-padding: 20;");
	    
	    // label to display the welcome message for the admin
	    Label adminLabel = new Label("Hello, Admin!");
	    
	    adminLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

	    Scene adminScene = new Scene(layout, 800, 400);
	    
	    // "Invite" button for admin to generate invitation codes
            Button inviteButton = new Button("Invite");
            inviteButton.setOnAction(a -> {
            	 new InvitationPage().show(databaseHelper, primaryStage);
        });
            
        //add logout button    
            Button logoutButton = new Button("Log out");
    	    logoutButton.setOnAction(a -> {
    	    	
    	    	new UserLoginPage(databaseHelper).show(primaryStage);
    	 	    	
    	    });
    	    
    	//add change role button
    	    Button changeRoleButton = new Button("Change Role");
    	    changeRoleButton.setOnAction(a -> {
    	    	
    	    	new WelcomeLoginPage(databaseHelper).show(primaryStage,user);
    	 	    	 
    	    });
    	    
    	    //get all users information button:
    	    
    	    Button getAll = new Button("All Users");
    	    
    	    getAll.setOnAction(a -> {
    	    	
    	    	try {
    	    	 // Retrieve the list of all users from the database
    	    	List<User> users = databaseHelper.getAllUsers();
  
    	    	new AllUsers(databaseHelper).show(primaryStage,users,user);
    	    	
    	    }catch(SQLException ex) {
    	    	ex.printStackTrace();
    	    }
    	    
    	    });
    	  
            
            layout.getChildren().addAll(adminLabel,inviteButton,logoutButton,changeRoleButton
            		,getAll);
	    
	    // Set the scene to primary stage
	    primaryStage.setScene(adminScene);
	    primaryStage.setTitle("Admin Page");
    }
}