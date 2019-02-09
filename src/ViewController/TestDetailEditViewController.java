package ViewController;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import DBController.TestDetailAdd;
import DBModel.TestDetailBean;
import ImageStore.TestImageStore;
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

public class TestDetailEditViewController implements Initializable {
	//Declare JAVA
	private TestDetailBean testdetailbean;
	private Stage stage; // file choose 하기 위함.
	public static String filename, filepath;
	public static String login_id = LoginViewController.login_id;
	//Declare FXML
	@FXML private TextField testdetail_subtitle;
	@FXML private ToggleGroup Quest1Group1;
	@FXML private TextField Radio1, Radio2, Radio3, Radio4, Radio5;
	@FXML private RadioButton Rb1, Rb2, Rb3, Rb4, Rb5;
	@FXML private Label txtFilepath;
	@FXML private void NAV_LoginView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavloginview()); }
	@FXML private void NAV_MainView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmainview());	}
	@FXML private void NAV_TestView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestview()); }
	@FXML private void NAV_TestBoardView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestboardview()); }
	@FXML private void NAV_StasticsView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavstasticsview()); }
	@FXML private void NAV_MypageView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmypageview()); }
	@FXML private void NAV_VideoView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavvideoview()); }
	@FXML private void logout(ActionEvent event) { CommonController.logout(event, getClass()); }
	@FXML
	private void deleteAction(ActionEvent event) {
		ButtonType YES = new ButtonType(config.StaticProperty.alertbtnyes(), ButtonBar.ButtonData.OK_DONE);
		ButtonType NO = new ButtonType(config.StaticProperty.alertbtnno(), ButtonBar.ButtonData.CANCEL_CLOSE);
		Alert alert = new Alert(AlertType.NONE,config.StaticProperty.alertcancel(), YES, NO);
		alert.setTitle(config.StaticProperty.alerttitlecancel());
		Optional<ButtonType> result = alert.showAndWait();
		if (result.orElse(NO) == YES) {
			try {
				CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestdetailview());
			}catch(Exception e) { }
		}
	}
	@FXML
	private void editAction(ActionEvent event) {
		TestDetailAdd testdetailadd = new TestDetailAdd();
		try {
			testdetailbean = new TestDetailBean();
			testdetailbean.setTESTDETAIL_ID_PK(testdetailadd.testdetail_id);
			testdetailbean.setTESTDETAIL_SUBTITLE(testdetail_subtitle.getText().toString());
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
			testdetailadd.updateTestDetail(testdetailbean);
		}catch(Exception e) {e.printStackTrace(); }
		try {
			CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestdetailview());
		}catch(Exception e) { }
	}
	@FXML
	private void Quest1Group1Action(ActionEvent action) {
	}
	public void initialize(URL url, ResourceBundle rb) {
		TestDetailAdd testdetailadd = new TestDetailAdd();
		testdetail_subtitle.setText(testdetailadd.selectSubtitle(testdetailadd.testdetail_id));
		Radio1.setText(testdetailadd.selectDATA1(testdetailadd.testdetail_id));
		Radio2.setText(testdetailadd.selectDATA2(testdetailadd.testdetail_id));
		Radio3.setText(testdetailadd.selectDATA3(testdetailadd.testdetail_id));
		Radio4.setText(testdetailadd.selectDATA4(testdetailadd.testdetail_id));
		Radio5.setText(testdetailadd.selectDATA5(testdetailadd.testdetail_id));
	}
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
	public void fileChooserSelect(ActionEvent event) { 
		openFile(); 
		txtFilepath.setText(filename);
	}
}
