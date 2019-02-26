package ViewController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import DBController.TestAdd;
import DBController.TestDetailAdd;
import DBModel.TestBean;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
public class AddTestViewController implements Initializable {
	//Declare JAVA
	public static String login_id = LoginViewController.login_id;
	private TestBean testbean;
	private ArrayList<String> getresult, checkarraylist;
	private TestDetailAdd testdetailadd;
	private TestAdd testadd;
	private int maxnumber;
	private String result="";
	private final List<TestDetailAdd> addtest_data = createData();
	private TableView<TestDetailAdd> addtest_table = createTable();
	private TableColumn<TestDetailAdd, String> writerColumn, dateColumn;
	private TableColumn<TestDetailAdd, CheckBox> checkColumn;
	private int fromindex, toindex, rowsPerPage = 10;
	//Declare FXML
	@FXML private Pagination addtest_pagination;
	@FXML private TextField ColTest_Subtitle;
	@FXML private void NAV_LoginView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavloginview()); }
	@FXML private void NAV_MainView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmainview());	}
	@FXML private void NAV_TestView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestview()); }
	@FXML private void NAV_TestBoardView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestboardview()); }
	@FXML private void NAV_StatisticsView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavstatisticsview()); }
	@FXML private void NAV_MypageView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmypageview()); }
	@FXML private void NAV_VideoView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavvideoview()); }
	@FXML private void logout(ActionEvent event) { CommonController.logout(getClass(), event); }
	@FXML private void deleteAction(ActionEvent event) { CommonController.Alert_YesorNo(event, config.StaticProperty.alertcancel(), config.StaticProperty.alerttitlecancel(), getClass(), config.StaticProperty.getnavtestview()); }
	@FXML
	private void addAction(ActionEvent event) {
		try {
			getresult = CheckFunction(checkColumn);
			testbean = new TestBean();
			testdetailadd = new TestDetailAdd();
			testadd = new TestAdd();
			testbean.setTEST_ID_PK(CommonController.MakeId());
			testbean.setTEST_SUBTITLE(ColTest_Subtitle.getText().toString());
			testbean.setTEST_WRITER(login_id);
			for(int i = 0 ; i < getresult.size(); i++) {
				if(i == getresult.size()-1) {
					result += getresult.get(i);
				}else {
					result += getresult.get(i) + ";";
				}
			}
			testbean.setTESTDETAIL_ID_FK(result);
			testadd.insertTest(testbean);
			CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestview());
		}catch(Exception e) {e.printStackTrace(); }
	}
	public void initialize(URL url, ResourceBundle rb) { 
		addtest_pagination.setPageFactory(this::createPage);
		addtest_pagination.setPageCount((int)Math.ceil((double)addtest_data.size()/rowsPerPage));
	}
	private Node createPage(int pageIndex) {
		fromindex = pageIndex * rowsPerPage;
		toindex = Math.min(fromindex + rowsPerPage, addtest_data.size());
		addtest_table.setItems(FXCollections.observableArrayList(addtest_data.subList(fromindex, toindex)));
		addtest_table.setMaxHeight(310);
		return addtest_table;
	}
	@SuppressWarnings("unchecked")
	private TableView<TestDetailAdd> createTable() {
		addtest_table = new TableView<>();
		checkColumn = new TableColumn<>("Check");
		checkColumn.setCellValueFactory(new PropertyValueFactory<TestDetailAdd, CheckBox>("testdetail_checkboxdetail"));
		checkColumn.setPrefWidth(100);
		writerColumn = new TableColumn<>("작성자");
		writerColumn.setCellValueFactory(param -> param.getValue().getTestdetail_writer());
		writerColumn.setPrefWidth(100);
		dateColumn = new TableColumn<>("날짜");
		dateColumn.setCellValueFactory(param -> param.getValue().getTestdetail_time());
		dateColumn.setPrefWidth(100);
		addtest_table.getColumns().addAll(checkColumn, writerColumn, dateColumn);
		addtest_table.setBackground(Background.EMPTY);
		return addtest_table;
	}
	private List<TestDetailAdd> createData() {
		testdetailadd = new TestDetailAdd();
		return testdetailadd.gettestdetailadd();
	}
	private ArrayList<String> CheckFunction(TableColumn<TestDetailAdd, CheckBox> tablecolumn) {
		testdetailadd = new TestDetailAdd();
		checkarraylist = new ArrayList<>();
		maxnumber = testdetailadd.gettestdetailadd().size();
		for(int i=0; i < maxnumber; i++) {
			if(tablecolumn.getCellData(i).isSelected()) {
				checkarraylist.add(CommonController.selectcontent(tablecolumn.getCellData(i).getText(), "TESTDETAIL_ID_PK", config.StaticProperty.gettestdetail_tb(), "TESTDETAIL_SUBTITLE"));
			}
		}
		System.out.println(checkarraylist.toString());
		return checkarraylist;
	}
}
