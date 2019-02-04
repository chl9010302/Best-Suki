package ViewController;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import DBController.SelectNowUser;
import DBController.UserDataUpdate;
import DBController.UserLogin;
import DBModel.UserBean;
import academyutil.Sha256;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MypageEditViewController implements Initializable {
	//Declare JAVA
	Sha256 sha256 = new Sha256();
	
	//Declare FXML
	@FXML private Button Property_userID;
	@FXML private Label Mypage_UserId;
	@FXML private TextField EditProperty_UserPassword, EditProperty_UserName,  EditProperty_UserAddress, EditProperty_UserSchoolName, EditProperty_UserAge, EditProperty_UserGender, EditProperty_UserPhone, EditProperty_UserFmphone;
	@FXML private void NAV_MainView(ActionEvent event) throws IOException { NAV(event, "../View/MainView.fxml");	}
	@FXML private void NAV_TestView(ActionEvent event) throws IOException { NAV(event, "../View/TestView.fxml"); }
	@FXML private void NAV_TestBoardView(ActionEvent event) throws IOException { NAV(event, "../View/TestBoardView.fxml"); }
	@FXML private void NAV_StasticsView(ActionEvent event) throws IOException { NAV(event, "../View/StasticsView.fxml"); }
	@FXML private void NAV_MypageView(ActionEvent event) throws IOException { NAV(event, "../View/MypageView.fxml"); }
	@FXML private void NAV_MypageEditView(ActionEvent event) throws IOException { NAV(event, "../View/MypageEditView.fxml"); }
	@FXML private void NAV_VideoView(ActionEvent event) throws IOException { NAV(event, "../View/VideoView.fxml"); }
	@FXML
	public void Btn_Edit(ActionEvent event) {
		ButtonType YES = new ButtonType("YES", ButtonBar.ButtonData.OK_DONE);
		Alert alert = new Alert(AlertType.NONE,"성공적으로 수정 되었습니다.", YES);
		alert.setTitle("마이페이지 - 수정 성공");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.orElse(YES) == YES) {
			UserBean userbean = new UserBean();
			try {
				userbean.setUSER_ID_PK(Mypage_UserId.getText().toString());
				userbean.setUSER_PASSWORD(sha256.sha256(EditProperty_UserPassword.getText()));
				userbean.setUSER_NAME(EditProperty_UserName.getText().toString());
				userbean.setUSER_ADDRESS(EditProperty_UserAddress.getText().toString());
				userbean.setUSER_SCHOOLNAME(EditProperty_UserSchoolName.getText().toString());
				userbean.setUSER_AGE(EditProperty_UserAge.getText().toString());
				userbean.setUSER_GENDER(EditProperty_UserGender.getText().toString());
				userbean.setUSER_PHONE(EditProperty_UserPhone.getText().toString());
				userbean.setUSER_FMPHONE(EditProperty_UserFmphone.getText().toString());
				UserDataUpdate userdataupdate = new UserDataUpdate();
				userdataupdate.UserUpdate(userbean, Mypage_UserId.getText().toString());
				NAV(event, "../View/MypageView.fxml");
			}catch(Exception e) { }
		}
	}
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
			SelectNowUser selectnowuser = new SelectNowUser();
			UserBean userbean;
			userbean  = selectnowuser.getSelectUser(LoginViewController.login_id);
			Mypage_UserId.setText(userbean.getUSER_ID_PK());
			EditProperty_UserPassword.setText(userbean.getUSER_PASSWORD());
			EditProperty_UserName.setText(userbean.getUSER_NAME());
			EditProperty_UserAddress.setText(userbean.getUSER_ADDRESS());
			EditProperty_UserSchoolName.setText(userbean.getUSER_SCHOOLNAME());
			EditProperty_UserAge.setText(userbean.getUSER_AGE());
			EditProperty_UserGender.setText(userbean.getUSER_GENDER());
			EditProperty_UserPhone.setText(userbean.getUSER_PHONE());
			EditProperty_UserFmphone.setText(userbean.getUSER_FMPHONE());
			}catch (Exception e) {
				// TODO: handle exception
			}
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
