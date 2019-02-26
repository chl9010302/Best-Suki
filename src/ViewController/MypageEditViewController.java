package ViewController;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class MypageEditViewController implements Initializable {
	//Declare JAVA
	Sha256 sha256 = new Sha256();
	private String usergender = "";
	private String EditProperty_UserPhone, EditProperty_UserFmphone;
	private ButtonType YES;
	private Alert alert;
	private Optional<ButtonType> result;
	private UserBean userbean;
	private UserDataUpdate userdataupdate;
	private SelectNowUser selectnowuser;
	private LocalDate localdate;
	//Declare FXML
	@FXML private Label Mypage_UserId, EditProperty_UserPassword;
	@FXML private TextField EditProperty_UserName,  EditProperty_UserAddress, EditProperty_UserSchoolName, EditProperty_UserAge, EditProperty_UserGender, EditProperty_UserPhone_Mid, EditProperty_UserPhone_End, EditProperty_UserFmphone_Mid, EditProperty_UserFmphone_End;
	@FXML private RadioButton rb_male, rb_female;
	@FXML private DatePicker dp_birth;
	@FXML private void NAV_MainView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmainview());	}
	@FXML private void NAV_TestView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestview()); }
	@FXML private void NAV_TestBoardView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestboardview()); }
	@FXML private void NAV_StatisticsView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavstatisticsview()); }
	@FXML private void NAV_MypageView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmypageview()); }
	@FXML private void NAV_MypageEditView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmypageeditview()); }
	@FXML private void NAV_VideoView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavvideoview()); }
	@FXML private void logout(ActionEvent event) { CommonController.logout(getClass(), event); }
	@FXML private void cancelAction(ActionEvent event) { CommonController.Alert_YesorNo(event, config.StaticProperty.alertgoback(), config.StaticProperty.alerttitlecancel(), getClass(), config.StaticProperty.getnavmypageview()); }
	@FXML public void editpasswordAction(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmypageeditpasswordview()); }
	@FXML
	public void editAction(ActionEvent event) {
		usergender = CommonController.gender(rb_male.isSelected(), rb_female.isSelected());
		YES = new ButtonType(config.StaticProperty.alertbtndone(), ButtonBar.ButtonData.OK_DONE);
		alert = new Alert(AlertType.NONE,config.StaticProperty.alertcompletetoedit(), YES);
		alert.setTitle(config.StaticProperty.alertcompletetoedit());
		result = alert.showAndWait();
		if (result.orElse(YES) == YES) {
			userbean = new UserBean();
			userdataupdate = new UserDataUpdate();
			EditProperty_UserPhone = CommonController.MakeMobilenumber(EditProperty_UserPhone_Mid.getText().toString(), EditProperty_UserPhone_End.getText().toString());
			EditProperty_UserFmphone = CommonController.MakeMobilenumber(EditProperty_UserFmphone_Mid.getText().toString(), EditProperty_UserFmphone_End.getText().toString());
			try {
				userbean.setUSER_ID_PK(Mypage_UserId.getText().toString());
				userbean.setUSER_NAME(EditProperty_UserName.getText().toString());
				userbean.setUSER_ADDRESS(EditProperty_UserAddress.getText().toString());
				userbean.setUSER_SCHOOLNAME(EditProperty_UserSchoolName.getText().toString());
				userbean.setUSER_AGE(dp_birth.getValue().toString());
				userbean.setUSER_GENDER(usergender);
				userbean.setUSER_PHONE(EditProperty_UserPhone);
				userbean.setUSER_FMPHONE(EditProperty_UserFmphone);
				userdataupdate.UserUpdate(userbean, Mypage_UserId.getText().toString());
				CommonController.NAV(getClass(), event, config.StaticProperty.getnavmypageview());
			}catch(Exception e) { }
		}
	}
	public void initialize(URL url, ResourceBundle rb) {
		try {
			CommonController.getlistener(EditProperty_UserPhone_Mid);
			CommonController.getlistener(EditProperty_UserPhone_End);
			CommonController.getlistener(EditProperty_UserFmphone_Mid);
			CommonController.getlistener(EditProperty_UserFmphone_End);
			userbean = new UserBean();;
			selectnowuser = new SelectNowUser();
			userbean = selectnowuser.getSelectUser(LoginViewController.login_id);
			Mypage_UserId.setText(userbean.getUSER_ID_PK());
			EditProperty_UserPassword.setText("***************");
			EditProperty_UserName.setText(userbean.getUSER_NAME());
			EditProperty_UserAddress.setText(userbean.getUSER_ADDRESS());
			EditProperty_UserSchoolName.setText(userbean.getUSER_SCHOOLNAME());
			localdate = LocalDate.of(Integer.parseInt(userbean.getUSER_AGE().substring(0, 4)), Integer.parseInt(userbean.getUSER_AGE().substring(5, 7)),Integer.parseInt(userbean.getUSER_AGE().substring(8, 10)));
			dp_birth.setValue(localdate);
			if(userbean.getUSER_GENDER().equals("Male")) {
				rb_male.setSelected(true);
			}else { rb_female.setSelected(true);}
			EditProperty_UserPhone_Mid.setText(userbean.getUSER_PHONE().substring(4, 8));
			EditProperty_UserPhone_End.setText(userbean.getUSER_PHONE().substring(9, 13));
			EditProperty_UserFmphone_Mid.setText(userbean.getUSER_FMPHONE().substring(4, 8));
			EditProperty_UserFmphone_End.setText(userbean.getUSER_FMPHONE().substring(9, 13));
			}catch (Exception e) { }
	}
}
