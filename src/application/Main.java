package application;
	
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class Main extends Application {
	
	@FXML
	private String test;
	
	@FXML
	private Button Login;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent fxml = FXMLLoader.load(getClass().getResource("LoginView.fxml"));
			// show root BorderPanel
			Scene scene = new Scene(fxml);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			System.out.println(test);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
}
