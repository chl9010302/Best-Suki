package ViewController;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import DBController.TestAdd;
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

public class TestViewController implements Initializable {
	//Declare JAVA
	public static String test_id="";
	private TestAdd testadd;
	private final List<TestAdd> test_data = createData();
	private TableView<TestAdd> test_table = createTable();
	private TableColumn<TestAdd, String> subtitleColumn, writerColumn, dateColumn, btndetailColumn;
	private int fromindex, toindex, rowsPerPage = 10;
	private int selectedItem;
	//Declare FXML
	@FXML private Pagination test_pagination;
	@FXML private void NAV_LoginView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavloginview()); }
	@FXML private void NAV_MainView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmainview());	}
	@FXML private void NAV_TestView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestview()); }
	@FXML private void NAV_TestBoardView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestboardview()); }
	@FXML private void NAV_StatisticsView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavstatisticsview()); }
	@FXML private void NAV_MypageView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmypageview()); }
	@FXML private void NAV_VideoView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavvideoview()); }
	@FXML private void logout(ActionEvent event) { CommonController.logout(getClass(), event); }
	@FXML private void addAction(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavaddtestview()); }
	@FXML
	private void removeAction(ActionEvent event){
		testadd = new TestAdd();
		selectedItem = test_table.getSelectionModel().getSelectedIndex();
		if(selectedItem == -1) {
			CommonController.Alert_ERROR(event, config.StaticProperty.alerttitlenoitem(), config.StaticProperty.alertnoitem());
		} else {
			testadd.delete(String.valueOf(test_table.getItems().get(selectedItem).getTest_id_pk().getValue()));
			test_table.setItems(testadd.gettestadd());
		}
	}
	@FXML
	private void NAV_TestDetailView(ActionEvent event) throws IOException { 
		testadd = new TestAdd();
		if(test_table.getSelectionModel().getSelectedIndex() == -1) {
			CommonController.Alert_ERROR(event, config.StaticProperty.alerttitlenoitem(), config.StaticProperty.alertnoitem());
		}else {
			selectedItem = test_table.getSelectionModel().getSelectedIndex();
			test_id = String.valueOf(test_table.getItems().get(selectedItem).getTest_id_pk().getValue());
			CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestdetailview());
		}
	}
	public void initialize(URL url, ResourceBundle rb) {
		test_pagination.setPageFactory(this::createPage);
		test_pagination.setPageCount((int)Math.ceil((double)test_data.size()/rowsPerPage));
	}
	private Node createPage(int pageIndex) {
		fromindex = pageIndex * rowsPerPage;
		toindex = Math.min(fromindex + rowsPerPage, test_data.size());
		test_table.setItems(FXCollections.observableArrayList(test_data.subList(fromindex, toindex)));
		test_table.setMaxHeight(310);
		return test_table;
	}
	@SuppressWarnings("unchecked")
	private TableView<TestAdd> createTable() {
		test_table = new TableView<>();
		subtitleColumn = new TableColumn<>("제목");
		subtitleColumn.setCellValueFactory(param -> param.getValue().getTest_Subtitle());
		subtitleColumn.setPrefWidth(350);
		writerColumn = new TableColumn<>("작성자");
		writerColumn.setCellValueFactory(param -> param.getValue().getTest_writer());
		writerColumn.setPrefWidth(100);
		dateColumn = new TableColumn<>("날짜");
		dateColumn.setCellValueFactory(param -> param.getValue().getTest_time());
		dateColumn.setPrefWidth(100);
		btndetailColumn = new TableColumn<>("상세보기");
		btndetailColumn.setCellValueFactory(new PropertyValueFactory<TestAdd, String>("test_btndetail"));
		btndetailColumn.setPrefWidth(88);
		test_table.getColumns().addAll(subtitleColumn, writerColumn, dateColumn, btndetailColumn);
		test_table.setBackground(Background.EMPTY);
		return test_table;
	}
	private List<TestAdd> createData() {
		testadd = new TestAdd();
		return testadd.gettestadd();
	}
}
