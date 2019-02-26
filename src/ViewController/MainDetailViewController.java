package ViewController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import DBController.NoticeDetailAdd;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MainDetailViewController implements Initializable {
	//Declare FXML
	@FXML private Label maindetail_subtitle, maindetail_txtContext;
	@FXML private void NAV_LoginView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavloginview()); }
	@FXML private void NAV_MainView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmainview());	}
	@FXML private void NAV_TestView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestview()); }
	@FXML private void NAV_TestBoardView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestboardview()); }
	@FXML private void NAV_StasticsView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavstasticsview()); }
	@FXML private void NAV_MypageView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmypageview()); }
	@FXML private void NAV_VideoView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavvideoview()); }
	@FXML private void logout(ActionEvent event) { CommonController.logout(getClass(), event); }
	@FXML private void editAction(ActionEvent event) { CommonController.Alert_YesorNo(event, config.StaticProperty.alertedit(), config.StaticProperty.alerttitlecancel(), getClass(), config.StaticProperty.getnavmaindetaileditview()); }
	public void initialize(URL url, ResourceBundle rb) {
		maindetail_subtitle.setText(CommonController.selectcontent(NoticeDetailAdd.noticedetail_id, "NOTICEDETAIL_SUBTITLE", config.StaticProperty.getnoticedetail_tb(), "NOTICEDETAIL_ID_PK"));
		maindetail_txtContext.setText(CommonController.selectcontent(NoticeDetailAdd.noticedetail_id, "NOTICEDETAIL_CONTEXT", config.StaticProperty.getnoticedetail_tb(), "NOTICEDETAIL_ID_PK"));
	}
}
