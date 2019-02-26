package ViewController;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import DBController.TestDetailAdd;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;

public class TestBoardViewController implements Initializable {
	private TestDetailAdd testdetailadd;
	private final List<TestDetailAdd> testboard_data = createData();
	private TableView<TestDetailAdd> testboard_table = createTable();
	private TableColumn<TestDetailAdd, String> subtitleColumn, writerColumn, dateColumn, btndetailColumn;
	private int fromindex, toindex, rowsPerPage = 10;
	private int selectedItem;
	//Declare FXML
	@FXML private Pagination testboard_pagination;
	@FXML private void NAV_LoginView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavloginview()); }
	@FXML private void NAV_MainView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmainview());	}
	@FXML private void NAV_TestView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestview()); }
	@FXML private void NAV_TestBoardView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestboardview()); }
	@FXML private void NAV_StatisticsView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavstatisticsview()); }
	@FXML private void NAV_MypageView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmypageview()); }
	@FXML private void NAV_VideoView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavvideoview()); }
	@FXML private void logout(ActionEvent event) { CommonController.logout(getClass(), event); }
	@FXML private void addAction(ActionEvent event) throws IOException { CommonController.NAV_POPUP(getClass(), event, config.StaticProperty.getnavaddtestboardview()); }
	@FXML
	private void removeAction(ActionEvent event){
		testdetailadd = new TestDetailAdd();
		selectedItem = testboard_table.getSelectionModel().getSelectedIndex();
		if(selectedItem == -1) {
			CommonController.Alert_ERROR(event, config.StaticProperty.alerttitlenoitem(), config.StaticProperty.alertnoitem());
		} else {
			testdetailadd.delete(String.valueOf(testboard_table.getItems().get(selectedItem).getTestdetail_id_pk().getValue()));
			testboard_table.setItems(testdetailadd.gettestdetailadd());
		}
	}
	@FXML
	private void modify(ActionEvent event) {
	}
	public void initialize(URL url, ResourceBundle rb) {
		testboard_pagination.setPageFactory(this::createPage);
		testboard_pagination.setPageCount((int)Math.ceil((double)testboard_data.size()/rowsPerPage));
	}
	private Node createPage(int pageIndex) {
		fromindex = pageIndex * rowsPerPage;
		toindex = Math.min(fromindex + rowsPerPage, testboard_data.size());
		testboard_table.setItems(FXCollections.observableArrayList(testboard_data.subList(fromindex, toindex)));
		testboard_table.setMaxHeight(310);
		return testboard_table;
	}
	@SuppressWarnings("unchecked")
	private TableView<TestDetailAdd> createTable() {
		testboard_table = new TableView<>();
		subtitleColumn = new TableColumn<>("제목");
		subtitleColumn.setCellValueFactory(param -> param.getValue().getTestdetail_Subtitle());
		subtitleColumn.setPrefWidth(350);
		writerColumn = new TableColumn<>("작성자");
		writerColumn.setCellValueFactory(param -> param.getValue().getTestdetail_writer());
		writerColumn.setPrefWidth(100);
		dateColumn = new TableColumn<>("날짜");
		dateColumn.setCellValueFactory(param -> param.getValue().getTestdetail_time());
		dateColumn.setPrefWidth(100);
		btndetailColumn = new TableColumn<>("상세보기");
		btndetailColumn.setCellValueFactory(new PropertyValueFactory<TestDetailAdd, String>("testdetail_btndetail"));
		btndetailColumn.setPrefWidth(90);
		testboard_table.getColumns().addAll(subtitleColumn, writerColumn, dateColumn, btndetailColumn);
		testboard_table.setBackground(Background.EMPTY);
		return testboard_table;
	}
	private List<TestDetailAdd> createData() {
		testdetailadd = new TestDetailAdd();
		return testdetailadd.gettestdetailadd();
	}
}