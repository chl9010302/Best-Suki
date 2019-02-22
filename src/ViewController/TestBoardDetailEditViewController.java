package ViewController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import DBController.TestDetailAdd;
import DBModel.TestDetailBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
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
		TestDetailAdd testdetailadd = new TestDetailAdd();
		try {
			testdetailbean = new TestDetailBean();
			testdetailbean.setTESTDETAIL_ID_PK(testdetailadd.testdetail_id);
			testdetailbean.setTESTDETAIL_SUBTITLE(testdetail_subtitle.getText().toString());
			testdetailbean.setTESTDETAIL_IMAGE_PATH(filename);
			testdetailbean.setTESTDETAIL_IMAGE(input);
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
		testdetail_subtitle.setText(CommonController.selectcontent(testdetailadd.testdetail_id, "TESTDETAIL_SUBTITLE", config.StaticProperty.gettestdetail_tb(), "TESTDETAIL_ID_PK"));
		testdetail_answer1.setText(CommonController.selectcontent(testdetailadd.testdetail_id, "TESTDETAIL_DATA1", config.StaticProperty.gettestdetail_tb(), "TESTDETAIL_ID_PK"));
		testdetail_answer2.setText(CommonController.selectcontent(testdetailadd.testdetail_id, "TESTDETAIL_DATA2", config.StaticProperty.gettestdetail_tb(), "TESTDETAIL_ID_PK"));
		testdetail_answer3.setText(CommonController.selectcontent(testdetailadd.testdetail_id, "TESTDETAIL_DATA3", config.StaticProperty.gettestdetail_tb(), "TESTDETAIL_ID_PK"));
		testdetail_answer4.setText(CommonController.selectcontent(testdetailadd.testdetail_id, "TESTDETAIL_DATA4", config.StaticProperty.gettestdetail_tb(), "TESTDETAIL_ID_PK"));
		testdetail_answer5.setText(CommonController.selectcontent(testdetailadd.testdetail_id, "TESTDETAIL_DATA5", config.StaticProperty.gettestdetail_tb(), "TESTDETAIL_ID_PK"));
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
