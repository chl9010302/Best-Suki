package ViewController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import DBController.VideoDetailAdd;
import DBModel.VideoDetailBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
public class VideoDetailEditViewController implements Initializable {
	//Declare JAVA
	private VideoDetailBean videodetailbean;
	private VideoDetailAdd videodetailadd;
	//Declare FXML
	@FXML private TextField txtSubtitle, txtFilepath; 
	@FXML private void NAV_LoginView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavloginview()); }
	@FXML private void NAV_MainView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmainview());	}
	@FXML private void NAV_TestView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestview()); }
	@FXML private void NAV_TestBoardView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestboardview()); }
	@FXML private void NAV_StasticsView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavstasticsview()); }
	@FXML private void NAV_MypageView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmypageview()); }
	@FXML private void NAV_VideoView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavvideoview()); }
	@FXML private void logout(ActionEvent event) { CommonController.logout(getClass(), event); }
	@FXML private void cancelAction(ActionEvent event) { CommonController.Alert_YesorNo(event, config.StaticProperty.alertcancel(), config.StaticProperty.alerttitlecancel(), getClass(), config.StaticProperty.getnavvideodetailview()); }
	@FXML
	private void editAction(ActionEvent event) {
		try {
			videodetailadd = new VideoDetailAdd();
			videodetailbean = new VideoDetailBean();
			videodetailbean.setVIDEODETAIL_ID_PK(VideoDetailAdd.videodetail_id);
			videodetailbean.setVIDEODETAIL_SUBTITLE(txtSubtitle.getText().toString());
			videodetailbean.setVIDEODETAIL_FILEPATH(txtFilepath.getText().toString());
			videodetailadd.updateVideoDetail(videodetailbean);
		}catch(Exception e) { }
		try {
			CommonController.NAV(getClass(), event, config.StaticProperty.getnavvideodetailview());
		}catch(Exception e) { }
	}
	public void initialize(URL url, ResourceBundle rb) {
		videodetailadd = new VideoDetailAdd();
		txtSubtitle.setText(videodetailadd.selectContent(VideoDetailAdd.videodetail_id, "VIDEODETAIL_SUBTITLE"));
		txtFilepath.setText(videodetailadd.selectContent(VideoDetailAdd.videodetail_id, "VIDEODETAIL_FILEPATH"));
	}
}