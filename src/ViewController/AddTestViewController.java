package ViewController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import DBController.TestAdd;
import DBController.TestDetailAdd;
import DBModel.TestBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class AddTestViewController implements Initializable {
	//Declare JAVA
	private TestBean testbean;
	public static String login_id = LoginViewController.login_id;
	private ArrayList<String> getresult, checkarraylist;
	private TestDetailAdd testdetailadd;
	private TestAdd testadd;
	private int maxnumber;
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
			for(int i = 1 ; i < getresult.size()+1; i++) {
				if(i==1) {
					testbean.setTESTDETAIL_ID1_FK(getresult.get(i-1));
				}else if(i==2) {
					testbean.setTESTDETAIL_ID2_FK(getresult.get(i-1));
				}else if(i==3) {
					testbean.setTESTDETAIL_ID3_FK(getresult.get(i-1));
				}else if(i==4) {
					testbean.setTESTDETAIL_ID4_FK(getresult.get(i-1));
				}else if(i==5) {
					testbean.setTESTDETAIL_ID5_FK(getresult.get(i-1));
				}else if(i==6) {
					testbean.setTESTDETAIL_ID6_FK(getresult.get(i-1));
				}else if(i==7) {
					testbean.setTESTDETAIL_ID7_FK(getresult.get(i-1));
				}else if(i==8) {
					testbean.setTESTDETAIL_ID8_FK(getresult.get(i-1));
				}else if(i==9) {
					testbean.setTESTDETAIL_ID9_FK(getresult.get(i-1));
				}else if(i==10) {
					testbean.setTESTDETAIL_ID10_FK(getresult.get(i-1));
				}else if(i==11) {
					testbean.setTESTDETAIL_ID11_FK(getresult.get(i-1));
				}else if(i==12) {
					testbean.setTESTDETAIL_ID12_FK(getresult.get(i-1));
				}else if(i==13) {
					testbean.setTESTDETAIL_ID13_FK(getresult.get(i-1));
				}else if(i==14) {
					testbean.setTESTDETAIL_ID14_FK(getresult.get(i-1));
				}
			}
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
