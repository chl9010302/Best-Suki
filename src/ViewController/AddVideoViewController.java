package ViewController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import DBController.VideoDetailAdd;
import DBModel.VideoDetailBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddVideoViewController implements Initializable {
	//Declare JAVA
	private VideoDetailBean videodetailbean;
	private VideoDetailAdd videodetailadd;
	public static String login_id = LoginViewController.login_id;
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
	@FXML private void deleteAction(ActionEvent event) { CommonController.Alert_YesorNo(event, config.StaticProperty.alertgoback(), config.StaticProperty.alerttitlecancel(), getClass(), config.StaticProperty.getnavvideoview()); }
	@FXML
	private void addAction(ActionEvent event) {
		if(txtFilepath.getText().toString().contains("www.youtube.com")) {
			try {
				videodetailbean = new VideoDetailBean();
				videodetailadd = new VideoDetailAdd();
				videodetailbean.setVIDEODETAIL_ID_PK(CommonController.MakeId());
				videodetailbean.setVIDEODETAIL_SUBTITLE(txtSubtitle.getText().toString());
				videodetailbean.setVIDEODETAIL_WRITER(login_id);
				videodetailbean.setVIDEODETAIL_FILEPATH(txtFilepath.getText().toString());
				videodetailadd.insertVideodetail(videodetailbean);
				((Stage) ((Node) event.getSource()).getScene().getWindow()).close(); // 창 닫음.
			}catch(Exception e) { }
		} else {
			CommonController.Alert_ERROR(event, config.StaticProperty.alerttitlenovideopath(), config.StaticProperty.alertputvideopath());
		}
	}
	public void initialize(URL url, ResourceBundle rb) {
	}
}