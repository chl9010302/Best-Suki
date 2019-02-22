package ViewController;

import java.io.IOException;
import java.net.URL;
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
	private TestAdd testadd;
	private String result_answer;
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
	@FXML private void NAV_StasticsView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavstasticsview()); }
	@FXML private void NAV_MypageView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmypageview()); }
	@FXML private void NAV_VideoView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavvideoview()); }
	@FXML private void logout(ActionEvent event) { CommonController.logout(getClass(), event); }
	@FXML
	public void nextAction(ActionEvent event) {
		TestAdd.maxpage--;
		// 결과값 저장
		TestResultAdd.testresult.add(TestAdd.selectgetTestId(TestAdd.testing_id));
		TestResultAdd.testresult.add(getAnswer());
		if(TestAdd.maxpage != 0) {
			++TestAdd.pagenumber;
			TestAdd.test_id_fk = TestAdd.selectgetTestId(TestAdd.testing_id);
			try {
				ViewController.CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestingview());
			} catch (IOException e) {e.printStackTrace(); }
			
			//try문 끝
		}else {
			YES = new ButtonType(config.StaticProperty.alertbtndone(), ButtonBar.ButtonData.OK_DONE);
			alert = new Alert(AlertType.NONE,config.StaticProperty.alertcongraturations(), YES);
			alert.setTitle(config.StaticProperty.alertcongraturations());
			result = alert.showAndWait();
			if (result.orElse(YES) == YES) {
				try {
					testresultadd = new TestResultAdd();
					testresultbean = new TestResultBean();
					int i = 0;
					i = TestResultAdd.testresult.size();
					if(i<10) {
						for( int j = i; j<10;j++) {
							TestResultAdd.testresult.add("");
						}
					}
					testresultbean.setTESTRESULT_ID1(TestResultAdd.testresult.get(0));
					testresultbean.setTESTRESULT_ANSWER1(TestResultAdd.testresult.get(1));
					testresultbean.setTESTRESULT_ID2(TestResultAdd.testresult.get(2));
					testresultbean.setTESTRESULT_ANSWER2(TestResultAdd.testresult.get(3));
					testresultbean.setTESTRESULT_ID3(TestResultAdd.testresult.get(4));
					testresultbean.setTESTRESULT_ANSWER3(TestResultAdd.testresult.get(5));
					testresultbean.setTESTRESULT_ID4(TestResultAdd.testresult.get(6));
					testresultbean.setTESTRESULT_ANSWER4(TestResultAdd.testresult.get(7));
					testresultbean.setTESTRESULT_ID5(TestResultAdd.testresult.get(8));
					testresultbean.setTESTRESULT_ANSWER5(TestResultAdd.testresult.get(9));
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
	@FXML
	private void Quest1Group1Action(ActionEvent action) {
	}
	public void initialize(URL url, ResourceBundle rb) {
		if(TestAdd.maxpage == 1) {
			btnSubmit.setText("제출하기");
			label_TestingView.setText("마지막 문제입니다.");
		}else {
			int i = TestAdd.maxpage-1;
			label_TestingView.setText("TestingView : " + i + " 문제 남았습니다.");
		}
		testdetailadd = new TestDetailAdd();
		testadd = new TestAdd();
		testdetail_subtitle.setText(CommonController.selectcontent(testadd.test_id_fk, "TESTDETAIL_SUBTITLE", config.StaticProperty.gettestdetail_tb(), "TESTDETAIL_ID_PK"));
		testdetail_answer1.setText(CommonController.selectcontent(testadd.test_id_fk, "TESTDETAIL_DATA1", config.StaticProperty.gettestdetail_tb(), "TESTDETAIL_ID_PK"));
		testdetail_answer2.setText(CommonController.selectcontent(testadd.test_id_fk, "TESTDETAIL_DATA2", config.StaticProperty.gettestdetail_tb(), "TESTDETAIL_ID_PK"));
		testdetail_answer3.setText(CommonController.selectcontent(testadd.test_id_fk, "TESTDETAIL_DATA3", config.StaticProperty.gettestdetail_tb(), "TESTDETAIL_ID_PK"));
		testdetail_answer4.setText(CommonController.selectcontent(testadd.test_id_fk, "TESTDETAIL_DATA4", config.StaticProperty.gettestdetail_tb(), "TESTDETAIL_ID_PK"));
		testdetail_answer5.setText(CommonController.selectcontent(testadd.test_id_fk, "TESTDETAIL_DATA5", config.StaticProperty.gettestdetail_tb(), "TESTDETAIL_ID_PK"));
		
		image = new Image(testdetailadd.selectIMAGE(testadd.test_id_fk).toURI().toString(),623,150,true,true);
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
