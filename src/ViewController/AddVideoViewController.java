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
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddVideoViewController implements Initializable {
	//Declare JAVA
	private VideoDetailBean videodetailbean;
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
	@FXML private void logout(ActionEvent event) { CommonController.logout(event, getClass()); }
	@FXML
	private void deleteAction(ActionEvent event) {
		ButtonType YES = new ButtonType(config.StaticProperty.alertbtnyes(), ButtonBar.ButtonData.OK_DONE);
		ButtonType NO = new ButtonType(config.StaticProperty.alertbtnno(), ButtonBar.ButtonData.CANCEL_CLOSE);
		Alert alert = new Alert(AlertType.NONE,config.StaticProperty.alertgoback(), YES, NO);
		alert.setTitle(config.StaticProperty.alerttitlecancel());
		Optional<ButtonType> result = alert.showAndWait();
		if (result.orElse(NO) == YES) {
			try {
				CommonController.NAV(getClass(), event, config.StaticProperty.getnavvideoview());
			}catch(Exception e) { }
		}
	}
	@FXML
	private void addAction(ActionEvent event) {
		try {
			videodetailbean = new VideoDetailBean();
			videodetailbean.setVIDEODETAIL_ID_PK(txtSubtitle.getText().toString());
			videodetailbean.setVIDEODETAIL_SUBTITLE(txtSubtitle.getText().toString());
			videodetailbean.setVIDEODETAIL_WRITER(login_id);
			videodetailbean.setVIDEODETAIL_FILEPATH(txtFilepath.getText().toString());
			VideoDetailAdd videodetailadd = new VideoDetailAdd();
			videodetailadd.insertVideodetail(videodetailbean);
			((Stage) ((Node) event.getSource()).getScene().getWindow()).close(); // 창 닫음.
		}catch(Exception e) { }
	}
	public void initialize(URL url, ResourceBundle rb) {
	}
}
