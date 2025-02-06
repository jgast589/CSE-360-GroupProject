package application;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import databasePart1.DatabaseHelper;


public class Reviewer implements Role {
	
	  private final DatabaseHelper databaseHelper;
	
	 public Reviewer(DatabaseHelper databaseHelper) {
	        this.databaseHelper = databaseHelper;
	    }

    public void showPage(Stage primaryStage, User user) {
    	
    	VBox layout = new VBox();
	    layout.setStyle("-fx-alignment: center; -fx-padding: 20;");
	    
	    // Label to display Hello reviewer
	    Label reviewerLabel = new Label("Hello, Reviewer!");
	    reviewerLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

	    Button logoutButton = new Button("Log out");
        logoutButton.setOnAction(a -> {
        	
            new UserLoginPage(databaseHelper).show(primaryStage);});
	    
    	//add change role button
	    Button changeRoleButton = new Button("Change Role");
	    changeRoleButton.setOnAction(a -> {
	    	
	    	new WelcomeLoginPage(databaseHelper).show(primaryStage,user);
	 	    	
	    });
	    
	    layout.getChildren().addAll(reviewerLabel,logoutButton,changeRoleButton);
	    Scene reviewerScene = new Scene(layout, 800, 400);

	    // Set the scene to primary stage
	    primaryStage.setScene(reviewerScene);
	    primaryStage.setTitle("Reviewer Page");
    }

	@Override //Just for avoid error
	public void showPage(Stage stage) {
		// TODO Auto-generated method stub
		
	}
}

