package ViewController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import DBController.TestAdd;
import DBController.TestDetailAdd;
import DBModel.TestBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TestDetailEditViewController implements Initializable {
	//Declare JAVA
	private TestBean testbean;
	public static String login_id = LoginViewController.login_id;
	private ArrayList<String> getresult;
	private TestAdd testadd;
	private TestDetailAdd testdetailadd;
	private ArrayList<String> checkarraylist;
	private int maxnumber;
	//Declare FXML
	@FXML private TableView<TestDetailAdd> testTableView;
	@FXML private Label ColTest_Subtitle;
	@FXML private TableColumn<TestDetailAdd, CheckBox> ColTest_Check;
	@FXML private TableColumn<TestDetailAdd, String> ColTest_Writer, ColTest_Date;
	@FXML private void NAV_LoginView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavloginview()); }
	@FXML private void NAV_MainView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmainview());	}
	@FXML private void NAV_TestView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestview()); }
	@FXML private void NAV_TestBoardView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestboardview()); }
	@FXML private void NAV_StatisticsView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavstatisticsview()); }
	@FXML private void NAV_MypageView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmypageview()); }
	@FXML private void NAV_VideoView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavvideoview()); }
	@FXML private void logout(ActionEvent event) { CommonController.logout(getClass(), event); }
	@FXML private void deleteAction(ActionEvent event) { CommonController.Alert_YesorNo(event, config.StaticProperty.alertcancel(), config.StaticProperty.alerttitlecancel(), getClass(), config.StaticProperty.getnavtestdetailview()); }
	@FXML
	private void editAction(ActionEvent event) {
		try {
			getresult = CheckFunction(ColTest_Check);
			testbean = new TestBean();
			testadd = new TestAdd();
			testbean.setTEST_ID_PK(TestViewController.test_id);
			testbean.setTEST_SUBTITLE(CommonController.selectcontent(TestViewController.test_id, "TEST_SUBTITLE", config.StaticProperty.gettest_tb(), "TEST_ID_PK"));
			testbean.setTEST_WRITER(login_id);
			String result = "";
			for(int i = 1 ; i < getresult.size()+1; i++) {
				result += getresult.get(i-1) + ";";
			}
			testbean.setTESTDETAIL_ID_FK(result);
			testadd.updateTestDetail(testbean);
			CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestview());
		}catch(Exception e) {e.printStackTrace(); }
	}
	public void initialize(URL url, ResourceBundle rb) { 
		ColTest_Check.setCellValueFactory(new PropertyValueFactory<TestDetailAdd, CheckBox>("testdetail_checkboxdetail"));
		ColTest_Writer.setCellValueFactory(cellData -> cellData.getValue().getTestdetail_writer());
		ColTest_Date.setCellValueFactory(cellData -> cellData.getValue().getTestdetail_time());
		testTableView.setItems(testdetailadd.gettestdetailadd());
		ColTest_Subtitle.setText(CommonController.selectcontent(TestViewController.test_id, "TEST_SUBTITLE", config.StaticProperty.gettest_tb(), "TEST_ID_PK"));
	}
	private ArrayList<String> CheckFunction(TableColumn<TestDetailAdd, CheckBox> tablecolumn) {
		testdetailadd = new TestDetailAdd();
		checkarraylist = new ArrayList<>();
		maxnumber = testdetailadd.gettestdetailadd().size();
		for(int i=0; i < maxnumber; i++) {
			if(tablecolumn.getCellData(i).isSelected()) {
				checkarraylist.add(CommonController.selectcontent(tablecolumn.getCellData(i).getText(), "TESTDETAIL_ID_PK", config.StaticProperty.gettestdetail_tb(), "TESTDETAIL_SUBTITLE"));
			}
		}
		return checkarraylist;
	}
}
