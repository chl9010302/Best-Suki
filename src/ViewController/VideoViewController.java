package ViewController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import DBController.VideoDetailAdd;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class VideoViewController implements Initializable {
	public static String selectedid= "";
	//Declare FXML
	@FXML private Button Property_userID, BtnDelete, BtnAdd;
	@FXML private TextField txtAddItem;
	@FXML private TableView<VideoDetailAdd> videoTableView;
	@FXML private TableColumn<VideoDetailAdd, String> ColVideo_Id, ColVideo_Subtitle, ColVideo_Writer, ColVideo_Date, ColVideo_Btndetail;
	@FXML private void NAV_LoginView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavloginview()); }
	@FXML private void NAV_MainView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmainview());	}
	@FXML private void NAV_TestView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestview()); }
	@FXML private void NAV_TestBoardView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestboardview()); }
	@FXML private void NAV_StasticsView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavstasticsview()); }
	@FXML private void NAV_MypageView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmypageview()); }
	@FXML private void NAV_VideoView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavvideoview()); }
	@FXML private void logout(ActionEvent event) { CommonController.logout(event, getClass()); }
	@FXML
	private void addAction(ActionEvent event){
		try {
			CommonController.NAV_POPUP(getClass(), event, config.StaticProperty.getnavaddvideoview());
		}catch(Exception e) { }
	}
	@FXML
	private void deleteAction(ActionEvent action){
		VideoDetailAdd videodetailadd = new VideoDetailAdd();
		int selectedItem = videoTableView.getSelectionModel().getSelectedIndex();
		videodetailadd.delete(String.valueOf(videoTableView.getItems().get(selectedItem).getVideodetail_id_pk().getValue()));
		videoTableView.setItems(videodetailadd.getvideodetailadd());
	}
	public void initialize(URL url, ResourceBundle rb) {
		VideoDetailAdd videodetailadd = new VideoDetailAdd();
		ColVideo_Subtitle.setCellValueFactory(cellData -> cellData.getValue().getVideodetail_subtitle());
		ColVideo_Writer.setCellValueFactory(cellData -> cellData.getValue().getVideodetail_writer());
		ColVideo_Date.setCellValueFactory(cellData -> cellData.getValue().getVideodetail_time());
		ColVideo_Btndetail.setCellValueFactory(new PropertyValueFactory<VideoDetailAdd, String>("videodetail_btndetail"));
		videoTableView.setItems(videodetailadd.getvideodetailadd());
	}
}
