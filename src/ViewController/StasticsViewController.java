package ViewController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import DBController.AddStastics;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class StasticsViewController implements Initializable {
	//Declare FXML
	@FXML private TableView<AddStastics> StasticsView;
	@FXML private TableColumn<AddStastics, String> USER_ID, USER_LOGIN_DATE, USER_LOGOUT_DATE;
	@FXML private void NAV_LoginView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavloginview()); }
	@FXML private void NAV_MainView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmainview());	}
	@FXML private void NAV_TestView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestview()); }
	@FXML private void NAV_TestBoardView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestboardview()); }
	@FXML private void NAV_StasticsView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavstasticsview()); }
	@FXML private void NAV_MypageView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavmypageview()); }
	@FXML private void NAV_VideoView(ActionEvent event) throws IOException { CommonController.NAV(getClass(), event, config.StaticProperty.getnavvideoview()); }
	@FXML private void logout(ActionEvent event) { CommonController.logout(getClass(), event); }
	public void initialize(URL url, ResourceBundle rb) {
		try {
			AddStastics stasticsview = new AddStastics();
			USER_ID.setCellValueFactory(cellData -> cellData.getValue().getUSER_ID());
			USER_LOGIN_DATE.setCellValueFactory(cellData -> cellData.getValue().getUSER_LOGIN_DATE());
			USER_LOGOUT_DATE.setCellValueFactory(cellData -> cellData.getValue().getUSER_LOGOUT_DATE());
			StasticsView.setItems(stasticsview.getstastics());
		}catch(Exception e) {}
	}
}
