package application;

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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class SignupViewController implements Initializable {
	//Declare JAVA
	private UserBean user; // 회원가입 시 User 정보를 송신하기 위함.
	private String usergender = "";
	Sha256 sha256 = new Sha256();
	//Declare FXML
	@FXML private TextField UserId, UserPassword, UserPasswordConfirm, UserName, UserAddress, UserSchoolName, UserPhone, UserFmphone;
	@FXML private DatePicker UserAge;
	@FXML private RadioButton UserGenderMale;
	@FXML private RadioButton UserGenderFeMale;
	@FXML private void NAV_SignUpView(ActionEvent event) throws IOException { NAV(event, "../View/SignupView.fxml"); }
	@FXML private void NAV_LoginView(ActionEvent event) throws IOException { NAV(event, "../View/LoginView.fxml"); }
	@FXML
	private void Signup(ActionEvent event) throws IOException, NoSuchAlgorithmException {
		LocalDate localDate = UserAge.getValue();
		// 회원가입 시 정보가  인터페이스됨.
		user = new UserBean();
		user.setUSER_ID_PK(UserId.getText().toString());
		user.setUSER_PASSWORD(sha256.sha256(UserPassword.getText()));
		user.setUSER_NAME(UserName.getText().toString());
		user.setUSER_ADDRESS(UserAddress.getText().toString());
		user.setUSER_SCHOOLNAME(UserSchoolName.getText().toString());
		user.setUSER_AGE(localDate.toString());
		user.setUSER_GENDER(usergender);
		user.setUSER_PHONE(UserPhone.getText().toString());
		user.setUSER_FMPHONE(UserFmphone.getText().toString());
		user.setUSER_LOGINSESSION("0");
		user.setUSER_TEACHERSESSION("0");
		// 회원가입과 함께 Login Page로 이동됨.
		try {
			UserJoin userjoin = new UserJoin();
			int i = userjoin.joinCheck(UserId.getText().toString());
			if(i == 1) {
				ButtonType YES = new ButtonType("YES", ButtonBar.ButtonData.OK_DONE);
				Alert alert = new Alert(AlertType.NONE,"회원가입이 성공적으로 되었습니다.", YES);
				alert.setTitle("회원가입 - 성공");
				Optional<ButtonType> result = alert.showAndWait();
				if (result.orElse(YES) == YES) {
					userjoin = new UserJoin(user);
					NAV(event, "../View/LoginView.fxml");
				}
			}
			else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Message Here...");
				alert.setHeaderText("회원가입에 실패하셨습니다.");
				alert.showAndWait();
			}
		}catch(Exception e) { }
	}
	public void radioSelect(ActionEvent action) {
		if(UserGenderMale.isSelected()) { usergender = UserGenderMale.getText(); }
		if(UserGenderFeMale.isSelected()) { usergender = UserGenderFeMale.getText(); }
	}
	public void initialize(URL url, ResourceBundle rb) {
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
