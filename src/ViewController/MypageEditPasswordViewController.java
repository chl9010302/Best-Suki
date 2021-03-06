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
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

public class MypageEditPasswordViewController implements Initializable {
	//Declare JAVA
	private Sha256 sha256 = new Sha256();
	private ButtonType YES;
	private Alert alert, alert2;
	private UserBean userbean;
	private UserDataUpdate userdataupdate;
	private SelectNowUser selectnowuser;
	private Optional<ButtonType> result2, result;
	//Declare FXML
	@FXML private Label Mypage_UserId, EditProperty_UserName,  EditProperty_UserAddress, EditProperty_UserSchoolName, EditProperty_UserAge, EditProperty_UserGender, EditProperty_UserPhone, EditProperty_UserFmphone;
	@FXML private PasswordField EditProperty_UserPassword;
	@FXML private void NAV_MainView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmainview());	}
	@FXML private void NAV_TestView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestview()); }
	@FXML private void NAV_TestBoardView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestboardview()); }
	@FXML private void NAV_StatisticsView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavstatisticsview()); }
	@FXML private void NAV_MypageView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmypageview()); }
	@FXML private void NAV_MypageEditView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmypageeditview()); }
	@FXML private void NAV_VideoView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavvideoview()); }
	@FXML private void logout(ActionEvent event) { CommonController.logout(getClass(), event); }
	@FXML
	public void editPassword(ActionEvent event) {
		YES = new ButtonType(config.StaticProperty.alertbtndone(), ButtonBar.ButtonData.OK_DONE);
		alert = new Alert(AlertType.NONE,config.StaticProperty.alertcompletetoedit(), YES);
		alert.setTitle(config.StaticProperty.alertcompletetoedit());
		if(EditProperty_UserPassword.getText().equals("")) {
			alert2 = new Alert(AlertType.NONE,config.StaticProperty.alertpasswordnotchanged(), YES);
			alert2.setTitle(config.StaticProperty.alertpasswordnotchanged());
			result2 = alert2.showAndWait();
		}else {
			result = alert.showAndWait();
			if (result.orElse(YES) == YES) {
				userbean = new UserBean();
				userdataupdate = new UserDataUpdate();
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
					userdataupdate.UserUpdate(userbean, Mypage_UserId.getText().toString());
					CommonController.NAV(getClass(), event, config.StaticProperty.getnavmypageview());
				}catch(Exception e) { }
			}
		}
	}
	public void initialize(URL url, ResourceBundle rb) {
		try {
			selectnowuser = new SelectNowUser();
			userbean = new UserBean();
			userbean  = selectnowuser.getSelectUser(LoginViewController.login_id);
			Mypage_UserId.setText(userbean.getUSER_ID_PK());
			EditProperty_UserPassword.setText("");
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
