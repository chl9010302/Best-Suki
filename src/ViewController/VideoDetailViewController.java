package ViewController;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import DBController.VideoDetailAdd;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class VideoDetailViewController implements Initializable {
	//Declare JAVA
	public static String login_id = LoginViewController.login_id;
	private WebEngine engine;
	//Declare FXML
	@FXML private WebView Myweb;
	@FXML private Label videodetail_subtitle, videodetail_filepath;
	@FXML private TextField txtSubtitle, txtFilepath; 
	@FXML private void NAV_LoginView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavloginview()); }
	@FXML private void NAV_MainView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmainview());	}
	@FXML private void NAV_TestView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestview()); }
	@FXML private void NAV_TestBoardView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestboardview()); }
	@FXML private void NAV_StasticsView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavstasticsview()); }
	@FXML private void NAV_MypageView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmypageview()); }
	@FXML private void NAV_VideoView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavvideoview()); }
	@FXML private void logout(ActionEvent event) { CommonController.logout(getClass(), event); }
	@FXML
	private void editAction(ActionEvent event) {
		ButtonType YES = new ButtonType(config.StaticProperty.alertbtnyes(), ButtonBar.ButtonData.OK_DONE);
		ButtonType NO = new ButtonType(config.StaticProperty.alertbtnno(), ButtonBar.ButtonData.CANCEL_CLOSE);
		Alert alert = new Alert(AlertType.NONE,config.StaticProperty.alertedit(), YES, NO);
		alert.setTitle(config.StaticProperty.alerttitlecancel());
		Optional<ButtonType> result = alert.showAndWait();
		if (result.orElse(NO) == YES) {
			try {
				CommonController.NAV(getClass(), event, config.StaticProperty.getnavvideodetaileditview());
			}catch(Exception e) { }
		}
	}
	public void initialize(URL url, ResourceBundle rb) {
		String URL = "";
		VideoDetailAdd videodetailadd = new VideoDetailAdd();
		URL = "https://www.youtube.com/embed/" + videodetailadd.selectFilepath(videodetailadd.videodetail_id).substring(32, videodetailadd.selectFilepath(videodetailadd.videodetail_id).length()) + "?wmode=opaque";
		videodetail_subtitle.setText(videodetailadd.selectSubtitle(videodetailadd.videodetail_id));
		engine = Myweb.getEngine();
		engine.load(URL);
	}
}
