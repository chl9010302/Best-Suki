package application;
	
import java.io.IOException;

import config.StaticProperty;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	public static String sfxml = "../View/LoginView.fxml";
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
	public static void main(String[] args) throws IOException {
		StaticProperty staticproperty = new StaticProperty();
		launch(args);
	}
}