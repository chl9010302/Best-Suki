package ViewController;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import DBController.VideoDetailAdd;
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

public class VideoViewController implements Initializable {
	//Declare FXML
	private VideoDetailAdd videodetailadd;
	private final List<VideoDetailAdd> video_data = createData();
	private TableView<VideoDetailAdd> video_table = createTable();
	private TableColumn<VideoDetailAdd, String> subtitleColumn, writerColumn, dateColumn, btndetailColumn;
	private int fromindex, toindex, rowsPerPage = 10;
	private int selectedItem;
	@FXML private Pagination video_pagination;
	@FXML private void NAV_LoginView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavloginview()); }
	@FXML private void NAV_MainView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmainview());	}
	@FXML private void NAV_TestView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestview()); }
	@FXML private void NAV_TestBoardView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestboardview()); }
	@FXML private void NAV_StatisticsView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavstatisticsview()); }
	@FXML private void NAV_MypageView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmypageview()); }
	@FXML private void NAV_VideoView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavvideoview()); }
	@FXML private void logout(ActionEvent event) { CommonController.logout(getClass(), event); }
	@FXML private void addAction(ActionEvent event) throws IOException { CommonController.NAV_POPUP(getClass(), event, config.StaticProperty.getnavaddvideoview());	}
	@FXML
	private void deleteAction(ActionEvent event){
		videodetailadd = new VideoDetailAdd();
		selectedItem = video_table.getSelectionModel().getSelectedIndex();
		if(selectedItem == -1) {
			CommonController.Alert_ERROR(event, config.StaticProperty.alerttitlenoitem(), config.StaticProperty.alertnoitem());
		} else {
			videodetailadd.delete(String.valueOf(video_table.getItems().get(selectedItem).getVideodetail_id_pk().getValue()));
			video_table.setItems(videodetailadd.getvideodetailadd());
		}
	}
	public void initialize(URL url, ResourceBundle rb) {
		video_pagination.setPageFactory(this::createPage);
		video_pagination.setPageCount((int)Math.ceil((double)video_data.size()/rowsPerPage));
	}
	private Node createPage(int pageIndex) {
		fromindex = pageIndex * rowsPerPage;
		toindex = Math.min(fromindex + rowsPerPage, video_data.size());
		video_table.setItems(FXCollections.observableArrayList(video_data.subList(fromindex, toindex)));
		video_table.setMaxHeight(310);
		return video_table;
	}
	@SuppressWarnings("unchecked")
	private TableView<VideoDetailAdd> createTable() {
		video_table = new TableView<>();
		subtitleColumn = new TableColumn<>("제목");
		subtitleColumn.setCellValueFactory(param -> param.getValue().getVideodetail_subtitle());
		subtitleColumn.setPrefWidth(350);
		writerColumn = new TableColumn<>("작성자");
		writerColumn.setCellValueFactory(param -> param.getValue().getVideodetail_writer());
		writerColumn.setPrefWidth(100);
		dateColumn = new TableColumn<>("날짜");
		dateColumn.setCellValueFactory(param -> param.getValue().getVideodetail_time());
		dateColumn.setPrefWidth(100);
		btndetailColumn = new TableColumn<>("상세보기");
		btndetailColumn.setCellValueFactory(new PropertyValueFactory<VideoDetailAdd, String>("videodetail_btndetail"));
		btndetailColumn.setPrefWidth(100);
		video_table.getColumns().addAll(subtitleColumn, writerColumn, dateColumn, btndetailColumn);
		video_table.setBackground(Background.EMPTY);
		return video_table;
	}
	private List<VideoDetailAdd> createData() {
		videodetailadd = new VideoDetailAdd();
		return videodetailadd.getvideodetailadd();
	}
}