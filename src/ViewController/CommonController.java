package ViewController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;

import DBController.UserLogin;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class CommonController{
	// 마우스를 눌러서 화면 이동 액션
	public static void NAV (Class getclass, ActionEvent event, String str) throws IOException {
		Parent View = FXMLLoader.load(getclass.getResource(str));
		Scene View_scene = new Scene(View);
		View_scene.getStylesheets().add(getclass.getResource(config.StaticProperty.getnavapplication()).toExternalForm());
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(View_scene);
		app_stage.show();
	}
	// 키를 눌러서 화면 이동 액션
	public static void NAV_Key (Class getclass, KeyEvent event, String str) throws IOException {
		Parent View = FXMLLoader.load(getclass.getResource(str));
		Scene View_scene = new Scene(View);
		View_scene.getStylesheets().add(getclass.getResource(config.StaticProperty.getnavapplication()).toExternalForm());
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(View_scene);
		app_stage.show();
	}
	// 팝업하여 화면 이동 액션
	public static void NAV_POPUP (Class getclass, ActionEvent event, String str) throws IOException {
		Parent View = FXMLLoader.load(getclass.getResource(str));
		Scene View_scene = new Scene(View);
		View_scene.getStylesheets().add(getclass.getResource(config.StaticProperty.getnavapplication()).toExternalForm());
		Stage app_stage = new Stage();
		app_stage.setScene(View_scene);
		app_stage.show();
	}
	// 로그아웃 액션
	public static void logout(Class getclass, ActionEvent event) {
		UserLogin userlogout = new UserLogin();
		ButtonType YES = new ButtonType(config.StaticProperty.alertbtnyes(), ButtonBar.ButtonData.OK_DONE);
		ButtonType NO = new ButtonType(config.StaticProperty.alertbtnno(), ButtonBar.ButtonData.CANCEL_CLOSE);
		Alert alert = new Alert(AlertType.NONE,config.StaticProperty.alertlogout(), YES, NO);
		alert.setTitle(config.StaticProperty.alerttitlelogout());
		Optional<ButtonType> result = alert.showAndWait();
		if (result.orElse(NO) == YES) {
			try {
				userlogout.logout(LoginViewController.login_id);
				userlogout.logout2(LoginViewController.login_id);
				NAV(getclass, event, config.StaticProperty.getnavloginview());
			}catch(Exception e) { }
		}
	}
	public static String MakeId() {
		Calendar calendar = Calendar.getInstance();
		java.util.Date date = calendar.getTime();
		String dateidpk = (new SimpleDateFormat("yyyyMMddHHmmss").format(date));
		return dateidpk;
	}
	public static String gender(Boolean selectedmale, Boolean selectedfemale) {
		if(selectedmale == true) {
			return "Male";
		}else if(selectedfemale == true){
			return "Female";
		}
		return null;
	}
	public static void Alert_YesorNo(ActionEvent event, String alertcontent, String alerttitle, Class getclass, String str){
		ButtonType YES = new ButtonType(config.StaticProperty.alertbtnyes(), ButtonBar.ButtonData.OK_DONE);
		ButtonType NO = new ButtonType(config.StaticProperty.alertbtnno(), ButtonBar.ButtonData.CANCEL_CLOSE);
		Alert alert = new Alert(AlertType.NONE,alertcontent, YES, NO);
		alert.setTitle(alerttitle);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.orElse(NO) == YES) {
			try {
				NAV(getclass, event, str);
			}catch(Exception e) { }
		}
	}
	public static void Alert_ERROR(ActionEvent event, String alertcontent, String alerttitle) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(alerttitle);
		alert.setHeaderText(alertcontent);
		alert.showAndWait();
	}
	public static void Alert_ERROR_Key(KeyEvent event, String alertcontent, String alerttitle) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(alerttitle);
		alert.setHeaderText(alertcontent);
		alert.showAndWait();
	}
	public static String MakeMobilenumber(String midnumber, String lastnumber) {
		String result = "";
		result = "010-" + midnumber + "-" + lastnumber;
		return result;
	}
}
