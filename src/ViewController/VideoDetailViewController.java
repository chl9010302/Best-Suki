package ViewController;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import DBController.UserLogin;
import DBController.VideoDetailAdd;
import DBModel.VideoDetailBean;
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
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class VideoDetailViewController implements Initializable {
	//Declare JAVA
	private VideoDetailBean videodetailbean;
	public static String login_id = LoginViewController.login_id;
	public static String videodetail_id = "";
	private BorderPane rootLayout;
		
	//Declare FXML
	@FXML private Label videodetail_subtitle;
	@FXML private Label videodetail_filepath;
	@FXML private Button Property_userID;
	@FXML private Button BtnAdd;
	@FXML private Button BtnDelete;
	@FXML private TextField txtSubtitle, txtFilepath; 
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
	private void deleteAction(ActionEvent action) {
		ButtonType YES = new ButtonType("YES", ButtonBar.ButtonData.OK_DONE);
		ButtonType NO = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);
		Alert alert = new Alert(AlertType.NONE,"작성을 취소하시겠습니까?", YES, NO);
		alert.setTitle("Cancel");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.orElse(NO) == YES) {
			try {
				NAV(action, "../View/VideoView.fxml");
			}catch(Exception e) { }
		}
	}
	@FXML
	private void addAction(ActionEvent action) {
		try {
			videodetailbean = new VideoDetailBean();
			videodetailbean.setVIDEODETAIL_ID_PK(txtSubtitle.getText().toString());
			videodetailbean.setVIDEODETAIL_SUBTITLE(txtSubtitle.getText().toString());
			videodetailbean.setVIDEODETAIL_WRITER(login_id);
			videodetailbean.setVIDEODETAIL_FILEPATH(txtFilepath.getText().toString());
			VideoDetailAdd videodetailadd = new VideoDetailAdd();
			videodetailadd.insertVideodetail(videodetailbean);
			NAV(action, "../View/VideoView.fxml");
		}catch(Exception e) { }
	}
	public void initialize(URL url, ResourceBundle rb) {
		VideoDetailAdd videodetailadd = new VideoDetailAdd();
		videodetail_subtitle.setText(videodetailadd.selectSubtitle(videodetailadd.videodetail_id));
		videodetail_filepath.setText(videodetailadd.selectFilepath(videodetailadd.videodetail_id));
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
