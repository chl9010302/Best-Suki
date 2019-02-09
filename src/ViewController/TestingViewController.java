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
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class TestingViewController implements Initializable {
	//Declare JAVA
	private TestDetailBean testdetailbean;
	public static String filepath;
	//Declare FXML
	@FXML private Button BtnAdd, BtnDelete, BtnSave;
	@FXML private RadioButton Rb1, Rb2, Rb3, Rb4, Rb5;
	@FXML private TextField Radio1, Radio2, Radio3, Radio4, Radio5, txtAddItem, txtSubtitle;
	@FXML private ToggleGroup Quest1Group1;
	@FXML private TableView<TestDetailAdd> testTableView;
	@FXML private TableColumn<TestDetailAdd, String> ColBoardId, ColSubtitle;
	@FXML private void NAV_LoginView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavloginview()); }
	@FXML private void NAV_MainView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmainview());	}
	@FXML private void NAV_TestView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestview()); }
	@FXML private void NAV_TestBoardView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestboardview()); }
	@FXML private void NAV_AddTestView(ActionEvent event) throws IOException { CommonController.NAV_POPUP(getClass(), event, config.StaticProperty.getnavaddtestview()); }
	@FXML private void NAV_StasticsView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavstasticsview()); }
	@FXML private void NAV_MypageView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmypageview()); }
	@FXML private void NAV_VideoView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavvideoview()); }
	@FXML private void logout(ActionEvent event) { CommonController.logout(event, getClass()); }
	@FXML
	private void removeAction(ActionEvent action){
		TestDetailAdd testdetailadd = new TestDetailAdd();
	  int selectedItem = testTableView.getSelectionModel().getSelectedIndex();
	  testdetailadd.delete(String.valueOf((testTableView.getItems().get(selectedItem).getTestdetail_id_pk().getValue())));
	  testTableView.setItems(testdetailadd.gettestdetailadd());
	}
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
			ColSubtitle.setCellValueFactory(cellData -> cellData.getValue().getSubtitle());
			testTableView.setItems(testdetailadd.gettestdetailadd());
		}catch(Exception e) {}
	}
}
