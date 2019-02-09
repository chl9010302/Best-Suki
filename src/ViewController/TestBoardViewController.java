package ViewController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import DBController.TestDetailAdd;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TestBoardViewController implements Initializable {
	//Declare FXML
	@FXML private TableView<TestDetailAdd> testTableView;
	@FXML private TableColumn<TestDetailAdd, String> ColBoardId, ColSubtitle, ColWriter, ColDate;
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
		testdetailadd.delete(String.valueOf(testTableView.getItems().get(selectedItem).getTestdetail_id_pk().getValue()));
		testTableView.setItems(testdetailadd.gettestdetailadd());
	}
	@FXML
	private void modify(ActionEvent action) {
	}
	public void initialize(URL url, ResourceBundle rb) {
	  // TODO
		try {
			TestDetailAdd testdetailadd = new TestDetailAdd();
			ColBoardId.setCellValueFactory(cellData -> cellData.getValue().getTestdetail_id_pk());
			ColSubtitle.setCellValueFactory(cellData -> cellData.getValue().getSubtitle());
			ColWriter.setCellValueFactory(cellData -> cellData.getValue().getTestdetail_writer());
			ColDate.setCellValueFactory(cellData -> cellData.getValue().getTestdetail_time());
			testTableView.setItems(testdetailadd.gettestdetailadd());
		}catch(Exception e) {}
	}
}
