package ViewController;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import DBController.TestDetailAdd;
import DBModel.TestBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class AddTestViewController implements Initializable {
	//Declare JAVA
	private TestBean testbean;
	public static String login_id = LoginViewController.login_id;
	//Declare FXML
	@FXML private TableView<TestDetailAdd> testTableView;
	@FXML private TextField ColTest_Subtitle;
	@FXML private TableColumn<TestDetailAdd, String> ColTest_Writer, ColTest_Date, ColTest_Check;
	@FXML private void NAV_LoginView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavloginview()); }
	@FXML private void NAV_MainView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmainview());	}
	@FXML private void NAV_TestView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestview()); }
	@FXML private void NAV_TestBoardView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestboardview()); }
	@FXML private void NAV_StasticsView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavstasticsview()); }
	@FXML private void NAV_MypageView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmypageview()); }
	@FXML private void NAV_VideoView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavvideoview()); }
	@FXML private void logout(ActionEvent event) { CommonController.logout(getClass(), event); }
	@FXML
	private void deleteAction(ActionEvent event) {
		ButtonType YES = new ButtonType(config.StaticProperty.alertbtnyes(), ButtonBar.ButtonData.OK_DONE);
		ButtonType NO = new ButtonType(config.StaticProperty.alertbtnno(), ButtonBar.ButtonData.CANCEL_CLOSE);
		Alert alert = new Alert(AlertType.NONE,config.StaticProperty.alertcancel(), YES, NO);
		alert.setTitle(config.StaticProperty.alerttitlecancel());
		Optional<ButtonType> result = alert.showAndWait();
		if (result.orElse(NO) == YES) {
			try {
				CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestview());
			}catch(Exception e) { }
		}
	}
	@FXML
	private void addAction(ActionEvent event) {
		try {
			testbean = new TestBean();
			TestDetailAdd testdetailadd = new TestDetailAdd();
			testbean.setTEST_SUBTITLE(ColTest_Subtitle.getText().toString());
			testbean.setTEST_WRITER(login_id);
//			testadd.insertTestDetail(testbean);
			CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestview());
		}catch(Exception e) { }
	}
	public void initialize(URL url, ResourceBundle rb) { 
		TestDetailAdd testdetailadd = new TestDetailAdd();
		ColTest_Check.setCellValueFactory(new PropertyValueFactory<TestDetailAdd, String>("testdetail_checkboxdetail"));
		ColTest_Writer.setCellValueFactory(cellData -> cellData.getValue().getTestdetail_writer());
		ColTest_Date.setCellValueFactory(cellData -> cellData.getValue().getTestdetail_time());
		testTableView.setItems(testdetailadd.gettestdetailadd());
	}
}
