package ViewController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import DBController.TestDetailAdd;
import DBModel.TestDetailBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
public class TestBoardDetailEditViewController implements Initializable {
	//Declare JAVA
	private TestDetailBean testdetailbean;
	private Stage stage; // file choose 하기 위함.
	public static String filename;
	public static FileInputStream input;
	private TestDetailAdd testdetailadd;
	private FileChooser fileChooser;
	private File file;
	private ArrayList<String> question_selected, result;
	//Declare FXML
	@FXML private ToggleGroup Quest1Group1;
	@FXML private TextField testdetail_subtitle, testdetail_answer1, testdetail_answer2, testdetail_answer3, testdetail_answer4, testdetail_answer5;
	@FXML private RadioButton testdetail_rb1, testdetail_rb2, testdetail_rb3, testdetail_rb4, testdetail_rb5;
	@FXML private Label txtFilepath;
	@FXML private void NAV_LoginView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavloginview()); }
	@FXML private void NAV_MainView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmainview());	}
	@FXML private void NAV_TestView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestview()); }
	@FXML private void NAV_TestBoardView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestboardview()); }
	@FXML private void NAV_StasticsView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavstasticsview()); }
	@FXML private void NAV_MypageView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmypageview()); }
	@FXML private void NAV_VideoView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavvideoview()); }
	@FXML private void logout(ActionEvent event) { CommonController.logout(getClass(), event); }
	@FXML private void cancelAction(ActionEvent event) { CommonController.Alert_YesorNo(event, config.StaticProperty.alertcancel(), config.StaticProperty.alerttitlecancel(), getClass(), config.StaticProperty.getnavtestboarddetailview()); }
	@FXML
	private void editAction(ActionEvent event) {
		testdetailadd = new TestDetailAdd();
		try {
			testdetailbean = new TestDetailBean();
			question_selected = new ArrayList<>();
			testdetailbean.setTESTDETAIL_ID_PK(TestDetailAdd.testdetail_id);
			testdetailbean.setTESTDETAIL_SUBTITLE(testdetail_subtitle.getText().toString());
			testdetailbean.setTESTDETAIL_IMAGE_PATH(filename);
			testdetailbean.setTESTDETAIL_IMAGE(input);
			question_selected.add(testdetail_answer1.getText().toString());
			question_selected.add(testdetail_answer2.getText().toString());
			question_selected.add(testdetail_answer3.getText().toString());
			question_selected.add(testdetail_answer4.getText().toString());
			question_selected.add(testdetail_answer5.getText().toString());
			testdetailbean.setTESTDETAIL_DATA(CommonController.makeAnswer(question_selected));
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
			testdetailadd.updateTestDetail(testdetailbean);
		}catch(Exception e) {e.printStackTrace(); }
		try {
			CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestboarddetailview());
		}catch(Exception e) { }
	}
	@FXML
	private void Quest1Group1Action(ActionEvent action) {
	}
	public void initialize(URL url, ResourceBundle rb) {
		testdetailadd = new TestDetailAdd();
		result = new ArrayList<>();
		result = CommonController.splitQuestion(CommonController.selectcontent(TestDetailAdd.testdetail_id, "TESTDETAIL_DATA", config.StaticProperty.gettestdetail_tb(), "TESTDETAIL_ID_PK"));
		testdetail_subtitle.setText(CommonController.selectcontent(TestDetailAdd.testdetail_id, "TESTDETAIL_SUBTITLE", config.StaticProperty.gettestdetail_tb(), "TESTDETAIL_ID_PK"));
		testdetail_answer1.setText(result.get(0));
		testdetail_answer2.setText(result.get(1));
		testdetail_answer3.setText(result.get(2));
		testdetail_answer4.setText(result.get(3));
		testdetail_answer5.setText(result.get(4));
	}
	public void openFile() {
		fileChooser = new FileChooser();
		file = fileChooser.showOpenDialog(stage);
		filename = file.getAbsolutePath();
		try {
			input = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void fileChooserSelect(ActionEvent event) { 
		openFile(); 
		txtFilepath.setText(filename);
	}
}
