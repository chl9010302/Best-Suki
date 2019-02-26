package ViewController;

import java.io.IOException;

import DBController.NoticeDetailAdd;
import DBModel.NoticeDetailBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
public class AddMainViewController{
	//Declare JAVA
	private NoticeDetailBean noticedetailbean;
	private NoticeDetailAdd noticedetailadd;
	public static String login_id = LoginViewController.login_id;
	//Declare FXML
	@FXML private TextField txtSubtitle, txtContext; 
	@FXML private void NAV_LoginView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavloginview()); }
	@FXML private void NAV_MainView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmainview());	}
	@FXML private void NAV_TestView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestview()); }
	@FXML private void NAV_TestBoardView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestboardview()); }
	@FXML private void NAV_StasticsView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavstasticsview()); }
	@FXML private void NAV_MypageView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmypageview()); }
	@FXML private void NAV_VideoView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavvideoview()); }
	@FXML private void logout(ActionEvent event) { CommonController.logout(getClass(), event); }
	@FXML private void deleteAction(ActionEvent event) { CommonController.Alert_YesorNo(event, config.StaticProperty.alertcancel(), config.StaticProperty.alerttitlecancel(), getClass(), config.StaticProperty.getnavmainview()); }
	@FXML
	private void addAction(ActionEvent event) {
		try {
			noticedetailbean = new NoticeDetailBean();
			noticedetailadd = new NoticeDetailAdd();
			noticedetailbean.setNOTICEDETAIL_ID_PK(CommonController.MakeId());
			noticedetailbean.setNOTICEDETAIL_SUBTITLE(txtSubtitle.getText().toString());
			noticedetailbean.setNOTICEDETAIL_WRITER(login_id);
			noticedetailbean.setNOTICEDETAIL_CONTEXT(txtContext.getText().toString());
			noticedetailadd.insertNoticeDetail(noticedetailbean);
			CommonController.NAV(getClass(), event, config.StaticProperty.getnavmainview());
		}catch(Exception e) { }
	}
}