package application;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import databasePart1.DatabaseHelper;



public class Staff implements Role {
	
	private final DatabaseHelper databaseHelper;
	
	public Staff(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public void showPage(Stage primaryStage,User user) {
        
    	VBox layout = new VBox();
	    layout.setStyle("-fx-alignment: center; -fx-padding: 20;");
	    
	    // Label to display Hello user
	    Label staffLabel = new Label("Hello, Staff!");
	    staffLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

	    Button logoutButton = new Button("Log out");
        logoutButton.setOnAction(a -> {

            new UserLoginPage(databaseHelper).show(primaryStage);

        });
        
    	//add change role button
	    Button changeRoleButton = new Button("Change Role");
	    changeRoleButton.setOnAction(a -> {
	    	
	    	new WelcomeLoginPage(databaseHelper).show(primaryStage,user);
	 	    	
	    });
	    
	    layout.getChildren().addAll(staffLabel,logoutButton,changeRoleButton);
	    Scene userScene = new Scene(layout, 800, 400);

	    // Set the scene to primary stage
	    primaryStage.setScene(userScene);
	    primaryStage.setTitle("Staff Page");
    }

	@Override  //Just for avoid error
	public void showPage(Stage stage) {
		// TODO Auto-generated method stub
		
	}
}

