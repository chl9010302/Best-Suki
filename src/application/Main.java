package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	public static String sfxml = "LoginView.fxml";
	
	public static void setsfxml(String Sfxml) {
		sfxml = Sfxml;
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent fxml = FXMLLoader.load(getClass().getResource(sfxml));
			// show root BorderPanel
			Scene scene = new Scene(fxml);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
	
	//DB 
	DBConnection connection = new DBConnection();
	System.out.println("관리자 여부 : "+connection.isAdmin("admin","admin"));
}
