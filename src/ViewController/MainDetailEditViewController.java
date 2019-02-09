package ViewController;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import DBController.NoticeDetailAdd;
import DBModel.NoticeDetailBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MainDetailEditViewController implements Initializable {
	//Declare JAVA
	private NoticeDetailBean noticedetailbean;
	public static String login_id = LoginViewController.login_id;
	//Declare FXML
	@FXML private Button Property_userID, BtnAdd, BtnDelete;
	@FXML private TextField txtSubtitle, txtContext; 
	@FXML private Label txtFilepath;
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
				CommonController.NAV(getClass(), event, config.StaticProperty.getnavmaindetailview());
			}catch(Exception e) { }
		}
	}
	@FXML
	private void addAction(ActionEvent event) {
		try {
			noticedetailbean = new NoticeDetailBean();
			noticedetailbean.setNOTICEDETAIL_ID_PK(txtSubtitle.getText().toString());
			noticedetailbean.setNOTICEDETAIL_SUBTITLE(txtSubtitle.getText().toString());
			noticedetailbean.setNOTICEDETAIL_CONTEXT(txtContext.getText().toString());
			NoticeDetailAdd noticedetailadd = new NoticeDetailAdd();
			noticedetailadd.updateNoticeDetail(noticedetailbean);
		}catch(Exception e) { }
		try {
			CommonController.NAV(getClass(), event, config.StaticProperty.getnavmaindetailview());
		}catch(Exception e) { }
	}
	public void initialize(URL url, ResourceBundle rb) {
		NoticeDetailAdd noticedetailadd = new NoticeDetailAdd();
		txtSubtitle.setText(noticedetailadd.selectSubtitle(noticedetailadd.noticedetail_id));
		txtContext.setText(noticedetailadd.selectContext(noticedetailadd.noticedetail_id));
	}
}
