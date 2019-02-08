package ViewController;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

import DBController.UserLogin;
import academyutil.Sha256;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class LoginViewController implements Initializable {
	//Declare JAVA
	public static String login_id="";
	Sha256 sha256 = new Sha256();
	
	//Declare FXML
	@FXML private TextField UserId, UserPassword;
	@FXML private void NAV_SignUpView(ActionEvent event) throws IOException { NAV(event, config.StaticProperty.getnavsignupview()); }
	@FXML private void NAV_LoginView(ActionEvent event) throws IOException { NAV(event, config.StaticProperty.getnavloginview()); }
	@FXML private void NAV_MainView(ActionEvent event) throws IOException { NAV(event, config.StaticProperty.getnavmainview());	}
	@FXML // 회원가입 버튼 클릭 시 활성화
	private void login(ActionEvent event) {
		UserLogin userlogin = new UserLogin();
		try {
			int i = userlogin.loginCheck(UserId.getText().toString(), sha256.sha256(UserPassword.getText()));
			login_id = UserId.getText().toString(); // 로그아웃 시 아이디를 기억하기 위함
			if(i == 1) {
				NAV(event, config.StaticProperty.getnavmainview());
			}
			else {
				login_id ="";
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Message Here...");
				alert.setHeaderText(config.StaticProperty.alertfailedtologin());
				alert.showAndWait();
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void setOnKeyPressed(KeyEvent ev) {
		if(ev.getCode().toString().equals("ENTER"))
		{
			UserLogin userlogin = new UserLogin();
			try {
				int i = userlogin.loginCheck(UserId.getText().toString(), sha256.sha256(UserPassword.getText()));
				login_id = UserId.getText().toString(); // 로그아웃 시 아이디를 기억하기 위함
				if(i == 1) {
					NAV_Key(ev, config.StaticProperty.getnavmainview());
				}
				else {
					login_id ="";
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Message Here...");
					alert.setHeaderText(config.StaticProperty.alertfailedtologin());
					alert.showAndWait();
				}
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			}
	    }
	}
	public void initialize(URL url, ResourceBundle rb) {
	}
	private void NAV (ActionEvent event, String str) throws IOException {
		Parent SignupView = FXMLLoader.load(getClass().getResource(str));
		Scene SignupView_scene = new Scene(SignupView);
		SignupView_scene.getStylesheets().add(getClass().getResource(config.StaticProperty.getnavapplication()).toExternalForm());
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(SignupView_scene);
		app_stage.show();
		
	}
	private void NAV_Key (KeyEvent event, String str) throws IOException {
		Parent SignupView = FXMLLoader.load(getClass().getResource(str));
		Scene SignupView_scene = new Scene(SignupView);
		SignupView_scene.getStylesheets().add(getClass().getResource(config.StaticProperty.getnavapplication()).toExternalForm());
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(SignupView_scene);
		app_stage.show();
	}
}