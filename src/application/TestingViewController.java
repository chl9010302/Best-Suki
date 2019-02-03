package application;

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

public class TestingViewController implements Initializable {
	//Declare JAVA
	
		//Declare FXML
		@FXML private Text result; // logout 시 result 표시
		@FXML private Button BtnAdd;
		@FXML private Button BtnDelete;
		@FXML private TableView<TestDetailAdd> testTableView;
		@FXML private TableColumn<TestDetailAdd, String> ColBoardId;
		@FXML private TableColumn<TestDetailAdd, String> ColSubtitle;
		@FXML private void NAV_LoginView(ActionEvent event) throws IOException { NAV(event, "../View/LoginView.fxml"); }
		@FXML private void NAV_MainView(ActionEvent event) throws IOException { NAV(event, "../View/MainView.fxml");	}
		@FXML private void NAV_TestView(ActionEvent event) throws IOException { NAV(event, "../View/TestView.fxml"); }
		@FXML private void NAV_TestBoardView(ActionEvent event) throws IOException { NAV(event, "../View/TestBoardView.fxml"); }
		@FXML private void NAV_AddTestView(ActionEvent event) throws IOException { NAV_POPUP(event, "../View/AddTestView.fxml"); }
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
		private void removeAction(ActionEvent action){
			TestDetailAdd testdetailadd = new TestDetailAdd();
		  int selectedItem = testTableView.getSelectionModel().getSelectedIndex();
		  testdetailadd.delete(Integer.parseInt(testTableView.getItems().get(selectedItem).getTestdetail_id_pk().getValue()));
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
				testTableView.setItems(testdetailadd.gettestdetailadd());
			}catch(Exception e) {}
		}
		private void NAV (ActionEvent event, String str) throws IOException {
			Parent SignupView = FXMLLoader.load(getClass().getResource(str));
			Scene SignupView_scene = new Scene(SignupView);
			SignupView_scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			app_stage.setScene(SignupView_scene);
			app_stage.show();
		}
		private void NAV_POPUP (ActionEvent event, String str) throws IOException {
			Parent SignupView = FXMLLoader.load(getClass().getResource(str));
			Scene SignupView_scene = new Scene(SignupView);
			SignupView_scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Stage app_stage = new Stage();
			app_stage.setScene(SignupView_scene);
			app_stage.show();
		}
}
