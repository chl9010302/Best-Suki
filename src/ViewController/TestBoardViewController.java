package ViewController;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import DBController.TestDetailAdd;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TestBoardViewController implements Initializable {
	//Declare JAVA
		
	//Declare FXML
	@FXML private Button Property_userID;
	@FXML private Text result; // logout 시 result 표시
	@FXML private Button BtnAdd;
	@FXML private Button BtnDelete;
	@FXML private TableView<TestDetailAdd> testTableView;
	@FXML private TableColumn<TestDetailAdd, String> ColBoardId;
	@FXML private TableColumn<TestDetailAdd, String> ColSubtitle;
	@FXML private TableColumn<TestDetailAdd, String> ColWriter;
	@FXML private TableColumn<TestDetailAdd, String> ColDate;
	@FXML private void NAV_LoginView(ActionEvent event) throws IOException { NAV(event, config.StaticProperty.getnavloginview()); }
	@FXML private void NAV_MainView(ActionEvent event) throws IOException { NAV(event, config.StaticProperty.getnavmainview());	}
	@FXML private void NAV_TestView(ActionEvent event) throws IOException { NAV(event, config.StaticProperty.getnavtestview()); }
	@FXML private void NAV_TestBoardView(ActionEvent event) throws IOException { NAV(event, config.StaticProperty.getnavtestboardview()); }
	@FXML private void NAV_AddTestView(ActionEvent event) throws IOException { NAV_POPUP(event, config.StaticProperty.getnavaddtestview()); }
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
	private void removeAction(ActionEvent action){
		TestDetailAdd testdetailadd = new TestDetailAdd();
		int selectedItem = testTableView.getSelectionModel().getSelectedIndex();
		testdetailadd.delete(String.valueOf(testTableView.getItems().get(selectedItem).getTestdetail_id_pk().getValue()));
		testTableView.setItems(testdetailadd.gettestdetailadd());
	}
	@FXML
	private void modify(ActionEvent action) {
	}
	
	public void initialize(URL url, ResourceBundle rb) {
	  // TODO
		try {
			TestDetailAdd testdetailadd = new TestDetailAdd();
			ColBoardId.setCellValueFactory(cellData -> cellData.getValue().getTestdetail_id_pk());
			ColSubtitle.setCellValueFactory(cellData -> cellData.getValue().getSubtitle());
			ColWriter.setCellValueFactory(cellData -> cellData.getValue().getTestdetail_writer());
			ColDate.setCellValueFactory(cellData -> cellData.getValue().getTestdetail_time());
			testTableView.setItems(testdetailadd.gettestdetailadd());
		}catch(Exception e) {}
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
