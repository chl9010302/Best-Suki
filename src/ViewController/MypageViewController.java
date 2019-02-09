package ViewController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import DBController.SelectNowUser;
import DBModel.UserBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class MypageViewController implements Initializable {
	//Declare FXML
	@FXML private Label Mypage_UserId, Mypage_UserPassword, Mypage_UserName,  Mypage_UserAddress, Mypage_UserSchoolName, Mypage_UserAge, Mypage_UserGender, Mypage_UserPhone, Mypage_UserFmphone;
	@FXML private void NAV_LoginView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavloginview()); }
	@FXML private void NAV_MainView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmainview());	}
	@FXML private void NAV_TestView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestview()); }
	@FXML private void NAV_TestBoardView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestboardview()); }
	@FXML private void NAV_StasticsView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavstasticsview()); }
	@FXML private void NAV_MypageView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmypageview()); }
	@FXML private void NAV_MypageEditView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmypageeditview()); }
	@FXML private void NAV_VideoView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavvideoview()); }
	@FXML private void logout(ActionEvent event) { CommonController.logout(event, getClass()); }
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
}
