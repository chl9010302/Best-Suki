package ViewController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import DBController.TestAdd;
import DBController.TestDetailAdd;
import DBController.TestResultAdd;
import DBModel.TestResultBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TestingViewController implements Initializable {
	public static Image image;
	public static String Resultid = "";
	private ButtonType YES;
	private Alert alert;
	private Optional<ButtonType> result;
	private TestResultAdd testresultadd;
	private TestResultBean testresultbean;
	private TestDetailAdd testdetailadd;
	private String result_answer;
	private int left_page;
	//Declare FXML
	@FXML private Button btnSubmit;
	@FXML private ToggleGroup Quest1Group1;
	@FXML private ImageView testdetail_imageview;
	@FXML private Label label_TestingView, testdetail_subtitle, testdetail_answer1, testdetail_answer2, testdetail_answer3, testdetail_answer4, testdetail_answer5;
	@FXML private RadioButton testdetail_rb1, testdetail_rb2, testdetail_rb3, testdetail_rb4, testdetail_rb5;
	@FXML private void NAV_LoginView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavloginview()); }
	@FXML private void NAV_MainView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmainview());	}
	@FXML private void NAV_TestView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestview()); }
	@FXML private void NAV_TestBoardView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestboardview()); }
	@FXML private void NAV_StatisticsView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavstatisticsview()); }
	@FXML private void NAV_MypageView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmypageview()); }
	@FXML private void NAV_VideoView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavvideoview()); }
	@FXML private void logout(ActionEvent event) { CommonController.logout(getClass(), event); }
	@FXML
	public void nextAction(ActionEvent event) {
		TestAdd.maxpage--;
		// 결과값 저장
		if(TestAdd.maxpage != 0) {
			TestResultAdd.answer_result += getAnswer() + ";";
			++TestAdd.pagenumber;
			TestAdd.test_id_fk = TestAdd.result_arraylist.get(TestAdd.pagenumber);
			try {
				ViewController.CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestingview());
			} catch (IOException e) {e.printStackTrace(); }
			
			//try문 끝
		}else {
			TestResultAdd.answer_result += getAnswer();
			YES = new ButtonType(config.StaticProperty.alertbtndone(), ButtonBar.ButtonData.OK_DONE);
			alert = new Alert(AlertType.NONE,config.StaticProperty.alertcongraturations(), YES);
			alert.setTitle(config.StaticProperty.alertcongraturations());
			result = alert.showAndWait();
			if (result.orElse(YES) == YES) {
				try {
					testresultadd = new TestResultAdd();
					testresultbean = new TestResultBean();
					if(TestResultAdd.testresult.size() < 10) {
						for( int j = TestResultAdd.testresult.size() ; j<10 ; j++) {
							TestResultAdd.testresult.add("");
						}
					}
					testresultbean.setTESTRESULT_ID(CommonController.selectcontent(TestAdd.testing_id, "TESTDETAIL_ID_FK", config.StaticProperty.gettest_tb(), "TEST_ID_PK"));
					testresultbean.setTESTRESULT_ANSWER(TestResultAdd.answer_result);
					testresultbean.setTESTRESULT_ID_PK(CommonController.MakeId());
					testresultbean.setTESTRESULT_WRITER(LoginViewController.login_id);
					testresultbean.setTEST_ID(TestAdd.testing_id);
					testresultadd.insertTestResult(testresultbean);
					Resultid = CommonController.MakeId();
					CommonController.NAV(getClass(), event, config.StaticProperty.getnavresultview());
				} catch (IOException e) {e.printStackTrace(); }
			}
		}
	}
	public void initialize(URL url, ResourceBundle rb) {
		if(TestAdd.maxpage == 1) {
			btnSubmit.setText("제출하기");
			label_TestingView.setText("마지막 문제입니다.");
		}else {
			left_page = TestAdd.maxpage-1;
			label_TestingView.setText("TestingView : " + left_page + " 문제 남았습니다.");
		}
		testdetailadd = new TestDetailAdd();
		testdetail_subtitle.setText(CommonController.selectcontent(TestAdd.test_id_fk, "TESTDETAIL_SUBTITLE", config.StaticProperty.gettestdetail_tb(), "TESTDETAIL_ID_PK"));
		testdetail_answer1.setText(TestAdd.initial_result.get(0));
		testdetail_answer2.setText(TestAdd.initial_result.get(1));
		testdetail_answer3.setText(TestAdd.initial_result.get(2));
		testdetail_answer4.setText(TestAdd.initial_result.get(3));
		testdetail_answer5.setText(TestAdd.initial_result.get(4));
		image = new Image(testdetailadd.selectIMAGE(TestAdd.test_id_fk).toURI().toString(),623,150,true,true);
		testdetail_imageview.setImage(image);
	}
	private String getAnswer() {
		if(testdetail_rb1.isSelected()) {
			result_answer = testdetail_answer1.getText().toString();
		}else if(testdetail_rb2.isSelected()) {
			result_answer = testdetail_answer2.getText().toString();
		}else if(testdetail_rb3.isSelected()) {
			result_answer = testdetail_answer3.getText().toString();
		}else if(testdetail_rb4.isSelected()) {
			result_answer = testdetail_answer4.getText().toString();
		}else {
			result_answer = testdetail_answer5.getText().toString();
		}
		return result_answer;
	}
}
