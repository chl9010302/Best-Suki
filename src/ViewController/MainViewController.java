package ViewController;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import DBController.NoticeDetailAdd;
import DBController.UserLogin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MainViewController implements Initializable {
	public static String selectedid= "";
	//Declare FXML
	@FXML private Button Property_userID;
	@FXML private Button BtnDelete;
	@FXML private TextField txtAddItem;
	@FXML private Button BtnAdd;
	@FXML private TableView<NoticeDetailAdd> noticeTableView;
	@FXML private TableColumn<NoticeDetailAdd, String> ColNotice_Id;
	@FXML private TableColumn<NoticeDetailAdd, String> ColNotice_Subtitle;
	@FXML private TableColumn<NoticeDetailAdd, String> ColNotice_Writer;
	@FXML private TableColumn<NoticeDetailAdd, String> ColNotice_Date;
	@FXML private TableColumn<NoticeDetailAdd, String> ColNotice_Btndetail;
	@FXML private void NAV_LoginView(ActionEvent event) throws IOException { NAV(event, config.StaticProperty.getnavloginview()); }
	@FXML private void NAV_MainView(ActionEvent event) throws IOException { NAV(event, config.StaticProperty.getnavmainview());	}
	@FXML private void NAV_TestView(ActionEvent event) throws IOException { NAV(event, config.StaticProperty.getnavtestview()); }
	@FXML private void NAV_TestBoardView(ActionEvent event) throws IOException { NAV(event, config.StaticProperty.getnavtestboardview()); }
	@FXML private void NAV_StasticsView(ActionEvent event) throws IOException { NAV(event, config.StaticProperty.getnavstasticsview()); }
	@FXML private void NAV_MypageView(ActionEvent event) throws IOException { NAV(event, config.StaticProperty.getnavmypageview()); }
	@FXML private void NAV_VideoView(ActionEvent event) throws IOException { NAV(event, config.StaticProperty.getnavvideoview()); }
	@FXML
	private void logout(ActionEvent event) {
		UserLogin userlogout = new UserLogin();
		ButtonType YES = new ButtonType(config.StaticProperty.alertbtnyes(), ButtonBar.ButtonData.OK_DONE);
		ButtonType NO = new ButtonType(config.StaticProperty.alertbtnno(), ButtonBar.ButtonData.CANCEL_CLOSE);
		Alert alert = new Alert(AlertType.NONE,config.StaticProperty.alertlogout(), YES, NO);
		alert.setTitle(config.StaticProperty.alerttitlelogout());
		Optional<ButtonType> result = alert.showAndWait();
		if (result.orElse(NO) == YES) {
			try {
				userlogout.logout(LoginViewController.login_id);
				userlogout.logout2(LoginViewController.login_id);
				NAV(event, config.StaticProperty.getnavloginview());
			}catch(Exception e) { }
		}
	}
	@FXML
	private void addAction(ActionEvent action){
		try {
			NAV(action, config.StaticProperty.getnavaddmainview());
		}catch(Exception e) { }
	}
	@FXML
	private void deleteAction(ActionEvent action){
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
	private void NAV (ActionEvent event, String str) throws IOException {
		Parent View = FXMLLoader.load(getClass().getResource(str));
		Scene View_scene = new Scene(View);
		View_scene.getStylesheets().add(getClass().getResource(config.StaticProperty.getnavapplication()).toExternalForm());
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(View_scene);
		app_stage.show();
	}
}
