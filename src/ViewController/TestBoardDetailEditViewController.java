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
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
public class TestBoardDetailEditViewController implements Initializable {
	//Declare JAVA
	public static Image image;
	private TestDetailBean testdetailbean;
	private Stage stage; // file choose 하기 위함.
	public static FileInputStream input;
	private TestDetailAdd testdetailadd;
	private FileChooser fileChooser;
	private File file;
	private ArrayList<String> question_selected, result;
	private int index_answer;
	private String number_answer, filename;
	//Declare FXML
	@FXML private ToggleGroup Quest1Group1;
	@FXML private TextField testdetail_subtitle, testdetail_answer1, testdetail_answer2, testdetail_answer3, testdetail_answer4, testdetail_answer5;
	@FXML private RadioButton testdetail_rb1, testdetail_rb2, testdetail_rb3, testdetail_rb4, testdetail_rb5;
	@FXML private Label txtFilepath;
	@FXML private void NAV_LoginView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavloginview()); }
	@FXML private void NAV_MainView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmainview());	}
	@FXML private void NAV_TestView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestview()); }
	@FXML private void NAV_TestBoardView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestboardview()); }
	@FXML private void NAV_StatisticsView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavstatisticsview()); }
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
			question_selected.add(testdetail_answer1.getText().toString());
			question_selected.add(testdetail_answer2.getText().toString());
			question_selected.add(testdetail_answer3.getText().toString());
			question_selected.add(testdetail_answer4.getText().toString());
			question_selected.add(testdetail_answer5.getText().toString());
			index_answer = Quest1Group1.getSelectedToggle().toString().indexOf("'");
			number_answer = Quest1Group1.getSelectedToggle().toString().substring(index_answer+1, index_answer+2);
			
			testdetailbean.setTESTDETAIL_ID_PK(TestDetailAdd.testdetail_id);
			testdetailbean.setTESTDETAIL_SUBTITLE(testdetail_subtitle.getText().toString());
			testdetailbean.setTESTDETAIL_IMAGE_PATH(filename);
			testdetailbean.setTESTDETAIL_IMAGE(input);
			question_selected.add(question_selected.get(0));
			question_selected.add(question_selected.get(1));
			question_selected.add(question_selected.get(2));
			question_selected.add(question_selected.get(3));
			question_selected.add(question_selected.get(4));
			testdetailbean.setTESTDETAIL_DATA(CommonController.makeAnswer(question_selected));
			testdetailbean.setTESTDETAIL_ANSWER(question_selected.get(Integer.parseInt(number_answer)-1));
			testdetailadd.updateTestDetail(testdetailbean);
		}catch(Exception e) {e.printStackTrace(); }
		try {
			CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestboarddetailview());
		}catch(Exception e) { e.printStackTrace();}
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
		txtFilepath.setText(CommonController.selectcontent(TestDetailAdd.testdetail_id, "TESTDETAIL_IMAGE_PATH", config.StaticProperty.gettestdetail_tb(), "TESTDETAIL_ID_PK"));
//		image = new Image(testdetailadd.selectIMAGE(TestDetailAdd.testdetail_id).toURI().toString(),623,150,true,true);
//		testdetail_imageview.setImage(image);
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