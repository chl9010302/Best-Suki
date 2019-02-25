package ViewController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import DBController.TestDetailAdd;
import DBModel.TestDetailBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AddTestBoardViewController{
	//Declare JAVA
	private TestDetailBean testdetailbean;
	private Stage stage; // file choose 하기 위함.
	public static String filename, number_answer;
	public static String login_id = LoginViewController.login_id;
	public static FileInputStream input;
	private TestDetailAdd detailAdd;
	private FileChooser fileChooser;
	private File file;
	private ArrayList<String> question_selected;
	private int index_answer;
	//Declare FXML
	@FXML private TextField testdetail_subtitle, testdetail_answer1, testdetail_answer2, testdetail_answer3, testdetail_answer4, testdetail_answer5;
	@FXML private RadioButton testdetail_rb1, testdetail_rb2, testdetail_rb3, testdetail_rb4, testdetail_rb5;
	@FXML private ToggleGroup Quest1Group1;
	@FXML private Label txtFilepath;
	@FXML
	private void saveAction(ActionEvent event) {
		if(input != null) {
			question_selected = new ArrayList<>();
			testdetailbean = new TestDetailBean();
			detailAdd = new TestDetailAdd();
			Quest1Group1 = new ToggleGroup();
			// ToggleGroup내 rb setup
			testdetail_rb1.setToggleGroup(Quest1Group1);
			testdetail_rb2.setToggleGroup(Quest1Group1);
			testdetail_rb3.setToggleGroup(Quest1Group1);
			testdetail_rb4.setToggleGroup(Quest1Group1);
			testdetail_rb5.setToggleGroup(Quest1Group1);
			question_selected.add(testdetail_answer1.getText().toString());
			question_selected.add(testdetail_answer2.getText().toString());
			question_selected.add(testdetail_answer3.getText().toString());
			question_selected.add(testdetail_answer4.getText().toString());
			question_selected.add(testdetail_answer5.getText().toString());
			index_answer = Quest1Group1.getSelectedToggle().toString().indexOf("'");
			number_answer = Quest1Group1.getSelectedToggle().toString().substring(index_answer+1, index_answer+2);
			testdetailbean.setTESTDETAIL_ID_PK(CommonController.MakeId());
			testdetailbean.setTESTDETAIL_SUBTITLE(testdetail_subtitle.getText().toString());
			testdetailbean.setTESTDETAIL_IMAGE_PATH(filename);
			testdetailbean.setTESTDETAIL_IMAGE(input);
			testdetailbean.setTESTDETAIL_DATA(CommonController.makeAnswer(question_selected));
			testdetailbean.setTESTDETAIL_ANSWER(question_selected.get(Integer.parseInt(number_answer)-1));
			testdetailbean.setTESTDETAIL_WRITER(login_id);
			((Stage) ((Node) event.getSource()).getScene().getWindow()).close(); // 창 닫음.
			detailAdd.insertTestDetail(testdetailbean);
		}else {
			CommonController.Alert_ERROR(event, config.StaticProperty.alerttitlenoimage(), config.StaticProperty.alertputimage());
		}
	}
	public void openFile() {
		fileChooser = new FileChooser();
		file = fileChooser.showOpenDialog(stage);
		filename = file.getName();
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
