package ViewController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import DBController.VideoDetailAdd;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
public class VideoDetailViewController  implements Initializable {
	//Declare JAVA
	public static String login_id = LoginViewController.login_id;
	private WebEngine engine;
	private String getfilepath;
	public static  String URL;
	private VideoDetailAdd videodetailadd;
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
	@FXML private void NAV_FullScreenVideo(ActionEvent event) throws IOException { CommonController.NAV_POPUP(getClass(), event, config.StaticProperty.getnavfullscreenvideoview()); }
	@FXML private void logout(ActionEvent event) { CommonController.logout(getClass(), event); }
	@FXML private void editAction(ActionEvent event) { CommonController.Alert_YesorNo(event, config.StaticProperty.alertedit(), config.StaticProperty.alerttitlecancel(), getClass(), config.StaticProperty.getnavvideodetaileditview()); }
	public void initialize(URL url, ResourceBundle rb) {
		videodetailadd = new VideoDetailAdd();
		getfilepath = videodetailadd.selectContent(VideoDetailAdd.videodetail_id, "VIDEODETAIL_FILEPATH");
		URL = "https://www.youtube.com/embed/" + getfilepath.substring(32) + "?wmode=transparent";
		videodetail_subtitle.setText(videodetailadd.selectContent(VideoDetailAdd.videodetail_id, "VIDEODETAIL_SUBTITLE"));
		engine = Myweb.getEngine();
		engine.load(URL);
	}
}
