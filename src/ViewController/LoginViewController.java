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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class LoginViewController implements Initializable {
	//Declare JAVA
	public static String login_id;
	Sha256 sha256 = new Sha256();
	//Declare FXML
	@FXML private TextField UserId, UserPassword;
	@FXML private void NAV_SignUpView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavsignupview()); }
	@FXML private void NAV_LoginView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavloginview()); }
	@FXML private void NAV_MainView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmainview());	}
	@FXML // 회원가입 버튼 클릭 시 활성화
	private void login(ActionEvent event) {
		UserLogin userlogin = new UserLogin();
		try {
			int i = userlogin.loginCheck(UserId.getText().toString(), sha256.sha256(UserPassword.getText()));
			login_id = UserId.getText().toString(); // 로그아웃 시 아이디를 기억하기 위함
			if(i == 1) {
				CommonController.NAV(getClass(), event, config.StaticProperty.getnavmainview());
			}
			else {
				login_id ="";
				CommonController.Alert_ERROR(event, config.StaticProperty.alerttitlemessage(), config.StaticProperty.alertfailedtologin());
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
	public void setOnKeyPressed(KeyEvent event) {
		if(event.getCode().toString().equals("ENTER"))
		{
			UserLogin userlogin = new UserLogin();
			try {
				int i = userlogin.loginCheck(UserId.getText().toString(), sha256.sha256(UserPassword.getText()));
				login_id = UserId.getText().toString(); // 로그아웃 시 아이디를 기억하기 위함
				if(i == 1) {
					CommonController.NAV_Key(getClass(), event, config.StaticProperty.getnavmainview());
				}
				else {
					login_id ="";
					CommonController.Alert_ERROR_Key(event, config.StaticProperty.alerttitlemessage(), config.StaticProperty.alertfailedtologin());
				}
			}catch (NoSuchAlgorithmException e) {	
			}catch (UnsupportedEncodingException e) {
			}catch (Exception e) { }
	    }
	}
	public void initialize(URL url, ResourceBundle rb) { }
}