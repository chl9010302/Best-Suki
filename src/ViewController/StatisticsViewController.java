package ViewController;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import DBController.AddStatistics;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Background;
public class StatisticsViewController implements Initializable {
	//Declare JAVA
	private AddStatistics addstatistics;
	private final List<AddStatistics> statistics_data = createData();
	private TableView<AddStatistics> statistics_table = createTable();
	private TableColumn<AddStatistics, String> idColumn, loginColumn, logoutColumn;
	private int fromindex, toindex, rowsPerPage = 10;
	//Declare FXML
	@FXML private Pagination statistics_pagination;
	@FXML private void NAV_LoginView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavloginview()); }
	@FXML private void NAV_MainView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmainview());	}
	@FXML private void NAV_TestView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestview()); }
	@FXML private void NAV_TestBoardView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestboardview()); }
	@FXML private void NAV_StatisticsView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavstatisticsview()); }
	@FXML private void NAV_MypageView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmypageview()); }
	@FXML private void NAV_VideoView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavvideoview()); }
	@FXML private void logout(ActionEvent event) { CommonController.logout(getClass(), event); }
	public void initialize(URL url, ResourceBundle rb) {
		statistics_pagination.setPageFactory(this::createPage);
		statistics_pagination.setPageCount((int)Math.ceil((double)statistics_data.size()/rowsPerPage));
	}
	private Node createPage(int pageIndex) {
		fromindex = pageIndex * rowsPerPage;
		toindex = Math.min(fromindex + rowsPerPage, statistics_data.size());
		statistics_table.setItems(FXCollections.observableArrayList(statistics_data.subList(fromindex, toindex)));
		statistics_table.setMaxHeight(310);
		return statistics_table;
	}
	@SuppressWarnings("unchecked")
	private TableView<AddStatistics> createTable() {
		statistics_table = new TableView<>();
		idColumn = new TableColumn<>("USER_ID");
		idColumn.setCellValueFactory(param -> param.getValue().getUSER_ID());
		idColumn.setPrefWidth(72);
		loginColumn = new TableColumn<>("USER_LOGIN_DATE");
		loginColumn.setCellValueFactory(param -> param.getValue().getUSER_LOGIN_DATE());
		loginColumn.setPrefWidth(331);
		logoutColumn = new TableColumn<>("USER_LOGOUT_DATE");
		logoutColumn.setCellValueFactory(param -> param.getValue().getUSER_LOGOUT_DATE());
		logoutColumn.setPrefWidth(251);
		statistics_table.getColumns().addAll(idColumn, loginColumn, logoutColumn);
		statistics_table.setBackground(Background.EMPTY);
		statistics_table.setPrefHeight(550);
		return statistics_table;
	}
	private List<AddStatistics> createData() {
		addstatistics = new AddStatistics();
		return addstatistics.getstatistics();
	}
}
