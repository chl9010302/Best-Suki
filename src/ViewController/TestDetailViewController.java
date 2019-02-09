package ViewController;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import DBController.TestDetailAdd;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class TestDetailViewController implements Initializable {
	//Declare JAVA
	
	public static String login_id = LoginViewController.login_id;
	//Declare FXML
	@FXML private Label testdetail_subtitle;
	@FXML private ToggleGroup Quest1Group1;
	@FXML private Label Radio1, Radio2, Radio3, Radio4, Radio5;
	@FXML private RadioButton Rb1, Rb2, Rb3, Rb4, Rb5;
	@FXML private void NAV_LoginView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavloginview()); }
	@FXML private void NAV_MainView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmainview());	}
	@FXML private void NAV_TestView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestview()); }
	@FXML private void NAV_TestBoardView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestboardview()); }
	@FXML private void NAV_StasticsView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavstasticsview()); }
	@FXML private void NAV_MypageView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmypageview()); }
	@FXML private void NAV_VideoView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavvideoview()); }
	@FXML private void logout(ActionEvent event) { CommonController.logout(event, getClass()); }
	@FXML
	private void editAction(ActionEvent event) {
		ButtonType YES = new ButtonType(config.StaticProperty.alertbtnyes(), ButtonBar.ButtonData.OK_DONE);
		ButtonType NO = new ButtonType(config.StaticProperty.alertbtnno(), ButtonBar.ButtonData.CANCEL_CLOSE);
		Alert alert = new Alert(AlertType.NONE,config.StaticProperty.alertedit(), YES, NO);
		alert.setTitle(config.StaticProperty.alerttitlecancel());
		Optional<ButtonType> result = alert.showAndWait();
		if (result.orElse(NO) == YES) {
			try {
//				CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestdetaileditview());
			}catch(Exception e) { }
		}
	}
	@FXML
	private void Quest1Group1Action(ActionEvent action) {
	}
	public void initialize(URL url, ResourceBundle rb) {
		TestDetailAdd testdetailadd = new TestDetailAdd();
		testdetail_subtitle.setText(testdetailadd.selectSubtitle(testdetailadd.testdetail_id));
		Radio1.setText(testdetailadd.selectDATA1(testdetailadd.testdetail_id));
		Radio2.setText(testdetailadd.selectDATA2(testdetailadd.testdetail_id));
		Radio3.setText(testdetailadd.selectDATA3(testdetailadd.testdetail_id));
		Radio4.setText(testdetailadd.selectDATA4(testdetailadd.testdetail_id));
		Radio5.setText(testdetailadd.selectDATA5(testdetailadd.testdetail_id));
	}
}
