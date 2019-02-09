package ViewController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import DBController.NoticeDetailAdd;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainViewController implements Initializable {
	public static String selectedid= "";
	//Declare FXML
	@FXML private Button Property_userID, BtnDelete, BtnAdd;
	@FXML private TextField txtAddItem;
	@FXML private TableView<NoticeDetailAdd> noticeTableView;
	@FXML private TableColumn<NoticeDetailAdd, String> ColNotice_Id, ColNotice_Subtitle, ColNotice_Writer, ColNotice_Date, ColNotice_Btndetail;
	@FXML private void NAV_LoginView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavloginview()); }
	@FXML private void NAV_MainView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmainview());	}
	@FXML private void NAV_TestView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestview()); }
	@FXML private void NAV_TestBoardView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestboardview()); }
	@FXML private void NAV_StasticsView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavstasticsview()); }
	@FXML private void NAV_MypageView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmypageview()); }
	@FXML private void NAV_VideoView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavvideoview()); }
	@FXML private void logout(ActionEvent event) { CommonController.logout(event, getClass()); }
	@FXML
	private void addAction(ActionEvent event){
		try {
			CommonController.NAV(getClass(), event, config.StaticProperty.getnavaddmainview());
		}catch(Exception e) { }
	}
	@FXML
	private void deleteAction(ActionEvent event){
		NoticeDetailAdd noticedetailadd = new NoticeDetailAdd();
		int selectedItem = noticeTableView.getSelectionModel().getSelectedIndex();
		noticedetailadd.delete(String.valueOf(noticeTableView.getItems().get(selectedItem).getNoticedetail_id_pk().getValue()));
		noticeTableView.setItems(noticedetailadd.getnoticedetailadd());
	}
	public void initialize(URL url, ResourceBundle rb) {
		try {
			NoticeDetailAdd noticedetailadd = new NoticeDetailAdd();
			ColNotice_Subtitle.setCellValueFactory(cellData -> cellData.getValue().getNoticedetail_subtitle());
			ColNotice_Writer.setCellValueFactory(cellData -> cellData.getValue().getNoticedetail_writer());
			ColNotice_Date.setCellValueFactory(cellData -> cellData.getValue().getNoticedetail_time());
			ColNotice_Btndetail.setCellValueFactory(new PropertyValueFactory<NoticeDetailAdd, String>("noticedetail_btndetail"));
			noticeTableView.setItems(noticedetailadd.getnoticedetailadd());
		}catch(Exception e) { }
	}
}
