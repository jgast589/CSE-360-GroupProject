package application;

import databasePart1.DatabaseHelper;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AllUsers {

	private final DatabaseHelper databaseHelper;
	
	public AllUsers(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }
	
    public void show(Stage primaryStage, List<User> user, User current) throws SQLException {
    	
    	System.out.println("Users infor");
    	
    	//function put here.
    	List<User> users = user;
    	
    	//error label
    	 Label errorLabel = new Label();
         errorLabel.setStyle("-fx-text-fill: red; -fx-font-size: 12px;");
         
    	// create box to show the users
    	VBox root = new VBox(10);
    	root.setPadding(new Insets(15));
    	
    	
    	
    	//label
    	Label header = new Label("List of Users: ");
    	header.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
    	root.getChildren().add(header);
    	
    	System.out.println("Users infor2");
    	
    	//create label for each user and add them in the Box
    	
    	for(User u : users) {
    		
    		HBox userRow = new HBox(10);
    		
    		Label userLabel = new Label ("Username: "+ u.getUserName() 
    		+"| Role: " + u.getRole());
    		System.out.print(userLabel);
    		Button delete = new Button("Delete");
    		
    	//create delete button to delete users
    		delete.setOnAction(a -> {
    			//delete that account.
    			errorLabel.setText("");
    					
    			 if (u.getUserName().equals(current.getUserName())) {
                     errorLabel.setText("Error: You cannot delete your own account!");
                     return;
    			 }
    				 
    			 if ("admin".equalsIgnoreCase(u.getRole())) {
    	                    errorLabel.setText("Error: Admin accounts cannot be removed!");
    	                    return;
    	          }
    				 
    			 Alert alert = new Alert(AlertType.CONFIRMATION);
    	         alert.setTitle("Confirm Deletion");
    	         alert.setHeaderText("Are you sure?");
    	         alert.setContentText("Do you really want to delete user " + u.getUserName() + "?");
                     
    	         ButtonType yesButton = new ButtonType("Yes", ButtonData.YES);
    	         ButtonType noButton = new ButtonType("No", ButtonData.NO);
    	         alert.getButtonTypes().setAll(yesButton, noButton);
    	                
    	         Optional<ButtonType> result = alert.showAndWait();
    	         
    	         if(result.isPresent() && result.get()==yesButton) {       
    	         try {
    	                     // Call the deleteUser method.
    	        	   
    	               databaseHelper.deleteUser(u.getUserName());
    	               show(primaryStage, users , current);
    	        				 
    	                
    	         } catch (SQLException ex) {
                     ex.printStackTrace();
                 }
    	         }
    				
    		});
    		
    		
    		//add Role button to add role or delete role
        	Button RoleButton = new Button("Role");
        	
        	RoleButton.setOnAction(a -> {
        		//add function
        		
        		
        		
        		
        	});
	
    		userRow.getChildren().addAll(userLabel,delete,RoleButton);
    		root.getChildren().add(userRow);
    		
    		
    	}
    	
    	root.getChildren().add(errorLabel);
    	
    	
    	
    	Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            // Define your navigation logic here.
        	new AdminHomePage(databaseHelper).show(primaryStage, current);
        });
        
        Button changeRole = new Button("Change Role");
        changeRole.setOnAction(a -> {
        	//add function for remove or add role.
 	
        	
        });
        
        
        root.getChildren().add(backButton);
    	
    	   Scene AllUser = new Scene(root, 800, 400);

   	    // Set the scene to primary stage
   	    primaryStage.setScene(AllUser);
   	    primaryStage.setTitle("Alluser Page");
    	
        }; 
    }
    
    
