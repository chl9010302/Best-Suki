package ViewController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TestDetailEditViewController implements Initializable {
	//Declare JAVA
	private TestBean testbean;
	public static String login_id = LoginViewController.login_id;
	//Declare FXML
	@FXML private TableView<TestDetailAdd> testTableView;
	@FXML private Label ColTest_Subtitle;
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
	@FXML
	private void deleteAction(ActionEvent event) {
		ButtonType YES = new ButtonType(config.StaticProperty.alertbtnyes(), ButtonBar.ButtonData.OK_DONE);
		ButtonType NO = new ButtonType(config.StaticProperty.alertbtnno(), ButtonBar.ButtonData.CANCEL_CLOSE);
		Alert alert = new Alert(AlertType.NONE,config.StaticProperty.alertcancel(), YES, NO);
		alert.setTitle(config.StaticProperty.alerttitlecancel());
		Optional<ButtonType> result = alert.showAndWait();
		if (result.orElse(NO) == YES) {
			try {
				CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestdetailview());
			}catch(Exception e) { }
		}
	}
	@FXML
	private void editAction(ActionEvent event) {
		try {
			ArrayList<String> getresult = CheckFunction(ColTest_Check);
			testbean = new TestBean();
			TestAdd testadd = new TestAdd();
			testbean.setTEST_ID_PK(TestViewController.test_id);
			testbean.setTEST_SUBTITLE(TestAdd.selectSubtitle(TestViewController.test_id));
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
			testadd.updateTestDetail(testbean);
			CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestview());
		}catch(Exception e) {e.printStackTrace(); }
	}
	public void initialize(URL url, ResourceBundle rb) { 
		TestDetailAdd testdetailadd = new TestDetailAdd();
		ColTest_Check.setCellValueFactory(new PropertyValueFactory<TestDetailAdd, CheckBox>("testdetail_checkboxdetail"));
		ColTest_Writer.setCellValueFactory(cellData -> cellData.getValue().getTestdetail_writer());
		ColTest_Date.setCellValueFactory(cellData -> cellData.getValue().getTestdetail_time());
		testTableView.setItems(testdetailadd.gettestdetailadd());
		ColTest_Subtitle.setText(TestAdd.selectSubtitle(TestViewController.test_id));
	}
	private ArrayList<String> CheckFunction(TableColumn<TestDetailAdd, CheckBox> tablecolumn) {
		TestDetailAdd testdetailadd = new TestDetailAdd();
		int maxnumber = testdetailadd.gettestdetailadd().size();
		ArrayList<String> checkarraylist = new ArrayList<>();
		ArrayList<String> result = new ArrayList<>();
		
		for(int i=0; i < maxnumber; i++) {
			if(tablecolumn.getCellData(i).isSelected()) {
				checkarraylist.add(testdetailadd.selectId(tablecolumn.getCellData(i).getText()));
			}
		}
		return checkarraylist;
	}
}
