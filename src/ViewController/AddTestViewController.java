package ViewController;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import DBController.TestDetailAdd;
import DBModel.TestDetailBean;
import ImageStore.TestImageStore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AddTestViewController implements Initializable {
	//Declare JAVA
	private TestDetailBean testdetailbean;
	private Stage stage; // file choose 하기 위함.
	public static String filename;
	public static String filepath;
	public static String login_id = LoginViewController.login_id;
		
	//Declare FXML
	@FXML private TextField Radio1, Radio2, Radio3, Radio4, Radio5;
	@FXML private RadioButton Rb1, Rb2, Rb3, Rb4, Rb5;
	@FXML private Button SearchFile;
	@FXML private Button Property_userID;
	@FXML private ToggleGroup Quest1Group1;
	@FXML private Button BtnSave;
	@FXML private TextField txtSubtitle; 
	@FXML private Label txtFilepath;
	@FXML
	private void saveAction(ActionEvent action) {
		testdetailbean = new TestDetailBean();
		testdetailbean.setTESTDETAIL_ID_PK(usingstaticfunction.TestDetailFunction.makeTestDetailKey(txtSubtitle.getText().toString()));
		testdetailbean.setTESTDETAIL_SUBTITLE(txtSubtitle.getText().toString());
		testdetailbean.setTESTDETAIL_IMAGE(filepath);
		testdetailbean.setTESTDETAIL_DATA1(Radio1.getText().toString());
		testdetailbean.setTESTDETAIL_DATA2(Radio2.getText().toString());
		testdetailbean.setTESTDETAIL_DATA3(Radio3.getText().toString());
		testdetailbean.setTESTDETAIL_DATA4(Radio4.getText().toString());
		testdetailbean.setTESTDETAIL_DATA5(Radio5.getText().toString());

		if(Rb1.isSelected()) {
			testdetailbean.setTESTDETAIL_ANSWER(Radio1.getText().toString());
		}else if(Rb2.isSelected()) {
			testdetailbean.setTESTDETAIL_ANSWER(Radio2.getText().toString());
		}else if(Rb3.isSelected()) {
			testdetailbean.setTESTDETAIL_ANSWER(Radio3.getText().toString());
		}else if(Rb4.isSelected()) {
			testdetailbean.setTESTDETAIL_ANSWER(Radio4.getText().toString());
		}else {
			testdetailbean.setTESTDETAIL_ANSWER(Radio5.getText().toString());
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
	public void openFile() {
		FileChooser fileChooser = new FileChooser();
		File file = fileChooser.showOpenDialog(stage);
		if(file != null) {
			filename = file.getName();
			String Address = file.toString().replaceAll("\\\\", "//");
			new TestImageStore("112233", Address); 
			filepath = Address;
		}
	}
	public void initialize(URL url, ResourceBundle rb) {
	}
	public void fileChooserSelect(ActionEvent event) { 
		openFile(); 
		txtFilepath.setText(filename);
	}
}
