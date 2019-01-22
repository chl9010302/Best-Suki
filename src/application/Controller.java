package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Controller {
	private String operator = "";
	private int x = 0;
	private Model model = new Model();
	Parent myNewScene;
	
	@FXML
	private Text result;
	
	@FXML // Move LoginView
	private void NAV_SignUp(ActionEvent event) throws IOException {
		Parent SignupView = FXMLLoader.load(getClass().getResource("SignupView.fxml"));
		Scene SignupView_scene = new Scene(SignupView);
		SignupView_scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(SignupView_scene);
		app_stage.show();
	}
	
	@FXML // Move LoginView
	private void NAV_Login(ActionEvent event) throws IOException {
		Parent LoginView = FXMLLoader.load(getClass().getResource("LoginView.fxml"));
		Scene LoginView_scene = new Scene(LoginView);
		LoginView_scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(LoginView_scene);
		app_stage.show();
	}
	
	@FXML // Move LoginView
	private void NAV_Main(ActionEvent event) throws IOException {
		Parent MainView = FXMLLoader.load(getClass().getResource("MainView.fxml"));
		Scene MainView_scene = new Scene(MainView);
		MainView_scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(MainView_scene);
		app_stage.show();
	}
	
	@FXML
	private void operator(ActionEvent event) {
		if(((Button) event.getSource()).getText().equals("=")) {
			result.setText(model.calculate(operator, x, Integer.parseInt(result.getText()))+ "");
		} else {
			operator = ((Button) event.getSource()).getText();
			x = Integer.parseInt(result.getText());
			result.setText("");
		}
	}
}
