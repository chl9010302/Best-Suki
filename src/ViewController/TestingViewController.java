package ViewController;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import DBController.TestAdd;
import DBController.TestDetailAdd;
import DBController.UserJoin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TestingViewController implements Initializable {
	public static Image image;
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
	private void nextAction(ActionEvent event) {
		TestAdd.maxpage--;
		if(TestAdd.maxpage != 0) {
			TestAdd testadd = new TestAdd();
			++TestAdd.pagenumber;
			TestAdd.test_id_fk = testadd.selectgetTestId(TestAdd.testing_id);
			try {
				ViewController.CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestingview());
			} catch (IOException e) {e.printStackTrace(); }
		}else {
			try {
				ButtonType YES = new ButtonType(config.StaticProperty.alertbtndone(), ButtonBar.ButtonData.OK_DONE);
				Alert alert = new Alert(AlertType.NONE,config.StaticProperty.alertcongraturations(), YES);
				alert.setTitle(config.StaticProperty.alertcongraturations());
				Optional<ButtonType> result = alert.showAndWait();
				if (result.orElse(YES) == YES) {
					CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestview());
				}
			} catch (IOException e) {e.printStackTrace(); }
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
		TestDetailAdd testdetailadd = new TestDetailAdd();
		TestAdd testadd = new TestAdd();
		testdetail_subtitle.setText(testdetailadd.selectSubtitle(testadd.test_id_fk));
		testdetail_answer1.setText(testdetailadd.selectDATA1(testadd.test_id_fk));
		testdetail_answer2.setText(testdetailadd.selectDATA2(testadd.test_id_fk));
		testdetail_answer3.setText(testdetailadd.selectDATA3(testadd.test_id_fk));
		testdetail_answer4.setText(testdetailadd.selectDATA4(testadd.test_id_fk));
		testdetail_answer5.setText(testdetailadd.selectDATA5(testadd.test_id_fk));
		image = new Image(testdetailadd.selectIMAGE(testadd.test_id_fk).toURI().toString(),623,150,true,true);
		testdetail_imageview.setImage(image);
	}
}
