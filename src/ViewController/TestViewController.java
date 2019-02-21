package ViewController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import DBController.TestAdd;
import DBController.TestDetailAdd;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class TestViewController implements Initializable {
	//Declare JAVA
	public static String test_id="";
	//Declare FXML
	@FXML private TableView<TestAdd> testTableView;
	@FXML private TableColumn<TestAdd, String> ColTest_Subtitle, ColTest_Writer, ColTest_Date, ColTest_Btndetail;
	@FXML private void NAV_LoginView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavloginview()); }
	@FXML private void NAV_MainView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmainview());	}
	@FXML private void NAV_TestView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestview()); }
	@FXML private void NAV_TestBoardView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestboardview()); }
	@FXML private void NAV_StasticsView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavstasticsview()); }
	@FXML private void NAV_MypageView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmypageview()); }
	@FXML private void NAV_VideoView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavvideoview()); }
	@FXML private void logout(ActionEvent event) { CommonController.logout(getClass(), event); }
	@FXML private void addAction(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavaddtestview()); }
	@FXML
	private void removeAction(ActionEvent event){
		TestAdd testadd = new TestAdd();
		int selectedItem = testTableView.getSelectionModel().getSelectedIndex();
		if(selectedItem == -1) {
			CommonController.Alert_ERROR(event, config.StaticProperty.alerttitlenoitem(), config.StaticProperty.alertnoitem());
		} else {
			testadd.delete(String.valueOf(testTableView.getItems().get(selectedItem).getTest_id_pk().getValue()));
			testTableView.setItems(testadd.gettestadd());
		}
	}
	@FXML
	private void NAV_TestDetailView(ActionEvent event) throws IOException { 
		if(testTableView.getSelectionModel().getSelectedIndex() == -1) {
			CommonController.Alert_ERROR(event, config.StaticProperty.alerttitlenoitem(), config.StaticProperty.alertnoitem());
		}else {
			TestAdd testadd = new TestAdd();
			int selectedItem = testTableView.getSelectionModel().getSelectedIndex();
			test_id = String.valueOf(testTableView.getItems().get(selectedItem).getTest_id_pk().getValue());
//			testadd.gettestdetailid(test_id);
			CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestdetailview());
		}
	}
	public void initialize(URL url, ResourceBundle rb) {
		try {
			TestAdd testadd = new TestAdd();
			ColTest_Subtitle.setCellValueFactory(cellData -> cellData.getValue().getTest_Subtitle());
			ColTest_Writer.setCellValueFactory(cellData -> cellData.getValue().getTest_writer());
			ColTest_Date.setCellValueFactory(cellData -> cellData.getValue().getTest_time());
			ColTest_Btndetail.setCellValueFactory(new PropertyValueFactory<TestAdd, String>("test_btndetail"));
			testTableView.setItems(testadd.gettestadd());
		}catch(Exception e) {}
	}
}
