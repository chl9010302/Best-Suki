package ViewController;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import DBController.VideoDetailAdd;
import DBModel.VideoDetailBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class VideoDetailEditViewController implements Initializable {
	//Declare JAVA
	private VideoDetailBean videodetailbean;
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
	@FXML
	private void cancelAction(ActionEvent event) {
		ButtonType YES = new ButtonType(config.StaticProperty.alertbtnyes(), ButtonBar.ButtonData.OK_DONE);
		ButtonType NO = new ButtonType(config.StaticProperty.alertbtnno(), ButtonBar.ButtonData.CANCEL_CLOSE);
		Alert alert = new Alert(AlertType.NONE,config.StaticProperty.alertcancel(), YES, NO);
		alert.setTitle(config.StaticProperty.alerttitlecancel());
		Optional<ButtonType> result = alert.showAndWait();
		if (result.orElse(NO) == YES) {
			try {
				CommonController.NAV(getClass(), event, config.StaticProperty.getnavvideodetailview());
			}catch(Exception e) { }
		}
	}
	@FXML
	private void editAction(ActionEvent event) {
		try {
			videodetailbean = new VideoDetailBean();
			videodetailbean.setVIDEODETAIL_ID_PK(txtSubtitle.getText().toString());
			videodetailbean.setVIDEODETAIL_SUBTITLE(txtSubtitle.getText().toString());
			videodetailbean.setVIDEODETAIL_FILEPATH(txtFilepath.getText().toString());
			VideoDetailAdd videodetailadd = new VideoDetailAdd();
			videodetailadd.updateVideoDetail(videodetailbean);
		}catch(Exception e) { }
		try {
			CommonController.NAV(getClass(), event, config.StaticProperty.getnavvideodetailview());
		}catch(Exception e) { }
	}
	public void initialize(URL url, ResourceBundle rb) {
		VideoDetailAdd videodetailadd = new VideoDetailAdd();
		txtSubtitle.setText(videodetailadd.selectSubtitle(videodetailadd.videodetail_id));
		txtFilepath.setText(videodetailadd.selectFilepath(videodetailadd.videodetail_id));
	}
}
