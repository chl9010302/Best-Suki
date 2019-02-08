package ViewController;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import DBController.UserLogin;
import DBController.VideoDetailAdd;
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

public class VideoViewController implements Initializable {
	public static String selectedid= "";
	//Declare FXML
	@FXML private Button Property_userID;
	@FXML private Button BtnDelete;
	@FXML private TextField txtAddItem;
	@FXML private Button BtnAdd;
	@FXML private TableView<VideoDetailAdd> videoTableView;
	@FXML private TableColumn<VideoDetailAdd, String> ColVideo_Id;
	@FXML private TableColumn<VideoDetailAdd, String> ColVideo_Subtitle;
	@FXML private TableColumn<VideoDetailAdd, String> ColVideo_Writer;
	@FXML private TableColumn<VideoDetailAdd, String> ColVideo_Date;
	@FXML private TableColumn<VideoDetailAdd, String> ColVideo_Btndetail;
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
		ButtonType YES = new ButtonType("YES", ButtonBar.ButtonData.OK_DONE);
		ButtonType NO = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);
		Alert alert = new Alert(AlertType.NONE,config.StaticProperty.alertlogout(), YES, NO);
		alert.setTitle("Logout");
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
			NAV_POPUP(action, config.StaticProperty.getnavaddvideoview());
		}catch(Exception e) { }
	}
	@FXML
	private void deleteAction(ActionEvent action){
		VideoDetailAdd videodetailadd = new VideoDetailAdd();
		int selectedItem = videoTableView.getSelectionModel().getSelectedIndex();
		videodetailadd.delete(String.valueOf(videoTableView.getItems().get(selectedItem).getVideodetail_id_pk().getValue()));
		videoTableView.setItems(videodetailadd.getvideodetailadd());
	}
	
	public void initialize(URL url, ResourceBundle rb) {
		VideoDetailAdd videodetailadd = new VideoDetailAdd();
		ColVideo_Subtitle.setCellValueFactory(cellData -> cellData.getValue().getVideodetail_subtitle());
		ColVideo_Writer.setCellValueFactory(cellData -> cellData.getValue().getVideodetail_writer());
		ColVideo_Date.setCellValueFactory(cellData -> cellData.getValue().getVideodetail_time());
		ColVideo_Btndetail.setCellValueFactory(new PropertyValueFactory<VideoDetailAdd, String>("videodetail_btndetail"));
		videoTableView.setItems(videodetailadd.getvideodetailadd());
	}
	private void NAV (ActionEvent event, String str) throws IOException {
		Parent SignupView = FXMLLoader.load(getClass().getResource(str));
		Scene SignupView_scene = new Scene(SignupView);
		SignupView_scene.getStylesheets().add(getClass().getResource(config.StaticProperty.getnavapplication()).toExternalForm());
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(SignupView_scene);
		app_stage.show();
	}
	
	private void NAV_POPUP (ActionEvent event, String str) throws IOException {
		Parent SignupView = FXMLLoader.load(getClass().getResource(str));
		Scene SignupView_scene = new Scene(SignupView);
		SignupView_scene.getStylesheets().add(getClass().getResource(config.StaticProperty.getnavapplication()).toExternalForm());
		Stage app_stage = new Stage();
		app_stage.setScene(SignupView_scene);
		app_stage.show();
	}
}
