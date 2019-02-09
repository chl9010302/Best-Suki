package ViewController;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import DBController.SelectNowUser;
import DBController.UserDataUpdate;
import DBModel.UserBean;
import academyutil.Sha256;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MypageEditViewController implements Initializable {
	//Declare JAVA
	Sha256 sha256 = new Sha256();
	
	//Declare FXML
	@FXML private Button Property_userID;
	@FXML private Label Mypage_UserId, EditProperty_UserPassword;
	@FXML private TextField EditProperty_UserName,  EditProperty_UserAddress, EditProperty_UserSchoolName, EditProperty_UserAge, EditProperty_UserGender, EditProperty_UserPhone, EditProperty_UserFmphone;
	@FXML private void NAV_MainView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmainview());	}
	@FXML private void NAV_TestView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestview()); }
	@FXML private void NAV_TestBoardView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestboardview()); }
	@FXML private void NAV_StasticsView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavstasticsview()); }
	@FXML private void NAV_MypageView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmypageview()); }
	@FXML private void NAV_MypageEditView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmypageeditview()); }
	@FXML private void NAV_VideoView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavvideoview()); }
	@FXML
	public void Btn_Edit(ActionEvent event) {
		ButtonType YES = new ButtonType(config.StaticProperty.alertbtndone(), ButtonBar.ButtonData.OK_DONE);
		Alert alert = new Alert(AlertType.NONE,config.StaticProperty.alertcompletetoedit(), YES);
		alert.setTitle(config.StaticProperty.alertcompletetoedit());
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
				CommonController.NAV(getClass(), event, config.StaticProperty.getnavmypageview());
			}catch(Exception e) { }
		}
	}
	@FXML
	public void Btn_EditPassword(ActionEvent event) {
		try {
			CommonController.NAV(getClass(), event, config.StaticProperty.getnavmypageeditpasswordview());
		} catch (IOException e) { }
	}
	@FXML private void logout(ActionEvent event) { CommonController.logout(event, getClass()); }
	public void initialize(URL url, ResourceBundle rb) {
		try {
			SelectNowUser selectnowuser = new SelectNowUser();
			UserBean userbean;
			userbean  = selectnowuser.getSelectUser(LoginViewController.login_id);
			Mypage_UserId.setText(userbean.getUSER_ID_PK());
			EditProperty_UserPassword.setText("***************");
			EditProperty_UserName.setText(userbean.getUSER_NAME());
			EditProperty_UserAddress.setText(userbean.getUSER_ADDRESS());
			EditProperty_UserSchoolName.setText(userbean.getUSER_SCHOOLNAME());
			EditProperty_UserAge.setText(userbean.getUSER_AGE());
			EditProperty_UserGender.setText(userbean.getUSER_GENDER());
			EditProperty_UserPhone.setText(userbean.getUSER_PHONE());
			EditProperty_UserFmphone.setText(userbean.getUSER_FMPHONE());
			}catch (Exception e) { }
	}
}
