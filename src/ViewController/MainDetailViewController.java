package ViewController;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import DBController.NoticeDetailAdd;
import DBController.UserLogin;
import DBModel.NoticeDetailBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainDetailViewController implements Initializable {
	//Declare JAVA
	private NoticeDetailBean noticedetailbean;
	public static String login_id = LoginViewController.login_id;
	public static String noticedetail_id = "";
		
	//Declare FXML
	@FXML private Label maindetail_subtitle;
	@FXML private Label maindetail_context;
	@FXML private Button Property_userID;
	@FXML private Button BtnEdit;
	@FXML private TextField txtSubtitle, txtContext; 
	@FXML private Label txtFilepath;
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
	@FXML
	private void editAction(ActionEvent action) {
		ButtonType YES = new ButtonType("YES", ButtonBar.ButtonData.OK_DONE);
		ButtonType NO = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);
		Alert alert = new Alert(AlertType.NONE,"수정하시겠습니까?", YES, NO);
		alert.setTitle("Cancel");
		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.orElse(NO) == YES) {
			try {
				NAV(action, "../View/MainDetailEditView.fxml");
			}catch(Exception e) { }
		}
	}
	public void initialize(URL url, ResourceBundle rb) {
		NoticeDetailAdd noticedetailadd = new NoticeDetailAdd();
		maindetail_subtitle.setText(noticedetailadd.selectSubtitle(noticedetailadd.noticedetail_id));
		maindetail_context.setText(noticedetailadd.selectContext(noticedetailadd.noticedetail_id));
	}
	private void NAV (ActionEvent event, String str) throws IOException {
		Parent SignupView = FXMLLoader.load(getClass().getResource(str));
		Scene SignupView_scene = new Scene(SignupView);
		SignupView_scene.getStylesheets().add(getClass().getResource("../application/application.css").toExternalForm());
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(SignupView_scene);
		app_stage.show();
	}
}