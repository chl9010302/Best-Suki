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
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import textmessage.Sendmsg;

public class LoginViewController implements Initializable {
	//Declare JAVA
	public static String login_id;
	private UserLogin userlogin;
	private int check_loginsession;
	private Sha256 sha256 = new Sha256();
	private String inDate;
	//Declare FXML
	@FXML private TextField UserId, UserPassword;
	@FXML private void NAV_SignUpView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavsignupview()); }
	@FXML private void NAV_LoginView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavloginview()); }
	@FXML private void NAV_MainView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmainview());	}
	@FXML // 회원가입 버튼 클릭 시 활성화
	private void login(ActionEvent event) {
		userlogin = new UserLogin();
		inDate = new java.text.SimpleDateFormat("MM월 dd일 HH시 mm분").format(new java.util.Date());
		try {
			check_loginsession = userlogin.loginCheck(UserId.getText().toString(), sha256.sha256(UserPassword.getText()));
			login_id = UserId.getText().toString(); // 로그아웃 시 아이디를 기억하기 위함
			if(check_loginsession == 1) {
//				Sendmsg.func_Sendmsg(CommonController.selectcontent(login_id, "USER_NAME" , config.StaticProperty.getuser_tb(), "USER_ID_PK") + config.StaticProperty.msglogin() + inDate , CommonController.selectcontent(login_id, "USER_FMPHONE" , config.StaticProperty.getuser_tb(), "USER_ID_PK"));
				CommonController.NAV(getClass(), event, config.StaticProperty.getnavmainview());
			}
			else {
				login_id ="";
				CommonController.Alert_ERROR(event, config.StaticProperty.alertfailedtologin(), config.StaticProperty.alertfailedtologin());
			}
		} catch (NoSuchAlgorithmException e) { e.printStackTrace();
		} catch (UnsupportedEncodingException e) { e.printStackTrace();
		} catch (Exception e) { e.printStackTrace(); }
	}
	public void setOnKeyPressed(KeyEvent event) {
		if(event.getCode().toString().equals("ENTER"))
		{
			userlogin = new UserLogin();
			try {
				check_loginsession = userlogin.loginCheck(UserId.getText().toString(), sha256.sha256(UserPassword.getText()));
				login_id = UserId.getText().toString(); // 로그아웃 시 아이디를 기억하기 위함
				if(check_loginsession == 1) {
					CommonController.NAV_Key(getClass(), event, config.StaticProperty.getnavmainview());
				}
				else {
					login_id ="";
					CommonController.Alert_ERROR_Key(event, config.StaticProperty.alertfailedtologin(), config.StaticProperty.alertfailedtologin());
				}
			}catch (NoSuchAlgorithmException e) {	
			}catch (UnsupportedEncodingException e) {
			}catch (Exception e) { }
	    }
	}
	public void initialize(URL url, ResourceBundle rb) { }
}