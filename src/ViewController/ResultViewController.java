package ViewController;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ResourceBundle;

import DBController.TestAdd;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;

public class ResultViewController implements Initializable {
	//Declare JAVA
	public static String login_id = LoginViewController.login_id;
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
	@SuppressWarnings("null")
	public void initialize(URL url, ResourceBundle rb) {
		ColTest_Subtitle.setText(TestAdd.selectSubtitle(TestAdd.testing_id));
		String str = "TESTRESULT_ANSWER1";
		try {
			Class c = Class.forName(getClass().toString());
			Method m[] = c.getDeclaredMethods();
			for (int i = 0; i < m.length; i++)
				System.out.println(m[i].toString());
		} catch (Throwable e) {
			System.err.println(e);
		}
//		for(int i=1; i<2; i++) {
//			StringBuilder sb = new StringBuilder();
//			sb = sb.append("TESTRESULT_ANSWER").append(i).append(".setText(TestResultAdd.selectgetresultanswer(TestingViewController.Resultid, ").append(i).append("));");
//			String sql = sb.toString();
//			System.out.println(sb.toString());
//			TESTRESULT_ANSWER1.setText(TestResultAdd.selectgetresultanswer(TestingViewController.Resultid, 1));
//			TESTDETAIL_ANSWER1.setText(TestDetailAdd.selectANSWER(TestResultAdd.selectgetresultid(TestingViewController.Resultid, 1)));
//			result1.setText(setresult(TESTRESULT_ANSWER1.getText(), TESTDETAIL_ANSWER1.getText()));
//		}
//		TESTRESULT_ANSWER1.setText(TestResultAdd.selectgetresultanswer(TestingViewController.Resultid, 1));
//		TESTDETAIL_ANSWER1.setText(TestDetailAdd.selectANSWER(TestResultAdd.selectgetresultid(TestingViewController.Resultid, 1)));
//		result1.setText(setresult(TESTRESULT_ANSWER1.getText(), TESTDETAIL_ANSWER1.getText()));
//		TESTRESULT_ANSWER2.setText(TestResultAdd.selectgetresultanswer(TestingViewController.Resultid, 2));
//		TESTDETAIL_ANSWER2.setText(TestDetailAdd.selectANSWER(TestResultAdd.selectgetresultid(TestingViewController.Resultid, 2)));
//		result2.setText(setresult(TESTRESULT_ANSWER2.getText(), TESTDETAIL_ANSWER2.getText()));
//		TESTRESULT_ANSWER3.setText(TestResultAdd.selectgetresultanswer(TestingViewController.Resultid, 3));
//		TESTDETAIL_ANSWER3.setText(TestDetailAdd.selectANSWER(TestResultAdd.selectgetresultid(TestingViewController.Resultid, 3)));
//		result3.setText(setresult(TESTRESULT_ANSWER3.getText(), TESTDETAIL_ANSWER3.getText()));
//		TESTRESULT_ANSWER4.setText(TestResultAdd.selectgetresultanswer(TestingViewController.Resultid, 4));
//		TESTDETAIL_ANSWER4.setText(TestDetailAdd.selectANSWER(TestResultAdd.selectgetresultid(TestingViewController.Resultid, 4)));
//		result4.setText(setresult(TESTRESULT_ANSWER4.getText(), TESTDETAIL_ANSWER4.getText()));
//		TESTRESULT_ANSWER5.setText(TestResultAdd.selectgetresultanswer(TestingViewController.Resultid, 5));
//		TESTDETAIL_ANSWER5.setText(TestDetailAdd.selectANSWER(TestResultAdd.selectgetresultid(TestingViewController.Resultid, 5)));
//		result5.setText(setresult(TESTRESULT_ANSWER5.getText(), TESTDETAIL_ANSWER5.getText()));
	}
	
//	public String setresult(String result1, String result2) {
//		if(result1.equals(result2)) {
//			return "맞았습니다.";
//		}
//		return "틀렸습니다.";
//	}
}
