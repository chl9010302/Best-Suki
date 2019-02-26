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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
public class AddTestViewController implements Initializable {
	//Declare JAVA
	public static String login_id = LoginViewController.login_id;
	private TestBean testbean;
	private ArrayList<String> getresult, checkarraylist;
	private TestDetailAdd testdetailadd;
	private TestAdd testadd;
	private int maxnumber;
	private String result="";
	//Declare FXML
	@FXML private TableView<TestDetailAdd> testTableView;
	@FXML private TextField ColTest_Subtitle;
	@FXML private TableColumn<TestDetailAdd, CheckBox> ColTest_Check;
	@FXML private TableColumn<TestDetailAdd, String> ColTest_Writer, ColTest_Date;
	@FXML private void NAV_LoginView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavloginview()); }
	@FXML private void NAV_MainView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmainview());	}
	@FXML private void NAV_TestView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestview()); }
	@FXML private void NAV_TestBoardView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestboardview()); }
	@FXML private void NAV_StasticsView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavstasticsview()); }
	@FXML private void NAV_MypageView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmypageview()); }
	@FXML private void NAV_VideoView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavvideoview()); }
	@FXML private void logout(ActionEvent event) { CommonController.logout(getClass(), event); }
	@FXML private void deleteAction(ActionEvent event) { CommonController.Alert_YesorNo(event, config.StaticProperty.alertcancel(), config.StaticProperty.alerttitlecancel(), getClass(), config.StaticProperty.getnavtestview()); }
	@FXML
	private void addAction(ActionEvent event) {
		try {
			getresult = CheckFunction(ColTest_Check);
			testbean = new TestBean();
			testdetailadd = new TestDetailAdd();
			testadd = new TestAdd();
			testbean.setTEST_ID_PK(CommonController.MakeId());
			testbean.setTEST_SUBTITLE(ColTest_Subtitle.getText().toString());
			testbean.setTEST_WRITER(login_id);
			for(int i = 0 ; i < getresult.size(); i++) {
				if(i == getresult.size()-1) {
					result += getresult.get(i);
				}else {
					result += getresult.get(i) + ";";
				}
			}
			testbean.setTESTDETAIL_ID_FK(result);
			testadd.insertTest(testbean);
			CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestview());
		}catch(Exception e) {e.printStackTrace(); }
	}
	public void initialize(URL url, ResourceBundle rb) { 
		testdetailadd = new TestDetailAdd();
		ColTest_Check.setCellValueFactory(new PropertyValueFactory<TestDetailAdd, CheckBox>("testdetail_checkboxdetail"));
		ColTest_Writer.setCellValueFactory(cellData -> cellData.getValue().getTestdetail_writer());
		ColTest_Date.setCellValueFactory(cellData -> cellData.getValue().getTestdetail_time());
		testTableView.setItems(testdetailadd.gettestdetailadd());
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
