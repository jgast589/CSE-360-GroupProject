package application;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import databasePart1.DatabaseHelper;


public class Student implements Role {
	
	private final DatabaseHelper databaseHelper;
	
	public Student(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public void showPage(Stage primaryStage,User user) {
        
    	VBox layout = new VBox();
	    layout.setStyle("-fx-alignment: center; -fx-padding: 20;");
	    
	    // Label to display Hello Student
	    Label studentLabel = new Label("Hello, Student!");
	    studentLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

	    Button logoutButton = new Button("Log out");
        logoutButton.setOnAction(a -> {
          
            new UserLoginPage(databaseHelper).show(primaryStage);
            
           
        });
        
    	//add change role button
	    Button changeRoleButton = new Button("Change Role");
	    changeRoleButton.setOnAction(a -> {
	    	
	    	new WelcomeLoginPage(databaseHelper).show(primaryStage,user);
	 	    	
	    });
	    
	    layout.getChildren().addAll(studentLabel,logoutButton,changeRoleButton);
	    Scene studentScene = new Scene(layout, 800, 400);

	    // Set the scene to primary stage
	    primaryStage.setScene(studentScene);
	    primaryStage.setTitle("Student Page");
    }

	@Override //Just for avoid error
	public void showPage(Stage stage) {
		// TODO Auto-generated method stub
		
	}
}



