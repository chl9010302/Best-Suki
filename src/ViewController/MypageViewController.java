package ViewController;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import DBController.SelectNowUser;
import DBController.UserLogin;
import DBModel.UserBean;
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
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MypageViewController implements Initializable {
	//Declare FXML
	@FXML private Label Mypage_UserId, Mypage_UserPassword, Mypage_UserName,  Mypage_UserAddress, Mypage_UserSchoolName, Mypage_UserAge, Mypage_UserGender, Mypage_UserPhone, Mypage_UserFmphone;
	@FXML private void NAV_LoginView(ActionEvent event) throws IOException { NAV(event, config.StaticProperty.getnavloginview()); }
	@FXML private void NAV_MainView(ActionEvent event) throws IOException { NAV(event, config.StaticProperty.getnavmainview());	}
	@FXML private void NAV_TestView(ActionEvent event) throws IOException { NAV(event, config.StaticProperty.getnavtestview()); }
	@FXML private void NAV_TestBoardView(ActionEvent event) throws IOException { NAV(event, config.StaticProperty.getnavtestboardview()); }
	@FXML private void NAV_StasticsView(ActionEvent event) throws IOException { NAV(event, config.StaticProperty.getnavstasticsview()); }
	@FXML private void NAV_MypageView(ActionEvent event) throws IOException { NAV(event, config.StaticProperty.getnavmypageview()); }
	@FXML private void NAV_MypageEditView(ActionEvent event) throws IOException { NAV(event, config.StaticProperty.getnavmypageeditview()); }
	@FXML private void NAV_VideoView(ActionEvent event) throws IOException { NAV(event, config.StaticProperty.getnavvideoview()); }
	@FXML
	private void logout(ActionEvent event) {
		UserLogin userlogout = new UserLogin();
		ButtonType YES = new ButtonType(config.StaticProperty.alertbtnyes(), ButtonBar.ButtonData.OK_DONE);
		ButtonType NO = new ButtonType(config.StaticProperty.alertbtnno(), ButtonBar.ButtonData.CANCEL_CLOSE);
		Alert alert = new Alert(AlertType.NONE,config.StaticProperty.alertlogout(), YES, NO);
		alert.setTitle(config.StaticProperty.alerttitlelogout());
		Optional<ButtonType> result = alert.showAndWait();
		if (result.orElse(NO) == YES) {
			try {
				userlogout.logout(LoginViewController.login_id);
				userlogout.logout2(LoginViewController.login_id);
				NAV(event, config.StaticProperty.getnavloginview());
			}catch(Exception e) { }
		}
	}
	public void initialize(URL url, ResourceBundle rb) {
		try {
			SelectNowUser selectnowuser = new SelectNowUser();
			UserBean userbean;
			userbean  = selectnowuser.getSelectUser(LoginViewController.login_id);
			Mypage_UserId.setText(userbean.getUSER_ID_PK());
			Mypage_UserPassword.setText("*********");
			Mypage_UserName.setText(userbean.getUSER_NAME());
			Mypage_UserAddress.setText(userbean.getUSER_ADDRESS());
			Mypage_UserSchoolName.setText(userbean.getUSER_SCHOOLNAME());
			Mypage_UserAge.setText(userbean.getUSER_AGE());
			Mypage_UserGender.setText(userbean.getUSER_GENDER());
			Mypage_UserPhone.setText(userbean.getUSER_PHONE());
			Mypage_UserFmphone.setText(userbean.getUSER_FMPHONE());
			}catch (Exception e) {
				// TODO: handle exception
			}
	}
	private void NAV (ActionEvent event, String str) throws IOException {
		Parent SignupView = FXMLLoader.load(getClass().getResource(str));
		Scene SignupView_scene = new Scene(SignupView);
		SignupView_scene.getStylesheets().add(getClass().getResource(config.StaticProperty.getnavapplication()).toExternalForm());
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(SignupView_scene);
		app_stage.show();
	}
}
