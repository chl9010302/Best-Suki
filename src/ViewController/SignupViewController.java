package ViewController;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import DBController.UserJoin;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class SignupViewController implements Initializable {
	//Declare JAVA
	private UserBean user; // 회원가입 시 User 정보를 송신하기 위함.
	private String usergender = "";
	private String UserPhone, UserFmPhone;
	private LocalDate localDate;
	private UserJoin userjoin;
	private ButtonType YES;
	private Alert alert;
	private Optional<ButtonType> result;
	Sha256 sha256 = new Sha256();
	//Declare FXML
	@FXML private ToggleGroup GenderGroup;
	@FXML private TextField UserId, UserPassword, UserPasswordConfirm, UserName, UserAddress, UserSchoolName, UserPhone_Mid, UserPhone_End, UserFmphone_Mid, UserFmphone_End;
	@FXML private DatePicker UserAge;
	@FXML private RadioButton UserGenderMale, UserGenderFeMale;
	@FXML private void NAV_SignUpView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavsignupview()); }
	@FXML private void NAV_LoginView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavloginview()); }
	@FXML
	private void Signup(ActionEvent event) throws IOException, NoSuchAlgorithmException {
		localDate = UserAge.getValue();
		user = new UserBean();
		// 회원가입 시 정보가  인터페이스됨.
		UserPhone = CommonController.MakeMobilenumber(UserPhone_Mid.getText().toString(), UserPhone_End.getText().toString());
		UserFmPhone = CommonController.MakeMobilenumber(UserFmphone_Mid.getText().toString(), UserFmphone_End.getText().toString());
		user.setUSER_ID_PK(UserId.getText().toString());
		user.setUSER_PASSWORD(sha256.sha256(UserPassword.getText()));
		user.setUSER_NAME(UserName.getText().toString());
		user.setUSER_ADDRESS(UserAddress.getText().toString());
		user.setUSER_SCHOOLNAME(UserSchoolName.getText().toString());
		user.setUSER_AGE(localDate.toString());
		user.setUSER_GENDER(usergender);
		user.setUSER_PHONE(UserPhone);
		user.setUSER_FMPHONE(UserFmPhone);
		user.setUSER_LOGINSESSION("0");
		user.setUSER_TEACHERSESSION("0");
		// 회원가입과 함께 Login Page로 이동됨.
		try {
			userjoin = new UserJoin();
			int i = userjoin.joinCheck(UserId.getText().toString());
			if(i == 1) {
				YES = new ButtonType(config.StaticProperty.alertbtndone(), ButtonBar.ButtonData.OK_DONE);
				alert = new Alert(AlertType.NONE,config.StaticProperty.alertcompletetosignup(), YES);
				alert.setTitle(config.StaticProperty.alertcompletetosignup());
				result = alert.showAndWait();
				if (result.orElse(YES) == YES) {
					userjoin = new UserJoin(user);
					CommonController.NAV(getClass(), event, config.StaticProperty.getnavloginview());
				}
			}
			else {
				CommonController.Alert_ERROR(event, config.StaticProperty.alerttitlemessage(), config.StaticProperty.alertfailedtosignup());
			}
		}catch(Exception e) { }
	}
	public void radioSelect(ActionEvent action) {
		usergender = CommonController.gender(UserGenderMale.isSelected(), UserGenderFeMale.isSelected());
	}
	public void initialize(URL url, ResourceBundle rb) {
	}
}
