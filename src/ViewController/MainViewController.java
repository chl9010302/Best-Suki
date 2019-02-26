package ViewController;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import DBController.NoticeDetailAdd;
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

public class MainViewController implements Initializable {
	//Declare JAVA
	private NoticeDetailAdd noticedetailadd;
	private final List<NoticeDetailAdd> main_data = createData();
	private TableView<NoticeDetailAdd> main_table = createTable();
	private TableColumn<NoticeDetailAdd, String> subtitleColumn, writerColumn, dateColumn, btndetailColumn;
	private int fromindex, toindex, rowsPerPage = 10;
	private int selectedItem;
	//Declare FXML
	@FXML private Pagination main_pagination;
	@FXML private void NAV_LoginView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavloginview()); }
	@FXML private void NAV_MainView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmainview());	}
	@FXML private void NAV_TestView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestview()); }
	@FXML private void NAV_TestBoardView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestboardview()); }
	@FXML private void NAV_StatisticsView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavstatisticsview()); }
	@FXML private void NAV_MypageView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmypageview()); }
	@FXML private void NAV_VideoView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavvideoview()); }
	@FXML private void logout(ActionEvent event) { CommonController.logout(getClass(), event); }
	@FXML private void addAction(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavaddmainview()); }
	@FXML
	private void deleteAction(ActionEvent event){
		noticedetailadd = new NoticeDetailAdd();
		selectedItem = main_table.getSelectionModel().getSelectedIndex();
		if(selectedItem == -1) {
			CommonController.Alert_ERROR(event, config.StaticProperty.alerttitlenoitem(), config.StaticProperty.alertnoitem());
		} else {
			noticedetailadd.delete(String.valueOf(main_table.getItems().get(selectedItem).getNoticedetail_id_pk().getValue()));
			main_table.setItems(noticedetailadd.getnoticedetailadd());
		}
	}
	public void initialize(URL url, ResourceBundle rb) {
		main_pagination.setPageFactory(this::createPage);
		main_pagination.setPageCount((int)Math.ceil((double)main_data.size()/rowsPerPage));
	}
	private Node createPage(int pageIndex) {
		fromindex = pageIndex * rowsPerPage;
		toindex = Math.min(fromindex + rowsPerPage, main_data.size());
		main_table.setItems(FXCollections.observableArrayList(main_data.subList(fromindex, toindex)));
		main_table.setMaxHeight(310);
		return main_table;
	}
	@SuppressWarnings("unchecked")
	private TableView<NoticeDetailAdd> createTable() {
		main_table = new TableView<>();
		subtitleColumn = new TableColumn<>("제목");
		subtitleColumn.setCellValueFactory(param -> param.getValue().getNoticedetail_subtitle());
		subtitleColumn.setPrefWidth(350);
		writerColumn = new TableColumn<>("작성자");
		writerColumn.setCellValueFactory(param -> param.getValue().getNoticedetail_writer());
		writerColumn.setPrefWidth(100);
		dateColumn = new TableColumn<>("날짜");
		dateColumn.setCellValueFactory(param -> param.getValue().getNoticedetail_time());
		dateColumn.setPrefWidth(100);
		btndetailColumn = new TableColumn<>("상세보기");
		btndetailColumn.setCellValueFactory(new PropertyValueFactory<NoticeDetailAdd, String>("noticedetail_btndetail"));
		btndetailColumn.setPrefWidth(100);
		main_table.getColumns().addAll(subtitleColumn, writerColumn, dateColumn, btndetailColumn);
		main_table.setBackground(Background.EMPTY);
		return main_table;
	}
	private List<NoticeDetailAdd> createData() {
		noticedetailadd = new NoticeDetailAdd();
		return noticedetailadd.getnoticedetailadd();
	}
}
