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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AddTestBoardViewController{
	//Declare JAVA
	private TestDetailBean testdetailbean;
	private Stage stage; // file choose 하기 위함.
	public static String filename;
	public static String login_id = LoginViewController.login_id;
	public static FileInputStream input;
	private TestDetailAdd detailAdd;
	private FileChooser fileChooser;
	private File file;
	//Declare FXML
	@FXML private TextField testdetail_subtitle, testdetail_answer1, testdetail_answer2, testdetail_answer3, testdetail_answer4, testdetail_answer5;
	@FXML private RadioButton testdetail_rb1, testdetail_rb2, testdetail_rb3, testdetail_rb4, testdetail_rb5;
	@FXML private ToggleGroup Quest1Group1;
	@FXML private Label txtFilepath;
	@FXML
	private void saveAction(ActionEvent event) {
		if(input != null) {
			testdetailbean = new TestDetailBean();
			detailAdd = new TestDetailAdd();
			testdetailbean.setTESTDETAIL_ID_PK(CommonController.MakeId());
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
	@FXML
	private ToggleGroup Quest1Group1Action() {
		return null;
	}
	public void fileChooserSelect(ActionEvent event) { 
		openFile(); 
		txtFilepath.setText(filename);
	}
}
