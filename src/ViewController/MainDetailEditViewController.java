package ViewController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import DBController.NoticeDetailAdd;
import DBModel.NoticeDetailBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class MainDetailEditViewController implements Initializable {
	//Declare JAVA
	private NoticeDetailBean noticedetailbean;
	private NoticeDetailAdd noticedetailadd;
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
	@FXML private void cancelAction(ActionEvent event) { CommonController.Alert_YesorNo(event, config.StaticProperty.alertgoback(), config.StaticProperty.alerttitlecancel(), getClass(), config.StaticProperty.getnavmaindetailview()); }
	@FXML
	private void editAction(ActionEvent event) {
		try {
			noticedetailbean = new NoticeDetailBean();
			noticedetailadd = new NoticeDetailAdd();
			noticedetailbean.setNOTICEDETAIL_ID_PK(NoticeDetailAdd.noticedetail_id);
			noticedetailbean.setNOTICEDETAIL_SUBTITLE(txtSubtitle.getText().toString());
			noticedetailbean.setNOTICEDETAIL_CONTEXT(txtContext.getText().toString());
			noticedetailadd.updateNoticeDetail(noticedetailbean);
			CommonController.NAV(getClass(), event, config.StaticProperty.getnavmaindetailview());
		}catch(Exception e) { e.printStackTrace();}
	}
	public void initialize(URL url, ResourceBundle rb) {
		txtSubtitle.setText(CommonController.selectcontent(noticedetailadd.noticedetail_id, "NOTICEDETAIL_SUBTITLE", config.StaticProperty.getnoticedetail_tb(), "NOTICEDETAIL_ID_PK"));
		txtContext.setText(CommonController.selectcontent(noticedetailadd.noticedetail_id, "NOTICEDETAIL_CONTEXT", config.StaticProperty.getnoticedetail_tb(), "NOTICEDETAIL_ID_PK"));
	}
}
