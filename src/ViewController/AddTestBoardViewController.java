package ViewController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AddTestBoardViewController implements Initializable {
	//Declare JAVA
	private TestDetailBean testdetailbean;
	private Stage stage; // file choose 하기 위함.
	public static String filename, filepath;
	public static String login_id = LoginViewController.login_id;
	public static FileInputStream input;
	//Declare FXML
	@FXML private TextField testdetail_subtitle, testdetail_answer1, testdetail_answer2, testdetail_answer3, testdetail_answer4, testdetail_answer5;
	@FXML private RadioButton testdetail_rb1, testdetail_rb2, testdetail_rb3, testdetail_rb4, testdetail_rb5;
	@FXML private ToggleGroup Quest1Group1;
	@FXML private Label txtFilepath;
	@FXML
	private void saveAction(ActionEvent action) {
		testdetailbean = new TestDetailBean();
		testdetailbean.setTESTDETAIL_ID_PK(usingstaticfunction.TestDetailFunction.makeTestDetailKey(testdetail_subtitle.getText().toString()));
		testdetailbean.setTESTDETAIL_SUBTITLE(testdetail_subtitle.getText().toString());
		testdetailbean.setTESTDETAIL_IMAGE_PATH(filepath);
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
		testdetailbean.setTESTDETAIL_WRITER(login_id);
	    ((Stage) ((Node) action.getSource()).getScene().getWindow()).close(); // 창 닫음.
	    TestDetailAdd detailAdd = new TestDetailAdd();
	    detailAdd.insertTestDetail(testdetailbean);
	}
	@FXML
	private void Quest1Group1Action(ActionEvent action) {
	}
	//fileChoose function
	public void initialize(URL url, ResourceBundle rb) {
	}
	public void openFile() {
		FileChooser fileChooser = new FileChooser();
		File file = fileChooser.showOpenDialog(stage);
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
