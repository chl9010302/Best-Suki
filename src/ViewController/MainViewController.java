package ViewController;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import DBController.NoticeDetailAdd;
import DBController.UserLogin;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
	@FXML private void NAV_LoginView(ActionEvent event) throws IOException { NAV(event, "../View/LoginView.fxml"); }
	@FXML private void NAV_MainView(ActionEvent event) throws IOException { NAV(event, "../View/MainView.fxml");	}
	@FXML private void NAV_TestView(ActionEvent event) throws IOException { NAV(event, "../View/TestView.fxml"); }
	@FXML private void NAV_TestBoardView(ActionEvent event) throws IOException { NAV(event, "../View/TestBoardView.fxml"); }
	@FXML private void NAV_StasticsView(ActionEvent event) throws IOException { NAV(event, "../View/StasticsView.fxml"); }
	@FXML private void NAV_MypageView(ActionEvent event) throws IOException { NAV(event, "../View/MypageView.fxml"); }
	@FXML private void NAV_VideoView(ActionEvent event) throws IOException { NAV(event, "../View/VideoView.fxml"); }
	@FXML
	private void logout(ActionEvent event) {
		UserLogin userlogout = new UserLogin();
		ButtonType YES = new ButtonType("YES", ButtonBar.ButtonData.OK_DONE);
		ButtonType NO = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);
		Alert alert = new Alert(AlertType.NONE,"Would you want to logout?", YES, NO);
		alert.setTitle("Logout");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.orElse(NO) == YES) {
			try {
				userlogout.logout(LoginViewController.login_id);
				userlogout.logout2(LoginViewController.login_id);
				NAV(event, "../View/LoginView.fxml");
			}catch(Exception e) { }
		}
	}
	@FXML
	private void addAction(ActionEvent action){
		try {
			NAV(action, "../View/AddMainView.fxml");
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
		Parent SignupView = FXMLLoader.load(getClass().getResource(str));
		Scene SignupView_scene = new Scene(SignupView);
		SignupView_scene.getStylesheets().add(getClass().getResource("../application/application.css").toExternalForm());
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(SignupView_scene);
		app_stage.show();
	}
	private void NAV_Mouse (MouseEvent event, String str) throws IOException {
		Parent SignupView = FXMLLoader.load(getClass().getResource(str));
		Scene SignupView_scene = new Scene(SignupView);
		SignupView_scene.getStylesheets().add(getClass().getResource("../application/application.css").toExternalForm());
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(SignupView_scene);
		app_stage.show();
	}
}
