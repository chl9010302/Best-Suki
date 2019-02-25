package ViewController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import DBController.TestAdd;
import DBController.TestDetailAdd;
import DBController.TestResultAdd;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;

public class ResultViewController implements Initializable {
	//Declare JAVA
	public static String login_id = LoginViewController.login_id;
	private TestDetailAdd testdetailadd;
	private TestResultAdd testresultadd;
	private ArrayList<String> testid, testanswer;
	//Declare FXML
	@FXML private ListView<String> testListView;
	@FXML private Label ColTest_Subtitle, TESTRESULT_ANSWER1, TESTRESULT_ANSWER2, TESTRESULT_ANSWER3, TESTRESULT_ANSWER4, TESTRESULT_ANSWER5, TESTDETAIL_ANSWER1, TESTDETAIL_ANSWER2, TESTDETAIL_ANSWER3, TESTDETAIL_ANSWER4, TESTDETAIL_ANSWER5, result1, result2, result3, result4, result5;
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
		testid = CommonController.splitQuestion(CommonController.selectcontent(TestingViewController.Resultid, "TESTRESULT_ID" ,config.StaticProperty.gettestresult_tb(), "TESTRESULT_ID_PK"));
		testanswer = CommonController.splitQuestion(CommonController.selectcontent(TestingViewController.Resultid, "TESTRESULT_ANSWER" ,config.StaticProperty.gettestresult_tb(), "TESTRESULT_ID_PK"));
		ColTest_Subtitle.setText(CommonController.selectcontent(TestAdd.testing_id, "TEST_SUBTITLE", config.StaticProperty.gettest_tb(), "TEST_ID_PK"));
		int i = testid.size();
		for(int j=i; j<5; j++) {
			testid.add(" ");
			testanswer.add(" ");
		}
		System.out.println(testid.size());
		TESTRESULT_ANSWER1.setText(testanswer.get(0));
		TESTDETAIL_ANSWER1.setText(CommonController.selectcontent(testid.get(0), "TESTDETAIL_ANSWER", config.StaticProperty.gettestdetail_tb(), "TESTDETAIL_ID_PK"));
		result1.setText(setresult(TESTRESULT_ANSWER1.getText(), TESTDETAIL_ANSWER1.getText()));
		TESTRESULT_ANSWER2.setText(testanswer.get(1));
		TESTDETAIL_ANSWER2.setText(CommonController.selectcontent(testid.get(1), "TESTDETAIL_ANSWER", config.StaticProperty.gettestdetail_tb(), "TESTDETAIL_ID_PK"));
		result2.setText(setresult(TESTRESULT_ANSWER2.getText(), TESTDETAIL_ANSWER2.getText()));
		TESTRESULT_ANSWER3.setText(testanswer.get(2));
		TESTDETAIL_ANSWER3.setText(CommonController.selectcontent(testid.get(2), "TESTDETAIL_ANSWER", config.StaticProperty.gettestdetail_tb(), "TESTDETAIL_ID_PK"));
		result3.setText(setresult(TESTRESULT_ANSWER3.getText(), TESTDETAIL_ANSWER3.getText()));
		TESTRESULT_ANSWER4.setText(testanswer.get(3));
		TESTDETAIL_ANSWER4.setText(CommonController.selectcontent(testid.get(3), "TESTDETAIL_ANSWER", config.StaticProperty.gettestdetail_tb(), "TESTDETAIL_ID_PK"));
		result4.setText(setresult(TESTRESULT_ANSWER4.getText(), TESTDETAIL_ANSWER4.getText()));
		TESTRESULT_ANSWER5.setText(testanswer.get(4));
		TESTDETAIL_ANSWER5.setText(CommonController.selectcontent(testid.get(4), "TESTDETAIL_ANSWER", config.StaticProperty.gettestdetail_tb(), "TESTDETAIL_ID_PK"));
		result5.setText(setresult(TESTRESULT_ANSWER5.getText(), TESTDETAIL_ANSWER5.getText()));
	}
	public String setresult(String result1, String result2) {
		if(result1.equals(result2)) {
			return "맞았습니다.";
		}
		return "틀렸습니다.";
	}
}
