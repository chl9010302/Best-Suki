package application;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import DBController.AddStastics;
import DBController.UserLogin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class StasticsViewController implements Initializable {
	//Declare JAVA
	
	//Declare FXML
	@FXML private TableView<AddStastics> StasticsView;
	@FXML private TableColumn<AddStastics, String> USER_ID;
	@FXML private TableColumn<AddStastics, String> USER_LOGIN_DATE;
	@FXML private TableColumn<AddStastics, String> USER_LOGOUT_DATE;
	@FXML private void NAV_LoginView(ActionEvent event) throws IOException { NAV(event, "../View/LoginView.fxml"); }
	@FXML private void NAV_MainView(ActionEvent event) throws IOException { NAV(event, "../View/MainView.fxml");	}
	@FXML private void NAV_TestView(ActionEvent event) throws IOException { NAV(event, "../View/TestView.fxml"); }
	@FXML private void NAV_TestBoardView(ActionEvent event) throws IOException { NAV(event, "../View/TestBoardView.fxml"); }
	@FXML private void NAV_StasticsView(ActionEvent event) throws IOException { NAV(event, "../View/StasticsView.fxml"); }
	@FXML private void NAV_MypageView(ActionEvent event) throws IOException { NAV(event, "../View/MypageView.fxml"); }
	@FXML private void NAV_VideoView(ActionEvent event) throws IOException { NAV(event, "../View/VideoView.fxml"); }
	@FXML
	private void logout(ActionEvent event) {
		UserLogin userlogout = new UserLogin();
		ButtonType YES = new ButtonType("YES", ButtonBar.ButtonData.OK_DONE);
		ButtonType NO = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);
		Alert alert = new Alert(AlertType.NONE,"Would you want to logout?", YES, NO);
		alert.setTitle("Logout");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.orElse(NO) == YES) {
			try {
				userlogout.logout(LoginViewController.login_id);
				userlogout.logout2(LoginViewController.login_id);
				NAV(event, "../View/LoginView.fxml");
			}catch(Exception e) { }
		}
	}
	public void initialize(URL url, ResourceBundle rb) {
		try {
			AddStastics stasticsview = new AddStastics();
			USER_ID.setCellValueFactory(cellData -> cellData.getValue().getUSER_ID());
			USER_LOGIN_DATE.setCellValueFactory(cellData -> cellData.getValue().getUSER_LOGIN_DATE());
			USER_LOGOUT_DATE.setCellValueFactory(cellData -> cellData.getValue().getUSER_LOGOUT_DATE());
			StasticsView.setItems(stasticsview.getstastics());
		}catch(Exception e) {}
	}
	private void NAV (ActionEvent event, String str) throws IOException {
		Parent SignupView = FXMLLoader.load(getClass().getResource(str));
		Scene SignupView_scene = new Scene(SignupView);
		SignupView_scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(SignupView_scene);
		app_stage.show();
	}
}
