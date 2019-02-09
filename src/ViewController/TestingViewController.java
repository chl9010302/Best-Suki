package ViewController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import DBController.TestDetailAdd;
import DBModel.TestDetailBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class TestingViewController implements Initializable {
	//Declare JAVA
	private TestDetailBean testdetailbean;
	public static String filepath;
	//Declare FXML
	@FXML private Label testdetail_subtitle, testdetail_answer1, testdetail_answer2, testdetail_answer3, testdetail_answer4, testdetail_answer5;
	@FXML private RadioButton testdetail_rb1, testdetail_rb2, testdetail_rb3, testdetail_rb4, testdetail_rb5;
	@FXML private ToggleGroup Quest1Group1;
	@FXML private TableView<TestDetailAdd> testTableView;
	@FXML private TableColumn<TestDetailAdd, String> ColBoardId, ColSubtitle;
	@FXML private void NAV_LoginView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavloginview()); }
	@FXML private void NAV_MainView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmainview());	}
	@FXML private void NAV_TestView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestview()); }
	@FXML private void NAV_TestBoardView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestboardview()); }
	@FXML private void NAV_StasticsView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavstasticsview()); }
	@FXML private void NAV_MypageView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmypageview()); }
	@FXML private void NAV_VideoView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavvideoview()); }
	@FXML private void logout(ActionEvent event) { CommonController.logout(getClass(), event); }
	@FXML
	private void saveAction(ActionEvent action) {
		testdetailbean = new TestDetailBean();
		testdetailbean.setTESTDETAIL_ID_PK(usingstaticfunction.TestDetailFunction.makeTestDetailKey(testdetail_subtitle.getText().toString()));
		testdetailbean.setTESTDETAIL_SUBTITLE(testdetail_subtitle.getText().toString());
		testdetailbean.setTESTDETAIL_IMAGE(filepath);
		testdetailbean.setTESTDETAIL_DATA1(testdetail_answer1.getText().toString());
		testdetailbean.setTESTDETAIL_DATA2(testdetail_answer2.getText().toString());
		testdetailbean.setTESTDETAIL_DATA3(testdetail_answer3.getText().toString());
		testdetailbean.setTESTDETAIL_DATA4(testdetail_answer4.getText().toString());
		testdetailbean.setTESTDETAIL_DATA5(testdetail_answer5.getText().toString());
		if(testdetail_rb1.isSelected()) {
			testdetailbean.setTESTDETAIL_ANSWER(testdetail_answer1.getText().toString());
		}else if(testdetail_rb2.isSelected()) {
			testdetailbean.setTESTDETAIL_ANSWER(testdetail_answer2.getText().toString());
		}else if(testdetail_rb3.isSelected()) {
			testdetailbean.setTESTDETAIL_ANSWER(testdetail_answer3.getText().toString());
		}else if(testdetail_rb4.isSelected()) {
			testdetailbean.setTESTDETAIL_ANSWER(testdetail_answer4.getText().toString());
		}else {
			testdetailbean.setTESTDETAIL_ANSWER(testdetail_answer5.getText().toString());
		}
	    ((Stage) ((Node) action.getSource()).getScene().getWindow()).close(); // 창 닫음.
	    TestDetailAdd detailAdd = new TestDetailAdd();
	    detailAdd.insertTestDetail(testdetailbean);
	}
	@FXML
	private void Quest1Group1Action(ActionEvent action) {
	}
	@FXML
	private void modify(ActionEvent action) {
	}
	public void initialize(URL url, ResourceBundle rb) {
		try {
			TestDetailAdd testdetailadd = new TestDetailAdd();
			ColBoardId.setCellValueFactory(cellData -> cellData.getValue().getTestdetail_id_pk());
			ColSubtitle.setCellValueFactory(cellData -> cellData.getValue().getTestdetail_Subtitle());
			testTableView.setItems(testdetailadd.gettestdetailadd());
		}catch(Exception e) {}
	}
}
