package ViewController;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import javafx.stage.Stage;
import usingstaticfunction.DBConnectionKeeping;

public class CommonController{
	public static Parent View;
	public static Scene View_scene;
	public static Stage app_stage;
	public static UserLogin userlogout;
	public static ButtonType YES, NO;
	public static Alert alert;
	public static Optional<ButtonType> result;
	public static Calendar calendar;
	public static String dateidpk, result_phone, sql, result_select, answer;
	public static StringBuilder sb;
	public static int lineCnt, fromIndex;
	public static ArrayList<String> question;
	// 마우스를 눌러서 화면 이동 액션
	public static void NAV (Class getclass, ActionEvent event, String str) throws IOException {
		View = FXMLLoader.load(getclass.getResource(str));
		View_scene = new Scene(View);
		View_scene.getStylesheets().add(getclass.getResource(config.StaticProperty.getnavapplication()).toExternalForm());
		app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(View_scene);
		app_stage.show();
	}
	// 키를 눌러서 화면 이동 액션
	public static void NAV_Key (Class getclass, KeyEvent event, String str) throws IOException {
		View = FXMLLoader.load(getclass.getResource(str));
		View_scene = new Scene(View);
		View_scene.getStylesheets().add(getclass.getResource(config.StaticProperty.getnavapplication()).toExternalForm());
		app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(View_scene);
		app_stage.show();
	}
	// 팝업하여 화면 이동 액션
	public static void NAV_POPUP (Class getclass, ActionEvent event, String str) throws IOException {
		View = FXMLLoader.load(getclass.getResource(str));
		View_scene = new Scene(View);
		View_scene.getStylesheets().add(getclass.getResource(config.StaticProperty.getnavapplication()).toExternalForm());
		app_stage = new Stage();
		app_stage.setScene(View_scene);
		app_stage.show();
	}
	// 로그아웃 액션
	public static void logout(Class getclass, ActionEvent event) {
		userlogout = new UserLogin();
		YES = new ButtonType(config.StaticProperty.alertbtnyes(), ButtonBar.ButtonData.OK_DONE);
		NO = new ButtonType(config.StaticProperty.alertbtnno(), ButtonBar.ButtonData.CANCEL_CLOSE);
		alert = new Alert(AlertType.NONE,config.StaticProperty.alertlogout(), YES, NO);
		alert.setTitle(config.StaticProperty.alerttitlelogout());
		result = alert.showAndWait();
		if (result.orElse(NO) == YES) {
			try {
				userlogout.logout(LoginViewController.login_id);
				userlogout.logout2(LoginViewController.login_id);
				NAV(getclass, event, config.StaticProperty.getnavloginview());
			}catch(Exception e) { }
		}
	}
	public static String MakeId() {
		calendar = Calendar.getInstance();
		java.util.Date date = calendar.getTime();
		dateidpk = (new SimpleDateFormat("yyyyMMddHHmmss").format(date));
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
		YES = new ButtonType(config.StaticProperty.alertbtnyes(), ButtonBar.ButtonData.OK_DONE);
		NO = new ButtonType(config.StaticProperty.alertbtnno(), ButtonBar.ButtonData.CANCEL_CLOSE);
		alert = new Alert(AlertType.NONE,alertcontent, YES, NO);
		alert.setTitle(alerttitle);
		result = alert.showAndWait();
		if (result.orElse(NO) == YES) {
			try {
				NAV(getclass, event, str);
			}catch(Exception e) { }
		}
	}
	public static void Alert_ERROR(ActionEvent event, String alertcontent, String alerttitle) {
		alert = new Alert(AlertType.ERROR);
		alert.setTitle(alerttitle);
		alert.setHeaderText(alertcontent);
		alert.showAndWait();
	}
	public static void Alert_ERROR_Key(KeyEvent event, String alertcontent, String alerttitle) {
		alert = new Alert(AlertType.ERROR);
		alert.setTitle(alerttitle);
		alert.setHeaderText(alertcontent);
		alert.showAndWait();
	}
	public static String MakeMobilenumber(String midnumber, String lastnumber) {
		result_phone = "";
		result_phone = "010-" + midnumber + "-" + lastnumber;
		return result_phone;
	}
	
	public static String selectcontent(String id, String getcontent, String gettable, String find_id) {
		sb = new StringBuilder();
		Statement stmt = null;
		ResultSet rs;
		DBConnectionKeeping dbConnectionKeeping;
		if (usingstaticfunction.DBConnectionKeeping.con == null)
			dbConnectionKeeping = new DBConnectionKeeping();
		try {
			Connection con = usingstaticfunction.DBConnectionKeeping.con;
			sql = sb.append("SELECT " + getcontent + " FROM "+ gettable + " WHERE " + find_id + " = '").append(id).append("';").toString();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				if (rs.getString(getcontent) != null)
					result_select = rs.getString(getcontent);
			}
		}catch(Exception e) { }
		return result_select;
	}
	public static String makeAnswer(ArrayList<String> answer1) {
		answer = "";
		for(int i=0; i<answer1.size(); i++) {
			if(i != answer1.size()-1) {
				answer += answer1.get(i) + ";";
			}else {
				answer += answer1.get(i);
			}
		}
		return answer;
	}
	public static ArrayList<String> splitQuestion(String data) {
		System.out.println(data);
		lineCnt = 0;
	    fromIndex = -1;
	    while ((fromIndex = data.indexOf(";", fromIndex + 1)) >= 0) {
	      lineCnt++;
	    }
		question = new ArrayList<>();
		for(int i = 0; i<=lineCnt; i++) {
			question.add(data.split(";")[i]);	
		}
		return question;
	}
}
