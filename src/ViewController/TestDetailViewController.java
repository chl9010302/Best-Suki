package ViewController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import DBController.TestAdd;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;

public class TestDetailViewController implements Initializable {
	private TestAdd testadd;
	//Declare JAVA
	public static String login_id = LoginViewController.login_id;
	//Declare FXML
	@FXML private ListView<String> testListView;
	@FXML private Label ColTest_Subtitle;
	@FXML private TableColumn<TestAdd, String> ColTestDetail_Subtitle, ColTest_Writer, ColTest_Date;
	@FXML private void NAV_LoginView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavloginview()); }
	@FXML private void NAV_MainView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmainview());	}
	@FXML private void NAV_TestView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestview()); }
	@FXML private void NAV_TestBoardView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestboardview()); }
	@FXML private void NAV_StasticsView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavstasticsview()); }
	@FXML private void NAV_MypageView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmypageview()); }
	@FXML private void NAV_VideoView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavvideoview()); }
	@FXML private void logout(ActionEvent event) { CommonController.logout(getClass(), event); }
	@FXML
	private void editAction(ActionEvent event) {
		try {
			CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestdetaileditview());
		}catch(Exception e) {e.printStackTrace(); }
	}
	public void initialize(URL url, ResourceBundle rb) { 
		testadd = new TestAdd();
		testListView.setItems(testadd.gettestdetailid(TestViewController.test_id));
		ColTest_Subtitle.setText(TestAdd.selectSubtitle(TestViewController.test_id));
	}
}
