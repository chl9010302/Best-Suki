package ViewController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import DBController.VideoDetailAdd;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class VideoViewController implements Initializable {
	//Declare FXML
	private VideoDetailAdd videodetailadd;
	private int selectedItem;
	@FXML private TableView<VideoDetailAdd> videotableview;
	@FXML private TableColumn<VideoDetailAdd, String> ColVideo_Id, ColVideo_Subtitle, ColVideo_Writer, ColVideo_Date, ColVideo_Btndetail;
	@FXML private void NAV_LoginView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavloginview()); }
	@FXML private void NAV_MainView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmainview());	}
	@FXML private void NAV_TestView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestview()); }
	@FXML private void NAV_TestBoardView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestboardview()); }
	@FXML private void NAV_StasticsView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavstasticsview()); }
	@FXML private void NAV_MypageView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmypageview()); }
	@FXML private void NAV_VideoView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavvideoview()); }
	@FXML private void logout(ActionEvent event) { CommonController.logout(getClass(), event); }
	@FXML private void addAction(ActionEvent event) throws IOException { CommonController.NAV_POPUP(getClass(), event, config.StaticProperty.getnavaddvideoview());	}
	@FXML
	private void deleteAction(ActionEvent event){
		videodetailadd = new VideoDetailAdd();
		selectedItem = videotableview.getSelectionModel().getSelectedIndex();
		if(selectedItem == -1) {
			CommonController.Alert_ERROR(event, config.StaticProperty.alerttitlenoitem(), config.StaticProperty.alertnoitem());
		} else {
			videodetailadd.delete(String.valueOf(videotableview.getItems().get(selectedItem).getVideodetail_id_pk().getValue()));
			videotableview.setItems(videodetailadd.getvideodetailadd());
		}
	}
	public void initialize(URL url, ResourceBundle rb) {
		videodetailadd = new VideoDetailAdd();
		ColVideo_Subtitle.setCellValueFactory(cellData -> cellData.getValue().getVideodetail_subtitle());
		ColVideo_Writer.setCellValueFactory(cellData -> cellData.getValue().getVideodetail_writer());
		ColVideo_Date.setCellValueFactory(cellData -> cellData.getValue().getVideodetail_time());
		ColVideo_Btndetail.setCellValueFactory(new PropertyValueFactory<VideoDetailAdd, String>("videodetail_btndetail"));
		videotableview.setItems(videodetailadd.getvideodetailadd());
	}
}
