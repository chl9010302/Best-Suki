package application;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import DBModel.UserBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Controller {
	private String UserGender = "";
	private UserBean user; // 회원가입 시 User 정보를 송신하기 위함.
	private Stage stage; // file choose 하기 위함.
	
	@FXML
	private TextField UserId, UserPassword, UserName, UserAddress, 
						UserSchoolName, UserPhone, UserFmphone;
	@FXML private DatePicker UserAge;
	@FXML RadioButton UserGenderMale;
	@FXML RadioButton UserGenderFeMale;
	@FXML private Text result;
	
	@FXML // Move SignupView
	private void NAV_SignUp(ActionEvent event) throws IOException {
		Parent SignupView = FXMLLoader.load(getClass().getResource("SignupView.fxml"));
		Scene SignupView_scene = new Scene(SignupView);
		SignupView_scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(SignupView_scene);
		app_stage.show();
	}
	
	@FXML // Move LoginView
	private void NAV_Login(ActionEvent event) throws IOException {
		Parent LoginView = FXMLLoader.load(getClass().getResource("LoginView.fxml"));
		Scene LoginView_scene = new Scene(LoginView);
		LoginView_scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(LoginView_scene);
		app_stage.show();
	}
	
	@FXML // Move MainView
	private void NAV_Main(ActionEvent event) throws IOException {
		Parent MainView = FXMLLoader.load(getClass().getResource("MainView.fxml"));
		Scene MainView_scene = new Scene(MainView);
		MainView_scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(MainView_scene);
		app_stage.show();
	}
	
	@FXML // 회원가입 버튼 클릭 시 활성화
	private void NAV_Signup(ActionEvent event) throws IOException {
		LocalDate localDate = UserAge.getValue();
		// 회원가입 시 정보가 인터페이스됨.
		user = new UserBean();
		user.setUserId(UserId.getText().toString());
		user.setUserPassword(UserPassword.getText().toString());
		user.setUserName(UserName.getText().toString());
		user.setUserAddress(UserAddress.getText().toString());
		user.setUserSchoolName(UserSchoolName.getText().toString());
		user.setUserAge(localDate.toString());
		user.setUserGender(UserGender);
		user.setUserPhone(UserPhone.getText().toString());
		user.setUserFmphone(UserFmphone.getText().toString());
		
		// 회원가입과 함께 Login Page로 이동됨.
		Parent MainView = FXMLLoader.load(getClass().getResource("LoginView.fxml"));
		Scene MainView_scene = new Scene(MainView);
		MainView_scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(MainView_scene);
		app_stage.show();
	}
	public void radioSelect(ActionEvent event) {
		if(UserGenderMale.isSelected()) { UserGender = UserGenderMale.getText(); }
		if(UserGenderFeMale.isSelected()) { UserGender = UserGenderFeMale.getText(); }
	}
	
	public void fileChooserSelect(ActionEvent event) { openFile(); }
	
	//fileChoose function
	public void openFile() {
		FileChooser fileChooser = new FileChooser();
		File file = fileChooser.showOpenDialog(stage);
		if(file != null) {
			System.out.println("File Chosen : " + file);
		}
	}
	
}
